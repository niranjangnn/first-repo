package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
 
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import bank.SingleTon;


@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	final Logger log = Logger.getLogger(dashboard.class.getName());
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession Session= request.getSession();
		PrintWriter out=response.getWriter();
		String name=Session.getAttribute("name").toString();
		System.out.println(name);
		try{
			Connection con = SingleTon.getSingleTonObject().getConnection();
		
		
		Statement st = con.createStatement();
	
		String qry = "select * from result where name=?";
		ResultSet rs = st.executeQuery(qry);
		while(rs.next()){         
            int accno = rs.getInt("accno");
            String name1 = rs.getString("name");
          System.out.println("Name: " + name1 + "<br>");
            System.out.println("account number: " + accno + "<br>");
           
         }
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		out.println("<html><body>");
		out.println("<a href=\"deposite.html\"><b>MONEY DEPOSIT</b></a><br>\r\n" + 
				"		<a href=\"withdraw.jsp\"><b>MONEY DEPOSIT</b></a><br>\r\n" + 
				"		<a href=\"transaction.jsp\"><b>TRANSACTION DETAILS</b></a><br>");
		out.println("</html></body>");
	
			
	}

}

