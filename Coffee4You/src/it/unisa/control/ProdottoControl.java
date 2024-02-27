package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import it.unisa.model.beans.ProdottoBean;
import it.unisa.model.cart.Carrello;
import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class ProdottoControl
 */
@WebServlet("/prodotto")
public class ProdottoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	static ProdottoModel prodottiModel;
	
	static {
		prodottiModel = new ProdottoDS();
	}
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdottoControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		 response.setContentType("application/json");
	     response.setCharacterEncoding("UTF-8");
	     
	     String suggest = (String) request.getParameter("suggest");
	
	     Collection<ProdottoBean> prodottiTrovati = null ;
	     
	     try {
	    	 prodottiTrovati = prodottiModel.doRetrieveAllBySuggest(suggest) ;
	     } catch(SQLException e) {
	    		System.out.println("Error:" + e.getMessage());
	     }
	
	     if(prodottiTrovati != null) {
	    	 response.getWriter().write(new Gson().toJson(prodottiTrovati));
	     }
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = (String) request.getParameter("action") ;
		String nomeProdotto =(String) request.getParameter("nomeRicerca") ;
	
		HttpSession session = request.getSession(true);
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
		}
		
		ProdottoBean prodottoCercato = new ProdottoBean() ;
		
		if(action != null) {
			if(action.equalsIgnoreCase("search")) {

				try {	
					prodottoCercato = prodottiModel.doRetrieveByNomeProdotto(nomeProdotto);
					request.removeAttribute("prodotto") ;
					request.setAttribute("prodotto", prodottoCercato);
					if(prodottoCercato.getId() != -1) {
					List<ProdottoBean> prodottiCategoria = (List<ProdottoBean>) prodottiModel.doRetrieveAllByCategoriaProdotto(prodottoCercato.getCategoriaId());
					request.removeAttribute("prodottiCategoria") ;
					request.setAttribute("prodottiCategoria", prodottiCategoria);
					}
					
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
				
				if(prodottoCercato.getId()!= -1) {
					RequestDispatcher dispatcher = request.getRequestDispatcher("dettaglio-prodotto.jsp") ;
					dispatcher.forward(request, response);
					}
			}
		}
	}

}
