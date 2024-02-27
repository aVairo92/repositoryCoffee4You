package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.ClienteBean;
import it.unisa.model.beans.IndirizzoSpedizioneBean;
import it.unisa.model.dao.ClienteDS;
import it.unisa.model.dao.ClienteModel;
import it.unisa.model.dao.IndirizzoSpedizioneDS;
import it.unisa.model.dao.IndirizzoSpedizioneModel;

/**
 * Servlet implementation class IndirizziClienteControl
 */
@WebServlet("/indirizzi")
public class IndirizziClienteControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Collection<IndirizzoSpedizioneBean> indirizziCliente = new ArrayList<>() ;
		
	static IndirizzoSpedizioneModel indirizzoModel ;
	static ClienteModel clienteModel ;
	
	static {
		indirizzoModel = new IndirizzoSpedizioneDS() ;
		clienteModel = new ClienteDS() ;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndirizziClienteControl() {
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
				indirizziCliente = indirizzoModel.doRetrieveAllByClienteId(clienteLoggato.getId()) ;
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
		}
		
		request.setAttribute("indirizziCliente",indirizziCliente) ;
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/indirizzi.jsp");
		dispatcher.forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = (String) request.getParameter("action") ;
		String check = (String) request.getParameter("check") ;
		
		
		if(action.equalsIgnoreCase("remove")) {
			
			int residenteId = Integer.parseInt(request.getParameter("residenteIdRem")) ;
			String viaRem =(String) request.getParameter("viaRem") ;
			String provinciaRem =(String) request.getParameter("provinciaRem") ;
			String cittaRem =(String) request.getParameter("cittaRem") ;
			String capRem =(String) request.getParameter("capRem") ;
			String nCivicoRem =(String) request.getParameter("nCivicoRem") ;
			String nazioneRem =(String) request.getParameter("nazioneRem") ;
		
			
			try {
				indirizzoModel.doDelete(residenteId,provinciaRem, cittaRem, viaRem, capRem, nCivicoRem, nazioneRem) ;
				indirizziCliente = indirizzoModel.doRetrieveAllByClienteId(residenteId) ;
			} catch(SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
			request.getSession().removeAttribute("indirizziCliente");
			request.getSession().setAttribute("indirizziCliente",indirizziCliente) ;
			response.sendRedirect("indirizzi") ;
			
		} else if(action.equalsIgnoreCase("add")) {

			int residenteId = Integer.parseInt(request.getParameter("residenteId")) ;
			String via =(String) request.getParameter("via") ;
			String provincia =(String) request.getParameter("provincia") ;
			String citta =(String) request.getParameter("citta") ;
			String cap =(String) request.getParameter("cap") ;
			String nCivico =(String) request.getParameter("nCivico") ;
			String nazione =(String) request.getParameter("nazione") ;

			try {
			ClienteBean residente = clienteModel.doRetrieveByKey(residenteId) ; 		
			IndirizzoSpedizioneBean indirizzo = new IndirizzoSpedizioneBean(residente,provincia,citta,via,cap,nCivico,nazione) ;
			
			indirizzoModel.doSave(indirizzo) ;
			indirizziCliente = indirizzoModel.doRetrieveAllByClienteId(residente.getId()) ;
			
			} catch(SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
			
			request.getSession().removeAttribute("indirizziCliente");
			request.getSession().setAttribute("indirizziCliente",indirizziCliente) ;
			
			if(check.equalsIgnoreCase("true")) {
			response.sendRedirect("checkout.jsp") ;
			}
			else { 
			response.sendRedirect("indirizzi") ;
			}
		}
		
	}

}
