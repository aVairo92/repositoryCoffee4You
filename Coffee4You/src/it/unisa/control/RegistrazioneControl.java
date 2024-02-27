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

import com.google.gson.Gson;

import it.unisa.model.beans.ClienteBean;
import it.unisa.model.dao.ClienteDS;
import it.unisa.model.dao.ClienteModel;

/**
 * Servlet implementation class RegistrazioneControl
 */
@WebServlet("/registrazione")
public class RegistrazioneControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ClienteModel model;

	static {
		model = new ClienteDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrazioneControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String check = (String) request.getParameter("check") ;
	    String suggest = (String) request.getParameter("suggest");
		
	    Collection<ClienteBean> clienti = new ArrayList<>() ;
	    ClienteBean clienteCercato = new ClienteBean() ;

	    
		if(check.equalsIgnoreCase("checkUsername")) {
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    
		    		    
		    try {
		    	 clienteCercato = model.doRetrieveByUsername(suggest) ;
		     } catch(SQLException e) {
		    		System.out.println("Error:" + e.getMessage());
		     }
		
		    clienti.add(clienteCercato) ;
		
		if(clienti.size() != 0) {    
		    response.getWriter().write(new Gson().toJson(clienti));
		 	}
		
		} else if(check.equalsIgnoreCase("checkEmail")) {
			
			response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    		    
		    try {
		    	 clienteCercato = model.doRetrieveByEmail(suggest) ;
		     } catch(SQLException e) {
		    		System.out.println("Error:" + e.getMessage());
		     }
		
		    clienti.add(clienteCercato) ;
		
		if(clienti.size() != 0) {    
		    response.getWriter().write(new Gson().toJson(clienti));
		 	}
		
		} else {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/registrazione.jsp");
			dispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ClienteBean nuovoCliente = new ClienteBean();
		nuovoCliente.setNome(request.getParameter(("nome")));
		nuovoCliente.setCognome(request.getParameter("cognome"));
		nuovoCliente.setSesso(request.getParameter("sesso"));
		nuovoCliente.setCodiceFiscale(request.getParameter("codiceFiscale"));
		nuovoCliente.setUsername(request.getParameter("username"));
		nuovoCliente.setPassword(request.getParameter("password"));
		nuovoCliente.setEmail(request.getParameter("email"));
		nuovoCliente.setTelefono(request.getParameter("telefono"));
		System.out.println(nuovoCliente);
		try {
			model.doSave(nuovoCliente);
			response.sendRedirect("home.jsp");
		} catch (SQLException e) {
			System.out.println("Errore:" + e.getMessage());
		}
	}
}