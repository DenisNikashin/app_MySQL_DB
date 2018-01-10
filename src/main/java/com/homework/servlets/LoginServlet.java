package com.homework.servlets;

import com.homework.userAccount.UserAccount;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                                        throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String errorMsg = null;

        if (email == null || email.equals("")) {
            errorMsg ="User Email can't be null or empty";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<p align=\"center\"><font size=\"20\" color=red>"+errorMsg+"</font></p>");
            rd.include(request, response);
        } else {

            Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try {
                preparedStatement = connection.prepareStatement("select id, " +
                        "name, email,country from user_account where email=? and password=? LIMIT 1");

                preparedStatement.setString(1, email);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet != null && resultSet.next()) {
                    UserAccount userAccount = new UserAccount(resultSet.getString("name"),
                                                              resultSet.getString("email"),
                                                              resultSet.getString("country"),
                                                              resultSet.getInt("id"));

                    HttpSession session = request.getSession();
                    session.setAttribute("User", userAccount);
                    response.sendRedirect("home.jsp");
                } else {
                    RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                    PrintWriter out= response.getWriter();
                    out.println("<p align=\"center\"><font size=\"20\" color=red>" +
                                    "No user found with given email id, please register first." +
                                "</font></p>");
                    requestDispatcher.include(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    resultSet.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
