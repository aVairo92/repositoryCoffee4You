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

import it.unisa.model.beans.IndirizzoSpedizioneBean;

/**
 * Classe che implementa i servizi offerti dall'
 * interfaccia @IndirizzoSpedizioneModel.
 * @author Antony Vairo
 *
 */
public class IndirizzoSpedizioneDS implements IndirizzoSpedizioneModel {

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
		} catch (NamingException e) {
			System.out.println("Errore:"+e.getMessage()) ;
		}
	}
	
	private static final String TABLE_NAME = "indirizzoSpedizione" ;
	
	@Override
	public synchronized void doSave(IndirizzoSpedizioneBean indirizzo) throws SQLException {
		
		Connection connection = null ;
		PreparedStatement ps = null ;
		
		String query = "INSERT INTO "+TABLE_NAME+" (CLIENTE_ID,PROVINCIA,CITTà,VIA,CAP,NCIVICO,NAZIONE) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
		
		try {
			connection = (Connection) ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
	
			ps.setInt(1, indirizzo.getResidente().getId());
			ps.setString(2, indirizzo.getProvincia());
			ps.setString(3, indirizzo.getCittà());
			ps.setString(4, indirizzo.getVia());
			ps.setString(5, indirizzo.getCap());
			ps.setString(6, indirizzo.getnCivico());
			ps.setString(7, indirizzo.getNazione());
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
	public boolean doDelete(int residenteId,String provincia, String città, String via, String cap, String nCivico, String nazione)
			throws SQLException {

		Connection connection = null ;
		PreparedStatement ps = null ;
		int result = 0 ;
		
		
		String query = "DELETE FROM "+TABLE_NAME+" WHERE (CLIENTE_ID = ?) AND (PROVINCIA = ?) AND (CITTà = ?) AND (VIA = ?) AND (CAP = ?) AND (NCIVICO = ?) AND (NAZIONE = ?)" ;
		
		try {
			connection = (Connection) ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1, residenteId);
			ps.setString(2, provincia);
			ps.setString(3, città);
			ps.setString(4, via);
			ps.setString(5, cap);
			ps.setString(6, nCivico);
			ps.setString(7, nazione);
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
		
		return (result!=0);
	}

	@Override
	public Collection<IndirizzoSpedizioneBean> doRetrieveAllByClienteId(int clienteId) throws SQLException {
	
		Connection connection = null ;
		PreparedStatement ps = null ;
		Collection<IndirizzoSpedizioneBean> indirizziCliente  = new LinkedList<>() ;
		
		String query  = "SELECT * FROM "+TABLE_NAME+" WHERE (CLIENTE_ID = ?)" ;
		
		try {
			connection = (Connection) ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1, clienteId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {	
				IndirizzoSpedizioneBean indirizzo = new IndirizzoSpedizioneBean() ;
				indirizzo.setResidente(clienteModel.doRetrieveByKey(clienteId)) ;
				indirizzo.setProvincia(rs.getString("PROVINCIA"));
				indirizzo.setCittà(rs.getString("CITTà"));
				indirizzo.setVia(rs.getString("VIA"));
				indirizzo.setCap(rs.getString("CAP"));
				indirizzo.setnCivico(rs.getString("NCIVICO"));
				indirizzo.setNazione(rs.getString("NAZIONE"));
				indirizziCliente.add(indirizzo) ;
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
		
		return indirizziCliente;
	}

	@Override
	public Collection<IndirizzoSpedizioneBean> doRetrieveAll() throws SQLException {
		
		Connection connection = null ;
		PreparedStatement ps = null ;
		Collection<IndirizzoSpedizioneBean> indirizzi  = new LinkedList<>() ;
		
		String query  = "SELECT * FROM "+TABLE_NAME;
		
		try {
			connection = (Connection) ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {	
				IndirizzoSpedizioneBean indirizzo = new IndirizzoSpedizioneBean() ;
				indirizzo.setResidente(clienteModel.doRetrieveByKey(rs.getInt("CLIENTE_ID"))) ;
				indirizzo.setProvincia(rs.getString("PROVINCIA"));
				indirizzo.setCittà(rs.getString("CITTà"));
				indirizzo.setCap(rs.getString("CAP"));
				indirizzo.setnCivico(rs.getString("NCIVICO"));
				indirizzo.setNazione(rs.getString("NAZIONE"));
				indirizzi.add(indirizzo) ;
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
		return indirizzi;
	}
}
