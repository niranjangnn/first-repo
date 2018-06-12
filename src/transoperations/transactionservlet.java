package transoperations;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.Transaction;
import bank.bankdoa;


@WebServlet("/transactionservlet")
public class transactionservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int accno = Integer.parseInt(request.getParameter("accno"));
		bankdoa dao=new bankdoa();
		ArrayList<Transaction> transactionList = dao.transaction1(accno);
		
		if(transactionList!=null)
		{
				System.out.println(transactionList);
			
			request.setAttribute("transaction", transactionList);
			RequestDispatcher rd = request.getRequestDispatcher("transactonlist.jsp");
			rd.forward(request, response);
		}
		else
		{
			response.sendRedirect("errormsg.jsp");
		}
		
		for(int i=0;i<transactionList.size();i++)
		{
			Transaction tx=(Transaction)transactionList.get(i);
			
			System.out.println(tx.getaccno()+" "+tx.getamount()+" "+tx.getbalance() );
			
			
		}
		
	}
	}


