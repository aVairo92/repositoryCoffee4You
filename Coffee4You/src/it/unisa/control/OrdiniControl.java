package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.AmministratoreBean;
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
 * Servlet implementation class OrdiniControl
 */
@WebServlet("/ordini")
public class OrdiniControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	static OrdineModel model ;
	static ProdottoQuantitaModel pqModel ;
	static ClienteModel clienteModel ;
	
	static {
		model = new OrdineDS() ;
		clienteModel = new ClienteDS() ;
		pqModel = new ProdottoQuantitaDS() ;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdiniControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ClienteBean clienteLoggato = (ClienteBean) request.getSession().getAttribute("clienteLoggato") ;	
		AmministratoreBean adminLoggato = (AmministratoreBean) request.getSession().getAttribute("adminLoggato") ; 
		String action = (String) request.getParameter("action") ;
		
		if(action != null) {
			try {
				int id =Integer.parseInt(request.getParameter("id")) ;
				
				OrdineBean ordine = model.doRetrieveByKey(id) ;
				Collection<ProdottoQuantita> prodottiOrdine = pqModel.retrieveAllByOrdineId(ordine.getId());
				ordine.setProdottiOrdine((List<ProdottoQuantita>) prodottiOrdine) ;

				request.removeAttribute("ordine") ;
				request.setAttribute("ordine", ordine) ;

				RequestDispatcher dispatcher = request.getRequestDispatcher("dettagli-ordine.jsp");
				dispatcher.forward(request,response) ;
				
			} catch(SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}
		} else {
			
			if(clienteLoggato != null ) {
			try {	
			Collection<OrdineBean> ordini = model.doRetrieveAllByClientId(clienteLoggato.getId()) ;
			
			for(OrdineBean ordine : ordini) {
				Collection<ProdottoQuantita> pqOrdine = pqModel.retrieveAllByOrdineId(ordine.getId());
				ordine.setProdottiOrdine((List<ProdottoQuantita>) pqOrdine) ;
			}
						
			request.removeAttribute("ordini") ;
			request.setAttribute("ordini",ordini);
			} catch(SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("ordini-cliente.jsp");
			dispatcher.forward(request,response) ;
		} else if(adminLoggato != null){
			try {
				request.removeAttribute("clienti") ;
				request.setAttribute("clienti", clienteModel.doRetrieveAll());
				
				Collection<OrdineBean> ordini = model.doRetrieveAll() ;
				for(OrdineBean ordine : ordini) {
					Collection<ProdottoQuantita> pqOrdine =  pqModel.retrieveAllByOrdineId(ordine.getId());
					ordine.setProdottiOrdine((List<ProdottoQuantita>) pqOrdine) ;
				}
											
				request.removeAttribute("ordiniDaVisualizzare") ;
				request.setAttribute("ordiniDaVisualizzare",ordini) ;	
			} catch (SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("admin/ordini-admin.jsp");
			dispatcher.forward(request,response) ;
		}else {
			response.sendRedirect("home.jsp");
			}
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AmministratoreBean adminLoggato = (AmministratoreBean) request.getSession().getAttribute("adminLoggato") ;
		
		LocalDate after = LocalDate.of(2023,1,1) ;
		LocalDate before = LocalDate.now() ;
		
		if(!(request.getParameter("after").equals(""))) {
		 after = LocalDate.parse(request.getParameter("after")) ;
		}
		
		if(!(request.getParameter("before").equals(""))) {
			 before = LocalDate.parse(request.getParameter("before")) ;
			}		
		
		String usernameCliente = request.getParameter("usernameCliente") ; 
		ClienteBean clienteScelto = new ClienteBean() ;
		Collection<OrdineBean> ordiniDaVisualizzare = null ;

		
		if(adminLoggato != null) {
			
			try {
				 ordiniDaVisualizzare = (Collection<OrdineBean>) model.doRetrieveAll();
					for(OrdineBean ordine : ordiniDaVisualizzare) {
						Collection<ProdottoQuantita> pqOrdine = pqModel.retrieveAllByOrdineId(ordine.getId());
						ordine.setProdottiOrdine((List<ProdottoQuantita>) pqOrdine) ;
					}
					
				request.removeAttribute("ordiniDaVisualizzare") ;
				request.setAttribute("ordiniDaVisualizzare", ordiniDaVisualizzare) ;
				request.removeAttribute("clienti") ;
				request.setAttribute("clienti", clienteModel.doRetrieveAll());
			} catch (SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}
			
			try {
			  clienteScelto = clienteModel.doRetrieveByUsername(usernameCliente) ;	
			} catch(SQLException e) {
				System.out.println("Errore:"+e.getMessage()) ;
			}	
			
			if(clienteScelto.getId() != -1) {
											
				try {
				 ordiniDaVisualizzare = (Collection<OrdineBean>) model.doRetrieveByDateAndCliente(after, before,clienteScelto.getId());
					for(OrdineBean ordine : ordiniDaVisualizzare) {
						Collection<ProdottoQuantita>  pqOrdine = pqModel.retrieveAllByOrdineId(ordine.getId());
						ordine.setProdottiOrdine((List<ProdottoQuantita>) pqOrdine) ;
					}	
				} catch(SQLException e) {
					System.out.println("Errore:"+e.getMessage()) ;
					}
				
				request.removeAttribute("ordiniDaVisualizzare") ;
				request.setAttribute("ordiniDaVisualizzare", ordiniDaVisualizzare) ;
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("admin/ordini-admin.jsp");
				dispatcher.forward(request,response) ;
				} else {
					
					try {
						ordiniDaVisualizzare = (Collection<OrdineBean>) model.doRetrieveByDate(after, before);
						for(OrdineBean ordine : ordiniDaVisualizzare) {
							Collection<ProdottoQuantita> pqOrdine = pqModel.retrieveAllByOrdineId(ordine.getId());
							ordine.setProdottiOrdine((List<ProdottoQuantita>) pqOrdine) ;
							}
						} catch(SQLException e) {
							System.out.println("Errore:"+e.getMessage()) ;
							}
					
					request.removeAttribute("ordiniDaVisualizzare") ;
					request.setAttribute("ordiniDaVisualizzare", ordiniDaVisualizzare) ;
	
					RequestDispatcher dispatcher = request.getRequestDispatcher("admin/ordini-admin.jsp");
					dispatcher.forward(request,response) ;
					}
			} else {
				response.sendRedirect("home.jsp");
				}
		}
	
}