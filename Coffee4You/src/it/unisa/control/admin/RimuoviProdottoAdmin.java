package it.unisa.control.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class RimuoviProdottoAdmin
 */
@WebServlet("/rimuoviProdotto")
public class RimuoviProdottoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static ProdottoModel model ;
	
	static {
		model = new ProdottoDS() ;
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RimuoviProdottoAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("idProdotto")) ;
	
		boolean result = false ;
		
		try {
			result = model.doDelete(id) ;
		} catch(SQLException e) {
			System.out.println("Errore:"+e.getMessage()) ;
		} catch (Exception ex) {
			System.out.println("Errore:"+ex.getMessage()) ;
		}
		
		if(result){
		RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo");	
		dispatcher.forward(request, response);
		}else {
		response.sendRedirect("modificaCatalogoFailed.jsp");
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
