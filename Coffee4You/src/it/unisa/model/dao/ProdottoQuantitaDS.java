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

import it.unisa.model.cart.ProdottoQuantita;

public class ProdottoQuantitaDS implements ProdottoQuantitaModel {

	private static DataSource ds  ;
	static ProdottoModel prodottoModel ;
	
	static {
		prodottoModel = new ProdottoDS() ;
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
	
	private final String TABLE_NAME = "prodotti_ordine" ; 
	
	@Override
	public synchronized void doSave(int ordine_id,ProdottoQuantita prod) 
			throws SQLException {
		
		Connection connection  = null ;
		PreparedStatement ps = null ;
		
		String query = "INSERT INTO "+TABLE_NAME+" (ORDINE_ID,PRODOTTO_ID,NOMEPRODOTTO,QUANTITàPRODOTTO,PREZZOPRODOTTO,IVAPRODOTTO,IMGPRODOTTO) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
		
		try {
			connection = ds.getConnection() ;
			ps = connection.prepareStatement(query) ;
			ps.setInt(1, ordine_id) ;
			ps.setInt(2, prod.getProdotto().getId()) ;
			ps.setString(3, prod.getProdotto().getNomeProdotto());
			ps.setInt(4, prod.getQuantita()) ;
			ps.setFloat(5, prod.getProdotto().getPrezzoProdotto());
			ps.setInt(6, prod.getProdotto().getIvaProdotto());
			ps.setString(7, prod.getProdotto().getUrlImmagine());
			ps.executeUpdate() ;
		} finally {
			try {
				if(ps != null) {
				ps.close();
				}
				} finally {
					if(connection != null) {
					connection.close() ;
				}
			}
		}	
	}
	
	@Override
	public Collection<ProdottoQuantita> retrieveAllByOrdineId(int id) 
			throws SQLException {
	
		Connection connection = null ;
		PreparedStatement ps = null ;
		Collection<ProdottoQuantita> prodottiOrdine = new LinkedList<>() ;
		
		String query = "SELECT * FROM "+TABLE_NAME+" WHERE ORDINE_ID = ?" ;
	
		try {
		connection = ds.getConnection() ;
		ps = connection.prepareStatement(query) ;
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery() ;
		
		while(rs.next()) {
			ProdottoQuantita prod = new ProdottoQuantita(prodottoModel.doRetrieveByKey(rs.getInt("PRODOTTO_ID"))) ;
			prod.setQuantita(rs.getInt("QUANTITàPRODOTTO"));
			prod.setNome(rs.getString("NOMEPRODOTTO")) ;
			prod.setPrezzo(rs.getFloat("PREZZOPRODOTTO"));
			prod.setIva(rs.getInt("IVAPRODOTTO"));
			prod.setImg(rs.getString("IMGPRODOTTO"));
			prodottiOrdine.add(prod) ;
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
	return prodottiOrdine ;
	}
}