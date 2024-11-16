
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;


@WebServlet("/add") 
public class add extends HttpServlet {

//    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Fetching form parameters from the request
        
        String  bookid = request.getParameter("bookid");
        String bookname = request.getParameter("bookname");
        String authorname = request.getParameter("authorname");
        String categoryid = request.getParameter("categoryid");
        
        
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "";
        
        // Checking if password and confirm password match
        if (bookname.equals(bookname)) {
            try {
                // Load and register MySQL driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                // Establishing a connection to the database
                Connection con = DriverManager.getConnection(url, user, pwd);
                
                // SQL query to insert user data into the database
                String query = "INSERT INTO lib (BookID, bookname, authorname,categoryid) VALUES (?, ?, ?,?)";
                PreparedStatement ps = con.prepareStatement(query);
                
                // Setting the parameters for the query
                ps.setString(1, bookid);
                ps.setString(2, bookname);
                ps.setString(3, authorname);
                ps.setString(4, categoryid);
                
                
                // Executing the query
                int count = ps.executeUpdate();
                
                // Verifying if insertion was successful
                if (count > 0) {
                    out.print("<h3>User Registered Successfully</h3>");
                    RequestDispatcher rd = request.getRequestDispatcher("./read.jsp");
                    rd.include(request, response);
                } else {
                    out.print("<h3>Registration Failed. Please Try Again.</h3>");
                    RequestDispatcher rd = request.getRequestDispatcher("./add.jsp");
                    rd.include(request, response);
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.print("<h3>Some error occurred: " + e.getMessage() + "</h3>");
            }
        } else {
            // If passwords do not match, show an error
            out.print("<h3>Password and Confirm Password do not match!</h3>");
            RequestDispatcher rd = request.getRequestDispatcher("./add.jsp");
            rd.include(request, response);
        }
    }
}
