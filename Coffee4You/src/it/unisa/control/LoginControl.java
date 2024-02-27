package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.unisa.model.beans.AmministratoreBean;
import it.unisa.model.beans.CartaDiCreditoBean;
import it.unisa.model.beans.ClienteBean;
import it.unisa.model.beans.IndirizzoSpedizioneBean;
import it.unisa.model.dao.AmministratoreDS;
import it.unisa.model.dao.AmministratoreModel;
import it.unisa.model.dao.CartaDiCreditoDS;
import it.unisa.model.dao.CartaDiCreditoModel;
import it.unisa.model.dao.ClienteDS;
import it.unisa.model.dao.ClienteModel;
import it.unisa.model.dao.IndirizzoSpedizioneDS;
import it.unisa.model.dao.IndirizzoSpedizioneModel;

/**
 * Servlet implementation class LoginControl
 */
@WebServlet("/login")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ClienteModel model;
	static IndirizzoSpedizioneModel indirizzoModel;
	static CartaDiCreditoModel cartaModel;
	static AmministratoreModel adminModel;

	static {
		model = new ClienteDS();
		indirizzoModel = new IndirizzoSpedizioneDS();
		cartaModel = new CartaDiCreditoDS();
		adminModel = new AmministratoreDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginControl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ClienteBean clienteLoggato = null;
		AmministratoreBean adminLoggato = null;

		try {
			clienteLoggato = model.doRetrieveByUsernameAndPassword(username, password);
			if (clienteLoggato.getId() != -1) {
				
				HttpSession session = request.getSession(true);
				
				session.setAttribute("isClienteLoggato", true) ;
				session.setAttribute("clienteLoggato", clienteLoggato);
				
				session.setAttribute("isAdminLoggato", false);
				session.setAttribute("adminLoggato", null);

				Collection<IndirizzoSpedizioneBean> indirizziCliente = null;
				Collection<CartaDiCreditoBean> carteCliente = null;

				try {
					indirizziCliente = indirizzoModel.doRetrieveAllByClienteId(clienteLoggato.getId());
					carteCliente = cartaModel.doRetrieveAllByClienteId(clienteLoggato.getId());
				} catch (SQLException e) {
					System.out.println("Errore:" + e.getMessage());
				}

				if (indirizziCliente != null) {
					session.removeAttribute("indirizziCliente");
					session.setAttribute("indirizziCliente", indirizziCliente);
				}

				if (carteCliente != null) {
					session.removeAttribute("carteCliente");
					session.setAttribute("carteCliente", carteCliente);
				}
			}
		} catch (SQLException e) {
			System.out.println("Errore:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());
			response.sendRedirect("/Coffee4You/loginError.jsp");
		}

		try {
			adminLoggato = adminModel.doRetrieveByUsernameAndPassword(username, password);
			if (adminLoggato.getId() != -1) {
				
				HttpSession session = request.getSession(true);
				
				session.setAttribute("adminLoggato", adminLoggato);
				session.setAttribute("isAdminLoggato", true);
				
				session.setAttribute("clienteLoggato", null);
				session.setAttribute("isClienteLoggato", false) ;
			
			}
		} catch (SQLException e) {
			System.out.println("Errore:" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Errore:" + e.getMessage());
			response.sendRedirect("/Coffee4You/loginError.jsp");
		}

		if (clienteLoggato.getId() == -1 && adminLoggato.getId() == -1) {
			response.sendRedirect("/Coffee4You/loginError.jsp");
		} else {
			response.sendRedirect("home.jsp");
		}

	}
}