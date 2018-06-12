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


@WebServlet("/withdraaw")
public class withdraaw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accno = request.getParameter("accno");
		int accnum1=Integer.parseInt(accno);
		String amount = request.getParameter("amount");
		int amount1=Integer.parseInt(amount);
		bankdoa doa = new bankdoa();
		
		int amount2 = doa.withdraw(accnum1, amount1);
		System.out.println("accnum1: " + accnum1);
		System.out.println("accnum2:"+amount1);
		System.out.println("amount withdraw"+(amount2-amount1));
		
		if (amount1>0) {
			PrintWriter out = response.getWriter();
			out.println("Sucessfull withdraw");
			RequestDispatcher rd = request.getRequestDispatcher("withdraw.jsp");

			rd.forward(request, response);
			
		}
		
		else
		{
			response.sendRedirect("withdraw.jsp");
		}
	}
		
	}


