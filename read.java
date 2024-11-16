import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

@WebServlet("/read")
public class read extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Fetching 'BookID' from the request
        String bookid = request.getParameter("bookid");

        // Output HTML structure including CSS
        out.println("<html><head>");
        out.println("<title>Records Page</title>");
        out.println("<style>");
        out.println("table { width: 100%; border-collapse: collapse; }");
        out.println("th, td { border: 1px solid black; padding: 10px; text-align: left; }");
        out.println("th { background-color: #f2f2f2; }");
        out.println("h2, h3 { color: #333; }");
        out.println("</style>");
        out.println("</head><body>");

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lib";
        String user = "root";
        String pwd = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, pwd);

            String readQuery;
            PreparedStatement ps;

            if (bookid != null && !bookid.isEmpty()) {
                readQuery = "SELECT * FROM lib WHERE bookid = ?";
                ps = conn.prepareStatement(readQuery);
                ps.setString(1, bookid);
            } else {
                readQuery = "SELECT * FROM lib";
                ps = conn.prepareStatement(readQuery);
            }

            ResultSet rs = ps.executeQuery();

            if (!rs.isBeforeFirst()) {
                out.println("<h3>No records found for the provided BookID.</h3>");
            } else {
                out.println("<h2>Records from table:</h2>");
                out.println("<table><tr><th>BookID</th><th>Book Name</th><th>Author Name</th><th>Category ID</th></tr>");

                while (rs.next()) {
                    int id = rs.getInt("bookid");
                    String bookName = rs.getString("bookname");
                    String authorName = rs.getString("authorname");
                    int categoryId = rs.getInt("categoryid");

                    out.println("<tr><td>" + id + "</td><td>" + bookName + "</td><td>" + authorName + "</td><td>" + categoryId + "</td></tr>");
                }

                out.println("</table>");
            }

            rs.close();
            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.print("<h3>Error: " + e.getMessage() + "</h3>");
        }

        out.println("</body></html>");
    }
}
