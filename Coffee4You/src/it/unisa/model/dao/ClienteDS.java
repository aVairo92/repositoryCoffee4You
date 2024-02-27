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

import it.unisa.model.beans.ClienteBean;

/**
 * Classe che implementa i servizi offerti dall'interfaccia @ClienteModel.
 * 
 * @author Antony Vairo
 *
 */
public class ClienteDS implements ClienteModel {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/c4y_db");
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}

	private static final String TABLE_NAME = "cliente";

	@Override
	public synchronized void doSave(ClienteBean cliente) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;

		String query = "INSERT INTO " + TABLE_NAME
				+ " (NOME,COGNOME,USERNAME,PASSWORD,SESSO,CODICEFISCALE,EMAIL,TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getCognome());
			ps.setString(3, cliente.getUsername());
			ps.setString(4, cliente.getPassword());
			ps.setString(5, cliente.getSesso());
			ps.setString(6, cliente.getCodiceFiscale());
			ps.setString(7, cliente.getEmail());
			ps.setString(8, cliente.getTelefono());
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
	public boolean doDelete(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;

		String query = "DELETE FROM " + TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
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

	@Override
	public ClienteBean doRetrieveByKey(int id) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ClienteBean cliente = new ClienteBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE ID = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setPassword(rs.getString("PASSWORD"));
				cliente.setSesso(rs.getString("SESSO"));
				cliente.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefono(rs.getString("TELEFONO"));
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

		return cliente;
	}

	@Override
	public ClienteBean doRetrieveByUsernameAndPassword(String username, String password) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ClienteBean cliente = new ClienteBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE (USERNAME = ? AND PASSWORD = ?)";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setPassword(rs.getString("PASSWORD"));
				cliente.setSesso(rs.getString("SESSO"));
				cliente.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefono(rs.getString("TELEFONO"));
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

		return cliente;
	}

	@Override
	public ClienteBean doRetrieveByUsername(String username) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ClienteBean cliente = new ClienteBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE USERNAME = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setPassword(rs.getString("PASSWORD"));
				cliente.setSesso(rs.getString("SESSO"));
				cliente.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefono(rs.getString("TELEFONO"));
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

		return cliente;
	}
	
	@Override
	public ClienteBean doRetrieveByEmail(String email) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		ClienteBean cliente = new ClienteBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE EMAIL = ?";

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setPassword(rs.getString("PASSWORD"));
				cliente.setSesso(rs.getString("SESSO"));
				cliente.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefono(rs.getString("TELEFONO"));
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

		return cliente;
	}


	@Override
	public Collection<ClienteBean> doRetrieveAll() throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		Collection<ClienteBean> clienti = new LinkedList<ClienteBean>();

		String query = "SELECT * FROM " + TABLE_NAME;

		try {
			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ClienteBean cliente = new ClienteBean();
				cliente.setId(rs.getInt("ID"));
				cliente.setNome(rs.getString("NOME"));
				cliente.setCognome(rs.getString("COGNOME"));
				cliente.setUsername(rs.getString("USERNAME"));
				cliente.setPassword(rs.getString("PASSWORD"));
				cliente.setSesso(rs.getString("SESSO"));
				cliente.setCodiceFiscale(rs.getString("CODICEFISCALE"));
				cliente.setEmail(rs.getString("EMAIL"));
				cliente.setTelefono(rs.getString("TELEFONO"));
				clienti.add(cliente);
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
		return clienti;
	}
}