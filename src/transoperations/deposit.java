package transoperations;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.bankdoa;

@WebServlet("/deposit")
public class deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accno = Integer.parseInt(request.getParameter("accno"));
		int amount = Integer.parseInt(request.getParameter("amount"));
		bankdoa doa = new bankdoa();

		int x = doa.deposit(accno, amount);

		if (x > 0) {
			PrintWriter out = response.getWriter();
			out.println("Sucessfully deposited");
			RequestDispatcher rd = request.getRequestDispatcher("deposit.jsp");
			rd.forward(request, response);
		}
		else {
			response.sendRedirect("deposit.jsp");
		}
	
		doGet(request, response);
	}

}
