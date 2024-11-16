import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/delete")
public class delete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Fetching the 'CategoryID' parameter from the request
        String categoryid = request.getParameter("categoryid");
        
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "";

        try {
            // Load and register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            Connection con = DriverManager.getConnection(url, user, pwd);
            
            // SQL query to delete a record from the database
            String query = "DELETE FROM lib WHERE categoryid = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            // Setting the parameter for the query
            ps.setString(1, categoryid);
            
            // Executing the query
            int count = ps.executeUpdate();
            
            // Verifying if the deletion was successful
            if (count > 0) {
                out.print("<h3>Record Deleted Successfully</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./read.jsp");
                rd.include(request, response);
            } else {
                out.print("<h3>Deletion Failed. No matching record found.</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./delete.jsp");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Some error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
