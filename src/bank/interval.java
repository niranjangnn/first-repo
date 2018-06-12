package bank;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class interval
 */
@WebServlet("/interval")
public class interval extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public interval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession=request.getSession(false);
//		Date t1 = (Date) httpSession.getAttribute("createTime");
		LocalTime t3 = (LocalTime) httpSession.getAttribute("logTime");
		httpSession.invalidate();
		HttpSession sess2 = request.getSession(true);
		Date t2 = new Date(sess2.getCreationTime());
		sess2.setAttribute("t2", t2);
		
		LocalTime t4=LocalTime.now();
		System.out.println("~~~~~~~~~~~~~~~~~");
		System.out.println("Create Time:"+t3);
		System.out.println("END Time:"+t4);
//		long interval1=(t2.getTime()-t3.getTime());
//		long interval = TimeUnit.MILLISECONDS.toSeconds(interval1);
		long interval=Duration.between(t3, t4).getSeconds();
		
		sess2.setAttribute("interval", interval);
		
		System.out.println("Interval Time:"+interval);
		RequestDispatcher rd = request.getRequestDispatcher("logout.jsp");

		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
