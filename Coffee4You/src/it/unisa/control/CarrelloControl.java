package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.beans.ProdottoBean;
import it.unisa.model.cart.Carrello;
import it.unisa.model.cart.ProdottoQuantita;
import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class CarrelloControl
 */
@WebServlet("/carrello")
public class CarrelloControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel prodModel;

	static {
		prodModel = new ProdottoDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarrelloControl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<ProdottoBean> prodPromo = (List<ProdottoBean>) prodModel.doRetrieveAllByPromo();
			request.setAttribute("prodPromo", prodPromo);
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		HttpSession session = request.getSession(true);
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if (carrello == null) {
			carrello = new Carrello();
			session.setAttribute("carrello", carrello);
	
		}

		String action = request.getParameter("action");

		try {
			if (action != null) {
				if (action.equalsIgnoreCase("add")) {
					int id = Integer.parseInt(request.getParameter("id"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));
					ProdottoBean prodotto = prodModel.doRetrieveByKey(id);
					int qntDaAggiornare = prodotto.getDisponibilità() - quantity ;
					prodModel.doUpdateDisponibilità(prodotto,qntDaAggiornare);
					carrello.addProdotto(prodotto,quantity);
					response.sendRedirect("carrello");
					return;
				} else if (action.equalsIgnoreCase("remove")) {
					int id = Integer.parseInt(request.getParameter("id"));
					ProdottoBean prodotto = prodModel.doRetrieveByKey(id);
					int quantity = 0 ;
					for(ProdottoQuantita p:carrello.getProdotti()) {
						if(p.getProdotto().getId() == prodotto.getId()) {
							quantity = p.getQuantita(); 
						}
					}
					prodModel.doUpdateDisponibilità(prodotto, prodotto.getDisponibilità()+quantity);					
					carrello.deleteProdotto(prodotto);
				} else if (action.equalsIgnoreCase("dettagli")) {
					int id = Integer.parseInt(request.getParameter("id"));
					ProdottoBean prodottoDett = prodModel.doRetrieveByKey(id);
					request.removeAttribute("prodotto") ;
					request.setAttribute("prodotto", prodottoDett);
					List<ProdottoBean> prodottiCategoria = (List<ProdottoBean>) prodModel.doRetrieveAllByCategoriaProdotto(prodottoDett.getCategoriaId());
					request.removeAttribute("prodottiCategoria") ;
					request.setAttribute("prodottiCategoria", prodottiCategoria);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dettaglio-prodotto.jsp");
					dispatcher.forward(request, response);
					return ;
				} else if (action.equalsIgnoreCase("clearCarrello")) {
				
					for(ProdottoQuantita p: carrello.getProdotti()) {
						ProdottoBean prodotto = prodModel.doRetrieveByKey(p.getProdotto().getId());
						int quantity = p.getQuantita() ;
	
						prodModel.doUpdateDisponibilità(prodotto, prodotto.getDisponibilità()+quantity);					
					}
					
					carrello.clearCarrello();
				}
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		request.getSession().setAttribute("carrello", carrello);
		request.setAttribute("carrello", carrello);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/carrello.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
