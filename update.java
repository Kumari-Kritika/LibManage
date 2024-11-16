import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/update")
public class update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Fetching form parameters from the request
        String bookid = request.getParameter("bookid");
        String newBookName = request.getParameter("newBookName");
        String newAuthorName = request.getParameter("newAuthorName");
        String newCategoryID = request.getParameter("newCategoryID");
        
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "";

        try {
            // Load and register MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish a connection to the database
            Connection con = DriverManager.getConnection(url, user, pwd);
            
            // SQL query to update a record in the database
            String query = "UPDATE lib SET bookname = ?, authorname = ?, categoryid = ? WHERE BookID = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            // Setting the parameters for the query
            ps.setString(1, newBookName);
            ps.setString(2, newAuthorName);
            ps.setString(3, newCategoryID);
            ps.setString(4, bookid);
            
            // Executing the query
            int count = ps.executeUpdate();
            
            // Verifying if the update was successful
            if (count > 0) {
                out.print("<h3>Record Updated Successfully</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./read.jsp");
                rd.include(request, response);
            } else {
                out.print("<h3>Update Failed. No matching record found.</h3>");
                RequestDispatcher rd = request.getRequestDispatcher("./update.jsp");
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Some error occurred: " + e.getMessage() + "</h3>");
        }
    }
}
