import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class firstdbserv
 */
@WebServlet("/firstdbserv")
public class firstdbserv extends HttpServlet {
    private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public firstdbserv() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);        
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name");
        String uname = request.getParameter("uname");
        String password = request.getParameter("pass");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");   

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/organisep?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");

            Statement st = conn.createStatement();

            String sql = "insert into student (Name,Uname,Password,Email,Subject) values('"+name+"','"+uname+"','"+password+"','"+email+"','"+subject+"')";

            st.executeUpdate(sql);
            out.println("Data is Successfully Inserted into Student Table");
               }catch (ClassNotFoundException e) {

              e.printStackTrace();

        } catch (SQLException e) {

              e.printStackTrace();

         }
    }

}