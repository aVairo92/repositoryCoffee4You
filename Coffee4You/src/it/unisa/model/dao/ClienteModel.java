package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.beans.ClienteBean;

/**
 * Interfaccia che espone i metodi per gestire un Cliente.
 * Offre i metodi per salvare o eliminare un cliente nel database,
 * ottenere un particolare cliente a partire dall'id o ottenere tutti
 * i clienti nel database.
 * 
 * @author Antony Vairo
 *
 */
public interface ClienteModel {

	public void doSave(ClienteBean cliente) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public ClienteBean doRetrieveByKey(int id) throws SQLException;
	
	public ClienteBean doRetrieveByUsernameAndPassword(String username,String password) throws SQLException;
	
	public ClienteBean doRetrieveByUsername(String username) throws SQLException;
	
	public ClienteBean doRetrieveByEmail(String email) throws SQLException;
	
	public Collection<ClienteBean> doRetrieveAll() throws SQLException;

}
