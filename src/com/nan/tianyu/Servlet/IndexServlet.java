package com.nan.tianyu.Servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import com.nan.tianyu.dbutil.*;
import com.nan.tianyu.model.Group;
import com.nan.tianyu.model.UserBean;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StringBuffer sbf = new StringBuffer();
        String check = request.getParameter("user_name");
        System.out.println(check);


        try {
            Connection conn = DatabaseUtil.getConnection();
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM User";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 5: Extract data from result set
            int count =0;
            if (check.equals("Officer")){
                count = 0;
            }
            if (check.equals("Supervisor")){
                count = 1;
            }
            if (check.equals("Manager")){
                count = 2;
            }
            int i = 0;
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("UserID");
                String username = rs.getString("Username");
                if(i == count){
                    System.out.print("USERID::::: " + id + "\t Username=" + username);
                    sbf.append("<h3>UserID=" + id + "; Username=" + username + "</h3>");
                }
                i++;

                //sbf.append("<h3>UserID=" + id + "; Username=" + username + "</h3>");
            }

            rs.close();
            stmt.close();
            conn.close();

            response.getWriter().append(sbf).append(request.getContextPath());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        UserBean usb = new UserBean();
        String userName = request.getParameter("email");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String action = request.getParameter("Register");
        usb.setUserName(userName);
        usb.setEmail(email);
        usb.setFirstName(firstName);
        usb.setLastName(lastName);
        //usb.setPassowrd(password1);
        usb.setStatus(1);
        //usb.setCreateDate(null);



        System.out.println(">>>>>>>>>>>>>>>>>Action>>>>>>"+action);

        boolean error = false;

        if (action != null && action.equals("Register")) {
            if (email.indexOf("@") == -1 || email.indexOf(".") == -1 || email.length() < 10) {
                error = true;
                usb.setMessage("Email is required!!");

            }

            if(!password1.equals(password2)){
                error = true;
                usb.setMessage("Password should be same!!");

            }

            if (error){

                request.setAttribute("user", usb);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
            else{
                request.setAttribute("user", usb);
                usb.setPassowrd(password1);
                Date curr_date = new Date();
                usb.setCreateDate(curr_date);
                DatabaseUtil.insertUser(usb);
                request.getRequestDispatcher("index1.jsp").forward(request, response);
            }

        }

        //session.setAttribute("user", usb);
        // Create User

    }

}
