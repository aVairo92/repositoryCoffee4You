package it.unisa.control.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.CategoriaProdottoBean;
import it.unisa.model.beans.ProdottoBean;
import it.unisa.model.dao.CategoriaProdottoDS;
import it.unisa.model.dao.CategoriaProdottoModel;
import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class AggiungiProdottoAdmin
 */
@WebServlet("/aggiungiProdotto")
public class AggiungiProdottoAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel model;
	static CategoriaProdottoModel categoryModel;

	static {
		model = new ProdottoDS();
		categoryModel = new CategoriaProdottoDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AggiungiProdottoAdmin() {
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

		Boolean modificaAvvenuta = false ;
		CategoriaProdottoBean categoria = new CategoriaProdottoBean();

		try {
			categoria = categoryModel.doRetrieveByKey(Integer.parseInt(request.getParameter("idCategoria")));
		} catch (SQLException e) {
			System.out.println("Errore:" + e.getMessage());
		}

		ProdottoBean prodotto = new ProdottoBean();
		prodotto.setCategoriaId(categoria.getId());
		prodotto.setNomeProdotto(request.getParameter("nomeProdotto"));
		prodotto.setDescrizioneProdotto(request.getParameter("descrizioneProdotto"));
		prodotto.setMarcaProdotto(request.getParameter("marcaProdotto"));
		prodotto.setDisponibilità(Integer.parseInt(request.getParameter("disponibilitaProdotto")));
		prodotto.setPrezzoProdotto(Float.parseFloat(request.getParameter("prezzoProdotto")));
		prodotto.setIvaProdotto(Integer.parseInt(request.getParameter("ivaProdotto")));
		prodotto.setUrlImmagine(request.getParameter("urlProdotto"));

		String promo = request.getParameter("promo");
		if (promo.equalsIgnoreCase("true")) {
			prodotto.setPromo(true);
		} else if (promo.equalsIgnoreCase("false")) {
			prodotto.setPromo(false);
		} else {
			System.out.println("Errore Promo!!!");
		}

		if (!(categoria.getNomeCategoria().equals(""))) {
			try {
				model.doSave(prodotto);
				modificaAvvenuta = true ;
			} catch (SQLException e) {
				System.out.println("Erroree:" + e.getMessage());
			}
		} else {
			try {
				categoria.setNomeCategoria(request.getParameter("nomeCategoria"));
				categoria.setDescrizione(request.getParameter("descrizioneCategoria"));
				int idCategoria = categoryModel.doSave(categoria);
				prodotto.setCategoriaId(idCategoria);
				model.doSave(prodotto);
				modificaAvvenuta = true ;
			} catch (SQLException e) {
				System.out.println("Errore:" + e.getMessage());
			}
		}

		if(modificaAvvenuta){
			RequestDispatcher dispatcher = request.getRequestDispatcher("catalogo");	
			dispatcher.forward(request, response);
			}else {
			response.sendRedirect("modificaCatalogoFailed.jsp");
			}

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
