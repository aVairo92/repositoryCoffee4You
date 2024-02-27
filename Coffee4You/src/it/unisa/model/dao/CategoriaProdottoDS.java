package it.unisa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.CategoriaProdottoBean;

/**
 * Classe che implementa i metodi desritti
 * nell'interfaccia @CategoriaProdottoModel.
 * 
 * @author Antony Vairo
 *
 */
public class CategoriaProdottoDS implements CategoriaProdottoModel {

	private static DataSource ds;

	/**
	 * Metodo statico che crea la connessione con il database.
	 */
	static {
		try {
			Context initContext = new InitialContext();
			Context envCtx = (Context) initContext.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/c4y_db");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "categoriaProdotto";

	@Override
	public synchronized int doSave(CategoriaProdottoBean categoria) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		int idCategoria = -1;

		String query = "INSERT INTO " + TABLE_NAME + "(DESCRIZIONE,NOME,URLIMMAGINE1,URLIMMAGINE2)"
				+ "VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query, new String[] { "id" });
			ps.setString(1, categoria.getDescrizione());
			ps.setString(2, categoria.getNomeCategoria());
			ps.setString(3, categoria.getUrlImmagine1());
			ps.setString(4, categoria.getUrlImmagine2());

			if (ps.executeUpdate() > 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					idCategoria = generatedKeys.getInt(1);
				}
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
		return idCategoria;
	}

	@Override
	public synchronized boolean doDelete(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;

		String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try {

			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeUpdate();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized CategoriaProdottoBean doRetrieveByKey(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		CategoriaProdottoBean categoria = new CategoriaProdottoBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				categoria.setId(rs.getInt("ID"));
				categoria.setDescrizione(rs.getString("DESCRIZIONE"));
				categoria.setNomeCategoria((rs.getString("NOME")));
				categoria.setUrlImmagine1(rs.getString("URLIMMAGINE1"));
				categoria.setUrlImmagine2(rs.getString("URLIMMAGINE2"));
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}

		return categoria;
	}

	@Override
	public synchronized CategoriaProdottoBean doRetrieveByNomeCategoriaProdotto(String nomeCategoria)
			throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		CategoriaProdottoBean categoria = new CategoriaProdottoBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, nomeCategoria);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				categoria.setId(rs.getInt("ID"));
				categoria.setDescrizione(rs.getString("DESCRIZIONE"));
				categoria.setNomeCategoria((rs.getString("NOME")));
				categoria.setUrlImmagine1(rs.getString("URLIMMAGINE1"));
				categoria.setUrlImmagine2(rs.getString("URLIMMAGINE2"));
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}

		return categoria;
	}

	@Override
	public synchronized Collection<CategoriaProdottoBean> doRetrieveAll() throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<CategoriaProdottoBean> categorie = new LinkedList<CategoriaProdottoBean>();

		String query = "SELECT * FROM " + TABLE_NAME;

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoriaProdottoBean categoria = new CategoriaProdottoBean();
				categoria.setId(rs.getInt("ID"));
				categoria.setDescrizione(rs.getString("DESCRIZIONE"));
				categoria.setNomeCategoria(rs.getString("NOME"));
				categoria.setUrlImmagine1(rs.getString("URLIMMAGINE1"));
				categoria.setUrlImmagine2(rs.getString("URLIMMAGINE2"));
				categorie.add(categoria);
			}
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
		return categorie;
	}
}