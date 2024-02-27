package it.unisa.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.beans.CategoriaProdottoBean;
import it.unisa.model.dao.CategoriaProdottoDS;
import it.unisa.model.dao.CategoriaProdottoModel;
import it.unisa.model.dao.ProdottoDS;
import it.unisa.model.dao.ProdottoModel;

/**
 * Servlet implementation class CategoriaControl
 */
@WebServlet("/categoria")
public class CategoriaControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static ProdottoModel prodottiModel;
	static CategoriaProdottoModel categorieModel;

	static {
		prodottiModel = new ProdottoDS();
		categorieModel = new CategoriaProdottoDS();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaControl() {
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

		String nomeCategoria = null;

		nomeCategoria = request.getParameter("nomeCategoria");

		if (nomeCategoria != null) {

			try {
				CategoriaProdottoBean categoria = categorieModel.doRetrieveByNomeCategoriaProdotto(nomeCategoria);

				if (categoria.getId() != -1) {
					request.removeAttribute("categoria");
					request.removeAttribute("prodottiCategoria");
					request.setAttribute("categoria", categoria);
					request.setAttribute("prodottiCategoria",
							prodottiModel.doRetrieveAllByCategoriaProdotto(categoria.getId()));
				} else {
					response.sendRedirect("error/mostraCategoriaFailed.jsp");
				}

			} catch (SQLException e) {
				System.out.println("Error:" + e.getMessage());
			}

		} else {
			response.sendRedirect("error/mostraCategoriaFailed.jsp");
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/categoria.jsp");
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
