package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.CartaDiCreditoBean;
import it.unisa.model.beans.ClienteBean;
import it.unisa.model.beans.OrdineBean;
import it.unisa.model.cart.Carrello;
import it.unisa.model.cart.ProdottoQuantita;
import it.unisa.model.dao.CartaDiCreditoDS;
import it.unisa.model.dao.CartaDiCreditoModel;
import it.unisa.model.dao.OrdineDS;
import it.unisa.model.dao.OrdineModel;
import it.unisa.model.dao.ProdottoQuantitaDS;
import it.unisa.model.dao.ProdottoQuantitaModel;

/**
 * Servlet implementation class CheckoutControl
 */
@WebServlet("/checkout")
public class CheckoutControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	static OrdineModel model ;
	static ProdottoQuantitaModel prodQuanModel ;
	static CartaDiCreditoModel cartaDiCreditoModel ;
	
	static {
		model = new OrdineDS() ;
		prodQuanModel = new ProdottoQuantitaDS() ;
		cartaDiCreditoModel = new CartaDiCreditoDS() ;
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutControl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		ClienteBean clienteOrdine =
		(ClienteBean) request.getSession().getAttribute("clienteLoggato") ;
			
		if(clienteOrdine == null) {
			response.sendRedirect("log-reg.jsp");
		} else {
			Carrello carrello = (Carrello) request.getSession().getAttribute("carrello") ;
			if(carrello != null) {
				OrdineBean ordine = new OrdineBean() ;
				ordine.setAcquirente(clienteOrdine);
				ordine.setDataEmissione(LocalDate.now());
				ordine.setStatoOrdine(OrdineBean.EMESSO) ;
				ordine.setProdottiOrdine(carrello.getProdotti());
				ordine.setTotaleSpesa(carrello.calcolaTotale());
				ordine.setIndirizzoConsegna(request.getParameter("viaIndirizzo"));
				
				//Aggiornare Il saldo della carta di credito se possibile
				CartaDiCreditoBean cartaUsata = null ;
				try {
					cartaUsata = cartaDiCreditoModel.doRetrieveByCodiceCarta(request.getParameter("codiceCarta")) ;
				} catch(SQLException e) {
					System.out.println("Errore:"+e.getMessage()) ;
				}
				
				if(cartaUsata != null && (cartaUsata.isValid() == true)) {
					if(cartaUsata.getSaldo() >= ordine.getTotaleSpesa()) {
						Float nuovoSaldo = cartaUsata.getSaldo() - ordine.getTotaleSpesa() ;
						try {
							cartaDiCreditoModel.doUpdateSaldo(cartaUsata.getCodiceCarta(), nuovoSaldo) ;
							try {
								ordine.setId(model.doSave(ordine));
								for(ProdottoQuantita p : carrello.getProdotti()) {
									try {
									prodQuanModel.doSave(ordine.getId(),p);	
									} catch(SQLException e) {
										System.out.println("Errore:"+e.getMessage()) ;
									}
								}
							} catch(SQLException e) {
								System.out.println("Errore:"+e.getMessage()) ;
								}

						
						} catch(SQLException e) {
								System.out.println("Errore:"+e.getMessage()) ;
								}
						
						carrello.clearCarrello();
						response.sendRedirect("ordine-effettuato.jsp");
						} else {
							response.sendRedirect("creditCardError.jsp");
						}
					} else {
						response.sendRedirect("creditCardError.jsp");
						}
									
			} else {
					response.sendRedirect("home.jsp");
					}
			}
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
