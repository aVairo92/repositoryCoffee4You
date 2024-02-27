package it.unisa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.AmministratoreBean;

public class AmministratoreDS implements AmministratoreModel {

	private static DataSource ds;

	static {
		try {
			Context ctx = new InitialContext();
			Context envCtx = (Context) ctx.lookup("java:comp/env");
			ds = (DataSource) envCtx.lookup("jdbc/c4y_db");
		} catch (NamingException e) {
			System.out.println("Errore:" + e.getMessage());
		}
	}

	private final String TABLE_NAME = "amministratore";

	@Override
	public AmministratoreBean doRetrieveByUsernameAndPassword(String username, String password) throws SQLException {

		Connection connection = null;
		PreparedStatement ps = null;
		AmministratoreBean admin = new AmministratoreBean();

		String query = "SELECT * FROM " + TABLE_NAME + " WHERE (USERNAME = ? AND PASSWORD = ?)";

		try {

			connection = ds.getConnection();
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				admin.setId(rs.getInt("ID"));
				admin.setNome(rs.getString("NOME"));
				admin.setCognome(rs.getString("COGNOME"));
				admin.setUsername(rs.getString("USERNAME"));
				admin.setPassword(rs.getString("PASSWORD"));
				admin.setSesso(rs.getString("SESSO"));
				admin.setEmail(rs.getString("EMAIL"));
				admin.setTelefono(rs.getString("TELEFONO"));
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
		return admin;
	}

}