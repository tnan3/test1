package com.nan.tianyu.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import com.nan.tianyu.dbutil.*;
import com.nan.tianyu.model.User;


/**
 * Created by tianyunan on 12/27/17.
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        User user = new User();
        user.setUserName(username);
        user.setEmail(email);
        user.setPassword(password1);
        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUserID(0);
        user.setStatus(1);
        user.setCreateDate(null);

        DatabaseUtil.insertUser(user);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" +email+ "</h1>");
        out.println("<h2>" +password1+ "</h2>");
        out.println("<h3>" + password2 + "</h3>");
        out.println("</body></html>");
        out.close();
            //request.getRequestDispatcher("/login.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        User user = null;
        out.println("<html><body>");

        out.println("<h1>Hello, World!</h1>");
        Iterator<User> itr = DatabaseUtil.getUserList().iterator();
        while (itr.hasNext()){
            user = itr.next();
            out.println(user.getEmail());
        }

        out.println("</body></html>");

        out.close();



    }
}
