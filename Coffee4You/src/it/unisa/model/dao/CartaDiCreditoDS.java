package it.unisa.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.CartaDiCreditoBean;

/**
 * Classe che implementa i metodi descritti nell'interfaccia
 * @CartaDiCreditoModel.
 * @author Antony Vairo
 *
 */
public class CartaDiCreditoDS implements CartaDiCreditoModel {

	private static DataSource ds ;
	static ClienteModel clienteModel ;
	
	static {
		clienteModel = new ClienteDS() ;
	}
	
	static {
		try {
			Context ctx = new InitialContext() ;
			Context envCtx = (Context) ctx.lookup("java:comp/env") ;
			ds = (DataSource) envCtx.lookup("jdbc/c4y_db") ;
		} catch(NamingException e) {
			System.out.println("Errore:"+e.getMessage()) ;
		}
	}
	
	private final String TABLE_NAME = "cartaDiCredito" ;
	
	@Override
	public synchronized void doSave(CartaDiCreditoBean cartaDiCredito) throws SQLException {		
	
		Connection connection  = null ;
		PreparedStatement ps = null ;

		String query = "INSERT INTO "+TABLE_NAME+" (CODICECARTA,PIN,CLIENTE_ID,DATASCADENZA,SALDO) VALUES (?, ?, ?, ?, ?)" ;
		
		try {
		
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setString(1, cartaDiCredito.getCodiceCarta());
			ps.setString(2, cartaDiCredito.getPin());
			ps.setInt(3, cartaDiCredito.getTitolare().getId());
			ps.setDate(4,Date.valueOf(cartaDiCredito.getDataScadenza()));
			ps.setFloat(5, cartaDiCredito.getSaldo());
			ps.executeUpdate() ;
		
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
	}

	@Override
	public synchronized boolean doUpdateSaldo(String codiceCarta,Float nuovoSaldo) 
			throws SQLException {

		Connection connection  = null ;
		PreparedStatement ps = null ;
	
		int result = 0 ;
		
		String query = "UPDATE "+TABLE_NAME+" SET SALDO = ? WHERE CODICECARTA = ?" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setFloat(1,nuovoSaldo);
			ps.setString(2, codiceCarta);
			result = ps.executeUpdate() ;
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
		return (result != 0);
	}
	
	@Override
	public boolean doDelete(String codiceCarta) throws SQLException {
		
		Connection connection  = null ;
		PreparedStatement ps = null ;
	
		int result = 0 ;
		String query = "DELETE FROM "+TABLE_NAME+" WHERE CODICECARTA = ?" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setString(1,codiceCarta);
			result = ps.executeUpdate() ;
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
		return (result != 0);
	}

	@Override
	public CartaDiCreditoBean doRetrieveByCodiceCarta(String codiceCarta) throws SQLException {
		
		Connection connection  = null ;
		PreparedStatement ps = null ;
		CartaDiCreditoBean carta = new CartaDiCreditoBean() ;
	
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE CODICECARTA = ?" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setString(1, codiceCarta) ;
	
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				carta.setCodiceCarta(rs.getString("CODICECARTA")) ;
				carta.setPin(rs.getString("PIN")) ;
				carta.setTitolare(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				carta.setDataScadenza(rs.getDate("DATASCADENZA").toLocalDate()) ;
				carta.setSaldo(rs.getFloat("SALDO")) ;
			}
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
		return carta;
	}

	@Override
	public Collection<CartaDiCreditoBean> doRetrieveAll() throws SQLException {
		Connection connection  = null ;
		PreparedStatement ps = null ;
	
		Collection<CartaDiCreditoBean> carte = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				CartaDiCreditoBean carta = new CartaDiCreditoBean() ;
				carta.setCodiceCarta(rs.getString("CODICECARTA")) ;
				carta.setPin(rs.getString("PIN")) ;
				carta.setTitolare(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				carta.setDataScadenza(rs.getDate("DATASCADENZA").toLocalDate()) ;
				carta.setSaldo(rs.getFloat("SALDO")) ;
				carte.add(carta) ;
			}
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
		return carte;
	}

	@Override
	public Collection<CartaDiCreditoBean> doRetrieveAllByClienteId(int id) throws SQLException {
		
		Connection connection  = null ;
		PreparedStatement ps = null ;
	
		Collection<CartaDiCreditoBean> carteCliente = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE CLIENTE_ID = ?" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery() ;
			
			while(rs.next()) {
				CartaDiCreditoBean carta = new CartaDiCreditoBean() ;
				carta.setCodiceCarta(rs.getString("CODICECARTA")) ;
				carta.setPin(rs.getString("PIN")) ;
				carta.setTitolare(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				carta.setDataScadenza(rs.getDate("DATASCADENZA").toLocalDate()) ;
				carta.setSaldo(rs.getFloat("SALDO")) ;
				carteCliente.add(carta) ;
			}
		
		} finally {
			try {
				if(ps != null) {
				ps.close() ;
				}
			} finally {
				if(connection != null) {
					connection.close() ;
				}
			}
		}
		
		return carteCliente;
	}
	
}