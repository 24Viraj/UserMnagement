import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private final static String query = "select * from user where id = ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	PrintWriter pw = res.getWriter();
	res.setContentType("text/html");
	int id  = Integer.parseInt(req.getParameter("id"));
	
	
	try {
	   Class.forName("com.mysql.jdbc.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaadv","root","root");
			PreparedStatement ps = con.prepareStatement(query);)  {
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
//		pw.println("<form action='editUrl?id=" + id + "'method = 'post'>");
		 pw.println("<form action='editurl?id=" + id + "' method='post'>");
		 
		pw.println("<table align = 'center'>");
		pw.println("<tr>");
		pw.println("<td>Id<td>");
		pw.println("<td><input type = 'text' name = 'id' value = '" + rs.getInt(1) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Name<td>");
		pw.println("<td><input type = 'text' name = 'name' value = '" + rs.getString(2) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Email<td>");
		pw.println("<td><input type = 'text' name = 'email' value = '" + rs.getString(3) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Mobile<td>");
		pw.println("<td><input type = 'text' name = 'mobile' value = '" + rs.getString(4) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>DOB<td>");
		pw.println("<td><input type = 'date' name = 'dob' value = '" + rs.getDate(5) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>City<td>");
		pw.println("<td><input type = 'text' name = 'city' value = '" + rs.getString(6) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td>Gender<td>");
		pw.println("<td><input type = 'text' name = 'gender' value = '" + rs.getString(7) +"' </td> ");
		pw.println("</tr>");
		pw.println("<tr>");
		pw.println("<td><input type= 'submit' value= 'Edit'</td>");
		pw.println("<td><input type= 'reset' value= 'Cancle'</td>");
		pw.println("</tr>");
		pw.println("</table>");
		pw.println("</form>");
		
		
	}catch(SQLException se) {
		pw.println("<h class = 'bg-danger text-light text-center'>"+se.getMessage()+"</h>");
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	pw.println("<a href = 'index.html'><button class='btn btn-outline'>Home</button>");
	
	pw.close();	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	  doGet(req,res);
	}


}
