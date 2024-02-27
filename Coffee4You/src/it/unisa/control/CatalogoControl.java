package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
import it.unisa.model.dao.CategoriaProdottoDS;
import it.unisa.model.dao.CategoriaProdottoModel;
import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class CatalogoControl
 */
@WebServlet("/catalogo")
public class CatalogoControl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private List<ProdottoBean> prodInCatalogo = new ArrayList<>();

	static ProdottoModel prodottiModel;
	static CategoriaProdottoModel categorieModel;

	static {
		prodottiModel = new ProdottoDS();
		categorieModel = new CategoriaProdottoDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CatalogoControl() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		
		if(carrello != null) {
			request.setAttribute("carrello",carrello) ;
		}
		
		String action = request.getParameter("action");

		if (action == null) {
			
			try {
				prodInCatalogo = (List<ProdottoBean>) prodottiModel.doRetrieveAll();
				request.removeAttribute("prodotti");
				request.removeAttribute("categorie");
				request.setAttribute("prodotti", prodInCatalogo);
				request.setAttribute("categorie", categorieModel.doRetrieveAll());
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
		} else if (action.equalsIgnoreCase("ordinaPrezzo")) {
			List<ProdottoBean> daOrdinare = prodInCatalogo;
			Collections.sort(daOrdinare);
			request.removeAttribute("prodotti");
			request.setAttribute("prodotti", daOrdinare);
			try {
				request.removeAttribute("categorie");
				request.setAttribute("categorie", categorieModel.doRetrieveAll());
			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}
		} else {
			System.out.println("Errore!!!");
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		List<ProdottoBean> prodottiFiltrati = new ArrayList<>();
		List<ProdottoBean> prodottiFiltratiCat = new ArrayList<>();
		List<ProdottoBean> allProdotti = null;

		try {
			allProdotti = (List<ProdottoBean>) prodottiModel.doRetrieveAll();
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String action = request.getParameter("action");

		if (action != null) {
			if (action.equalsIgnoreCase("addFiltro")) {

				String[] categorieSel = request.getParameterValues("categorieSelezionate");
				String[] marcheSel = request.getParameterValues("marcheSelezionate");
				float filtroPrezzo = Float.parseFloat(request.getParameter("prezzoMax"));

				System.out.println(filtroPrezzo);

				if (filtroPrezzo != 0) {
					Iterator<ProdottoBean> iterator = allProdotti.iterator();
					while (iterator.hasNext()) {
						ProdottoBean b = iterator.next();
						if (b.getPrezzoProdotto() > filtroPrezzo) {
							iterator.remove();
						}
					}
				}

				if (categorieSel != null) {
					for (String s : categorieSel) {
						int categoriaId = -1;
						try {
							categoriaId = categorieModel.doRetrieveByNomeCategoriaProdotto(s).getId();
						} catch (SQLException e) {
							System.out.println("Error:" + e.getMessage());
						}
						for (ProdottoBean p : allProdotti) {
							if (p.getCategoriaId() == categoriaId) {
								prodottiFiltratiCat.add(p);
							}
						}
					}
				}

				if (marcheSel != null) {
					if (categorieSel != null) {
						for (String m : marcheSel) {
							for (ProdottoBean b : prodottiFiltratiCat) {
								if (b.getMarcaProdotto().equalsIgnoreCase(m)) {
									prodottiFiltrati.add(b);
								}
							}
						}
					} else {
						for (String m : marcheSel) {
							for (ProdottoBean b : allProdotti) {
								if (b.getMarcaProdotto().equalsIgnoreCase(m)) {
									prodottiFiltrati.add(b);
								}
							}
						}
					}
				} else {
					prodottiFiltrati = prodottiFiltratiCat;
				}

				if (categorieSel == null && marcheSel == null) {
					prodottiFiltrati = allProdotti;
				}

				try {
					prodInCatalogo = prodottiFiltrati;
					request.removeAttribute("prodotti");
					request.removeAttribute("categorie");
					request.setAttribute("prodotti", prodottiFiltrati);
					request.setAttribute("categorie", categorieModel.doRetrieveAll());
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			} else {
				try {
					prodInCatalogo = allProdotti;
					request.removeAttribute("prodotti");
					request.removeAttribute("categorie");
					request.setAttribute("prodotti", allProdotti);
					request.setAttribute("categorie", categorieModel.doRetrieveAll());
				} catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}

			}
			
			HttpSession session = request.getSession(false);
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			
			if(carrello != null) {
				request.setAttribute("carrello",carrello) ;
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/catalogo.jsp");
			dispatcher.forward(request, response);
		}

	}

}