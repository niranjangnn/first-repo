package bank;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bank.bankdoa;


@WebServlet("/operations")
public class operations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public String name;
	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		bankdoa doa = new bankdoa();
			HttpSession session = request.getSession();
			int deposit = 500;
			userutils ut = new userutils();
			ut.name = request.getParameter("name");
			session.setAttribute("name", ut.name);
			ut.bdate = request.getParameter("bdate");
			ut.password = request.getParameter("password");
			ut.actype = request.getParameter("acctype");
			ut.email= request.getParameter("email");

			if (ut.actype.equalsIgnoreCase("current")) {
				deposit = 5000;
			}
			ut.amount = deposit;

			int xyz = doa.registration(ut);
			if (xyz > 0) {
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");

				rd.forward(request, response);

			}

		}

}
