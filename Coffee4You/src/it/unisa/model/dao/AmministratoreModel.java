package it.unisa.model.dao;

import java.sql.SQLException;

import it.unisa.model.beans.AmministratoreBean;

/**
 * Interfaccia che espone i metodi per gestire un Amministratore.
 * 
 * @author Antony Vairo
 *
 */
public interface AmministratoreModel {
	
	public AmministratoreBean doRetrieveByUsernameAndPassword(String username,String password) 
			throws SQLException;

}
