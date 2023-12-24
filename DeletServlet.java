import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteurl")
public class DeletServlet extends HttpServlet {
	private static final String query ="delete from user  where id =?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		int id = Integer.parseInt(req.getParameter("id"));
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException cfe) {
			cfe.printStackTrace();
		}
		
		try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaadv","root","root");
				PreparedStatement ps = con.prepareStatement(query);) {
			ps.setInt(1, id);
			
			int count = ps.executeUpdate();
			if(count==1) {
				pw.println("<h>Record Deleted successfully</h>");
			}else {
				pw.println("<h>Something went wrong</h>");
			}
			
			}catch(SQLException se){
			se.printStackTrace();
			pw.println("<h>"+se.getMessage()+"<h>");
			}catch(Exception e) {
				e.printStackTrace();
				pw.println("<h>"+ e.getMessage() +"</h>");
			}
		pw.println("<a href = 'index.html'><button class='btn btn-outline'>Home</button>");
		pw.println("<a href='showData'>Show Users</a>");
		pw.close();
		}
	@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	      doGet(req,res);		
		}


}
