package it.unisa.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CatalogoAdminControl
 */
@WebServlet("/modificaCatalogo")
public class CatalogoAdminControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoAdminControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = (String) request.getParameter("action") ;
		
		if(action != null) {
			if(action.equalsIgnoreCase("aggiungi")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/aggiungi-prodotto.jsp") ;
				dispatcher.forward(request, response);
			} else if(action.equalsIgnoreCase("rimuovi")) {
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/rimuovi-prodotto.jsp") ;
				dispatcher.forward(request, response);
			}
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
