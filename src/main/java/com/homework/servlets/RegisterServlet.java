package com.homework.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                                                        throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String errorMsg = null;

        if (email == null || email.equals("")) {
            errorMsg = "Email ID can't be null or empty.";
        }
        if (password == null || password.equals("")) {
            errorMsg = "Password can't be null or empty.";
        }
        if (name == null || name.equals("")) {
            errorMsg = "Name can't be null or empty.";
        }
        if (country == null || country.equals("")) {
            errorMsg = "Country can't be null or empty.";
        }

        if (errorMsg != null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.html");
            PrintWriter out= response.getWriter();
            out.println("<p align=\"center\"><font size=\"20\" color=red>"+errorMsg+"</font></p>");
            rd.include(request, response);
        } else {

            Connection dbConnection = (Connection) getServletContext().getAttribute("DBConnection");
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = dbConnection.prepareStatement
                                    ("insert into user_account(name,email,country, password) values (?,?,?,?)");
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, email);
                preparedStatement.setString(3, country);
                preparedStatement.setString(4, password);

                preparedStatement.execute();

                //forward to login page to login
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.html");
                PrintWriter out= response.getWriter();
                out.println("<p align=\"center\"><font size=\"15\" color=green>" +
                                    "Registration successful, please login below." +
                            "</font></p>");
                requestDispatcher.include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            } finally {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
