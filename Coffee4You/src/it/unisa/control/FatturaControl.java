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

import it.unisa.model.beans.ClienteBean;
import it.unisa.model.beans.OrdineBean;
import it.unisa.model.cart.ProdottoQuantita;
import it.unisa.model.dao.ClienteDS;
import it.unisa.model.dao.ClienteModel;
import it.unisa.model.dao.OrdineDS;
import it.unisa.model.dao.OrdineModel;
import it.unisa.model.dao.ProdottoQuantitaDS;
import it.unisa.model.dao.ProdottoQuantitaModel;

/**
 * Servlet implementation class FatturaControl
 */
@WebServlet("/fattura")
public class FatturaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static OrdineModel ordineModel ;
	static ProdottoQuantitaModel prodQuanModel ;
	static ClienteModel clienteModel ;
	
	static {
		ordineModel = new OrdineDS() ;
		prodQuanModel = new ProdottoQuantitaDS() ;
		clienteModel = new ClienteDS() ;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FatturaControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int idOrdine = -1 ;
		idOrdine = Integer.parseInt(request.getParameter("id")) ;
		
		if(idOrdine != -1) {
			try {
				OrdineBean ordine = ordineModel.doRetrieveByKey(idOrdine) ; 
				ClienteBean cliente = clienteModel.doRetrieveByKey(ordine.getAcquirente().getId()) ;
				ordine.setProdottiOrdine( (List<ProdottoQuantita>) prodQuanModel.retrieveAllByOrdineId(ordine.getId())) ;			
		
			request.removeAttribute("ordineFattura");
			request.removeAttribute("clienteFattura");
			
			request.setAttribute("ordineFattura", ordine);
			request.setAttribute("clienteFattura", cliente);

			} catch (SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("visualizza-fattura.jsp") ;
			dispatcher.forward(request, response);
			
		} else {
			response.sendRedirect("ordineNotFound.jsp");
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
