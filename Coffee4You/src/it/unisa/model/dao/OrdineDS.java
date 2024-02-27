package it.unisa.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import it.unisa.model.beans.OrdineBean;

/**
 * Classe che implementa i metodi descritti nell'interfaccia
 * @OrdineModel.
 * @author Antony Vairo
 *
 */
public class OrdineDS implements OrdineModel {

	private static DataSource ds ;
	static ClienteModel clienteModel ;
	
	static {
		clienteModel = new ClienteDS() ;
	}

	static {
		try {
		Context context = new InitialContext() ;
		Context envCtx =(Context) context.lookup("java:comp/env") ;
		ds = (DataSource) envCtx.lookup("jdbc/c4y_db") ;
		} catch (NamingException e) {
			System.out.println("Error:"+e.getMessage()) ;
		}
	}
	
	private final String TABLE_NAME = "ordine" ; 
	
	@Override
	public synchronized int doSave(OrdineBean ordine) throws SQLException {
	
		Connection connection  = null ;
		PreparedStatement ps = null ;
		int idOrdine = 0 ;
		String query = "INSERT INTO "+TABLE_NAME+" (CLIENTE_ID,TOTALESPESA,STATOORDINE,DATAEMISSIONE,INDIRIZZOCONSEGNA) VALUES (?, ?, ?, ?, ?)" ;
		try {

			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query,new String[] {"id"}) ;
			ps.setInt(1, ordine.getAcquirente().getId());
			ps.setFloat(2, ordine.getTotaleSpesa());
			ps.setInt(3, ordine.getStatoOrdine());
			ps.setDate(4,Date.valueOf(ordine.getDataEmissione()));
			ps.setString(5, ordine.getIndirizzoConsegna());
		if(ps.executeUpdate() > 0) {
			ResultSet generatedKeys = ps.getGeneratedKeys() ;
			if(generatedKeys.next()) {
				idOrdine = generatedKeys.getInt(1) ;
			}
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
	return idOrdine ;
	}

	@Override
	public boolean doDelete(int id) throws SQLException {

		Connection connection  = null ;
		PreparedStatement ps = null ;
		int result = 0 ;
		
		String query = "DELETE FROM "+TABLE_NAME+" WHERE ID = ?" ;
		
		try {	
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1,id);
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
		return (result != 0) ;
	}

	@Override
	public OrdineBean doRetrieveByKey(int id) throws SQLException {

		Connection connection  = null ;
		PreparedStatement ps = null ;
		OrdineBean ordine = new OrdineBean() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE ID = ?" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				ordine.setId(rs.getInt("ID"));
				ordine.setAcquirente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				ordine.setTotaleSpesa(rs.getFloat("TOTALESPESA"));
				ordine.setStatoOrdine(rs.getInt("STATOORDINE")) ;
				ordine.setDataEmissione(rs.getDate("DATAEMISSIONE").toLocalDate());
				ordine.setIndirizzoConsegna(rs.getString("INDIRIZZOCONSEGNA"));
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
	return ordine ;
	}

	@Override
	public Collection<OrdineBean> doRetrieveAll() 
			throws SQLException {
	

		Connection connection  = null ;
		PreparedStatement ps = null ;
		Collection<OrdineBean> ordini = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME ;
		
		try {
		connection = ds.getConnection() ;
		ps = connection.prepareStatement(query) ;
		ResultSet rs = ps.executeQuery() ;
		while(rs.next()) {
			OrdineBean ordine = new OrdineBean() ;
			ordine.setId(rs.getInt("ID"));
			ordine.setAcquirente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
			ordine.setTotaleSpesa(rs.getFloat("TOTALESPESA"));
			ordine.setStatoOrdine(rs.getInt("STATOORDINE")) ;
			ordine.setDataEmissione(rs.getDate("DATAEMISSIONE").toLocalDate());
			ordine.setIndirizzoConsegna(rs.getString("INDIRIZZOCONSEGNA"));
			ordini.add(ordine) ;
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
		return ordini ;
	}

	@Override
	public Collection<OrdineBean> doRetrieveAllByClientId(int id) 
			throws SQLException {

		Connection connection  = null ;
		PreparedStatement ps = null ;
		
	Collection<OrdineBean> ordini = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE CLIENTE_ID = ?" ;
		
		try {
		connection = ds.getConnection() ;
		ps = connection.prepareStatement(query) ;
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery() ;
		while(rs.next()) {
			OrdineBean ordine = new OrdineBean() ;
			ordine.setId(rs.getInt("ID"));
			ordine.setAcquirente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
			ordine.setTotaleSpesa(rs.getFloat("TOTALESPESA"));
			ordine.setStatoOrdine(rs.getInt("STATOORDINE")) ;
			ordine.setDataEmissione(rs.getDate("DATAEMISSIONE").toLocalDate());
			ordine.setIndirizzoConsegna(rs.getString("INDIRIZZOCONSEGNA"));
			ordini.add(ordine) ;
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
	return ordini ;
	}

	@Override
	public Collection<OrdineBean> doRetrieveByDate(LocalDate after,LocalDate before) 
			throws SQLException {
		
		Connection connection = null ;
		PreparedStatement ps = null ;
		
		Collection<OrdineBean> ordiniData = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE (DATAEMISSIONE BETWEEN ? AND ?) ORDER BY DATAEMISSIONE DESC" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setDate(1, Date.valueOf(after));
			ps.setDate(2, Date.valueOf(before));
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				OrdineBean ordine = new OrdineBean() ;
				ordine.setId(rs.getInt("ID"));
				ordine.setAcquirente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				ordine.setTotaleSpesa(rs.getFloat("TOTALESPESA"));
				ordine.setStatoOrdine(rs.getInt("STATOORDINE")) ;
				ordine.setDataEmissione(rs.getDate("DATAEMISSIONE").toLocalDate());
				ordine.setIndirizzoConsegna(rs.getString("INDIRIZZOCONSEGNA"));
				ordiniData.add(ordine) ;
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
		return ordiniData ;	
	}

	@Override
	public Collection<OrdineBean> doRetrieveByDateAndCliente(LocalDate after,LocalDate before,int id) 
			throws SQLException {
		
		Connection connection = null ;
		PreparedStatement ps = null ;
		
		Collection<OrdineBean> ordiniDataCliente = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE ((DATAEMISSIONE BETWEEN ? AND ?) AND CLIENTE_ID = ?) ORDER BY DATAEMISSIONE DESC" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setDate(1, Date.valueOf(after));
			ps.setDate(2, Date.valueOf(before));
			ps.setInt(3, id);
			ResultSet rs = ps.executeQuery() ;
			while(rs.next()) {
				OrdineBean ordine = new OrdineBean() ;
				ordine.setId(rs.getInt("ID"));
				ordine.setAcquirente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				ordine.setTotaleSpesa(rs.getFloat("TOTALESPESA"));
				ordine.setStatoOrdine(rs.getInt("STATOORDINE")) ;
				ordine.setDataEmissione(rs.getDate("DATAEMISSIONE").toLocalDate());
				ordine.setIndirizzoConsegna(rs.getString("INDIRIZZOCONSEGNA"));
				ordiniDataCliente.add(ordine) ;
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
		return ordiniDataCliente ;	
	}
	
}