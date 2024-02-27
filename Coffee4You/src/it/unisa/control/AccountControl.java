package it.unisa.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AccountControl
 */
@WebServlet("/account")
public class AccountControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action") ;
		
		if(action == null) {	
			RequestDispatcher dispatcher = request.getRequestDispatcher("gestione-account.jsp");
			dispatcher.forward(request,response) ;
			} else if(action.equalsIgnoreCase("profilo")){
				RequestDispatcher dispatcher = request.getRequestDispatcher("profilo.jsp");
				dispatcher.forward(request,response) ;
			} else if(action.equalsIgnoreCase("indirizzi")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("indirizzi.jsp");
				dispatcher.forward(request,response) ;
			} else if(action.equalsIgnoreCase("carte")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("gestione-carte.jsp");
				dispatcher.forward(request,response) ;
			}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
