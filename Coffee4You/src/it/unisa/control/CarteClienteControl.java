package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.CartaDiCreditoBean;
import it.unisa.model.beans.ClienteBean;
import it.unisa.model.dao.CartaDiCreditoDS;
import it.unisa.model.dao.CartaDiCreditoModel;
import it.unisa.model.dao.ClienteDS;
import it.unisa.model.dao.ClienteModel;

/**
 * Servlet implementation class CarteClienteControl
 */
@WebServlet("/carte")
public class CarteClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
private Collection<CartaDiCreditoBean> carteCliente = new ArrayList<>() ;
	
	static CartaDiCreditoModel carteModel ;
	static ClienteModel clienteModel ;
	
	static {
		carteModel = new CartaDiCreditoDS() ;
		clienteModel = new ClienteDS() ;
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarteClienteControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ClienteBean clienteLoggato =(ClienteBean) request.getSession().getAttribute("clienteLoggato") ;
		
		if(clienteLoggato != null) {
			
			try {
				carteCliente = carteModel.doRetrieveAllByClienteId(clienteLoggato.getId()) ;
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
		}
		
		request.setAttribute("carteCliente",carteCliente) ;
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestione-carte.jsp");
		dispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String action = (String) request.getParameter("action");
		String check = (String) request.getParameter("check") ;
		
		
		if(action.equalsIgnoreCase("remove")) {
			
			int titolareId = Integer.parseInt(request.getParameter("titolareId")) ;
			String codiceCarta = (String)request.getParameter("codiceCarta") ;
		
			try {
				carteModel.doDelete(codiceCarta) ;
				carteCliente = carteModel.doRetrieveAllByClienteId(titolareId);
			} catch(SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
			request.getSession().removeAttribute("carteCliente");
			request.getSession().setAttribute("carteCliente",carteCliente) ;
			response.sendRedirect("carte") ;
			
		} else if(action.equalsIgnoreCase("add")) {

			int titolareId = Integer.parseInt(request.getParameter("titolareId")) ;
			String codiceCarta =(String) request.getParameter("codiceCarta") ;
			String pin = (String) request.getParameter("pin") ;
			LocalDate dataScadenza =  LocalDate.parse(request.getParameter("dataScadenza")) ;
			Float saldo =Float.parseFloat(request.getParameter("saldo")) ;
			
			try {
			ClienteBean titolare = clienteModel.doRetrieveByKey(titolareId) ; 		
			CartaDiCreditoBean carta = new CartaDiCreditoBean(codiceCarta,pin,titolare,dataScadenza,saldo) ;
			
			

			if(!(carteModel.doRetrieveByCodiceCarta(codiceCarta).getCodiceCarta().equalsIgnoreCase(""))) {
				System.out.println("ERRORE CARTA") ;
				response.sendRedirect("creditCardError.jsp") ;
			} else {
			carteModel.doSave(carta) ;
			carteCliente = carteModel.doRetrieveAllByClienteId(titolare.getId()) ; 
			
			request.getSession().removeAttribute("carteCliente");
			request.getSession().setAttribute("carteCliente",carteCliente) ;
			
			if(check.equalsIgnoreCase("true")) {
				response.sendRedirect("checkout.jsp") ;
				
			} else { 
				response.sendRedirect("carte") ;
				}
			}
			
			} catch(SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
				
	}

}
	
}
