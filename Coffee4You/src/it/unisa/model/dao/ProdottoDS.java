package it.unisa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.ProdottoBean;

/**
 * Classe che implementa i metodi desritti nell'interfaccia @ProdottoModel.
 * 
 * @author Antony Vairo
 *
 */
public class ProdottoDS implements ProdottoModel {

	private static DataSource ds;

	/**
	 * Metodo statico che crea la connessione con il database.
	 */
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/c4y_db");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "prodotto";

	/**
	 * Metodo che permatte di aggiungere il prodotto come parametro al database.
	 * 
	 * @param prodotto Il ProdottoBean da aggiungere
	 */
	@Override
	public synchronized void doSave(ProdottoBean prodotto) throws SQLException {

		// Dichiariamo connessione e preparedStatement
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		// Creiamo la stringa per la query
		String insertSql = "INSERT INTO " + TABLE_NAME + " "
				+ "(NOME,DESCRIZIONE,CATEGORIA_ID,MARCA,DISPONIBILITà,PREZZO,IVA,URLIMMAGINE,PROMO)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {

			// Settiamo i parametri della stringa
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSql);
			preparedStatement.setString(1, prodotto.getNomeProdotto());
			preparedStatement.setString(2, prodotto.getDescrizioneProdotto());
			preparedStatement.setInt(3, prodotto.getCategoriaId());
			preparedStatement.setString(4, prodotto.getMarcaProdotto());
			preparedStatement.setInt(5, prodotto.getDisponibilità());
			preparedStatement.setFloat(6, prodotto.getPrezzoProdotto());
			preparedStatement.setInt(7, prodotto.getIvaProdotto());
			preparedStatement.setString(8, prodotto.getUrlImmagine());
			preparedStatement.setBoolean(9, prodotto.getPromo());

			// Eseguiamo la query e committiamo
			preparedStatement.executeUpdate();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		}
	}

	/**
	 * 
	 */
	@Override
	public synchronized boolean doDelete(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;

		String deleteSQL = "DELETE FROM " + ProdottoDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(deleteSQL);
			ps.setInt(1, id);
			result = ps.executeUpdate();
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

	/**
	 * 
	 * @param prodotto
	 * @param quantit�
	 * @throws SQLException
	 */
	@Override
	public synchronized void doUpdateDisponibilità(ProdottoBean prodotto, int quantità) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;

		String query = "UPDATE " + TABLE_NAME + " SET DISPONIBILITà = ? WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, quantità);
			ps.setInt(2, prodotto.getId());
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
	}

	@Override
	public synchronized ProdottoBean doRetrieveByKey(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ProdottoBean prodotto = new ProdottoBean();

		String retrieveQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveQuery);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));

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
		return prodotto;
	}
	
	@Override
	public synchronized ProdottoBean doRetrieveByNomeProdotto(String nomeProdotto) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ProdottoBean prodotto = new ProdottoBean();

		String retrieveQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveQuery);
			ps.setString(1, nomeProdotto);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));

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
		return prodotto;
	}

	
	

	@Override
	public synchronized List<ProdottoBean> doRetrieveAll() throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();

		String retrieveAllQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME;

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveAllQuery);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));
				prodotti.add(prodotto);
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
		return (List<ProdottoBean>) prodotti;

	}

	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAllByCategoriaProdotto(int idCategoria) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();

		String retrieveAllQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE CATEGORIA_ID = ?";
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveAllQuery);
			ps.setInt(1, idCategoria);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));
				prodotti.add(prodotto);
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
		return prodotti;

	}

	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAllByMarca(String marca) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();

		String retrieveAllQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE MARCA = ?";
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveAllQuery);
			ps.setString(1, marca);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));
				prodotti.add(prodotto);
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
		return prodotti;

	}

	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAllByPromo() throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();

		String retrieveAllQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE PROMO = ?";
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveAllQuery);
			ps.setBoolean(1, true);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));
				prodotti.add(prodotto);
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
		return prodotti;

	}
	
	@Override
	public synchronized Collection<ProdottoBean> doRetrieveAllBySuggest(String suggest) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ProdottoBean> prodotti = new LinkedList<ProdottoBean>();

		String retrieveAllQuery = "SELECT * FROM " + ProdottoDS.TABLE_NAME + " WHERE NOME LIKE ? ";
		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(retrieveAllQuery);
			ps.setString(1,"%"+suggest+"%");

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ProdottoBean prodotto = new ProdottoBean();
				prodotto.setId(rs.getInt("ID"));
				prodotto.setNomeProdotto(rs.getString("NOME"));
				prodotto.setDescrizioneProdotto(rs.getString("DESCRIZIONE"));
				prodotto.setCategoriaId(rs.getInt("CATEGORIA_ID"));
				prodotto.setMarcaProdotto(rs.getString("MARCA"));
				prodotto.setDisponibilità(rs.getInt("DISPONIBILITà"));
				prodotto.setPrezzoProdotto(rs.getFloat("PREZZO"));
				prodotto.setIvaProdotto(rs.getInt("IVA"));
				prodotto.setUrlImmagine(rs.getString("URLIMMAGINE"));
				prodotto.setPromo(rs.getBoolean("PROMO"));
				prodotti.add(prodotto);
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
		return prodotti;

	}

	
	

}