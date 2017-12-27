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
import com.nan.tianyu.dbutil.*;


/**
 * Created by tianyunan on 12/27/17.
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuffer sbf = new StringBuffer ();

        Connection conn = DatabaseUtil.getConnection();
        Statement stmt = null;

        try {
            stmt = conn.createStatement();

            String sql;
            sql = "SELECT * FROM User";
            ResultSet rs = null;

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Retrieve by column name
            int id = rs.getInt("UserID");
            String username = rs.getString("Username");
            sbf.append("<h3>UserId="+id+"; Username="+username+"</h3><br>");

            System.out.print("USERID::::::::::::::::::::::::::::::::::::::::::::::: " + id);
            }
            // STEP 6: Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();

        } finally {
            // finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try


        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<h1>Hello, World!</h1>");
        out.println(sbf);
        out.println("</body></html>");

        out.close();



    }
}
