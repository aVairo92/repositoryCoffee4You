package it.unisa.model.dao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;

import it.unisa.model.beans.OrdineBean;

/**
 * Interfaccia che espone i metodi per gestire un Ordine.
 * Offre i metodi per salvare o eliminare un ordine nel database,
 * ottenere gli ordini di un particolare cliente o un ordine a partire dall'id o ottenere tutti
 * gli ordini nel database.
 * 
 * @author Antony Vairo
 */
public interface OrdineModel {

	public int doSave(OrdineBean ordine) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public OrdineBean doRetrieveByKey(int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveAll() throws SQLException;

	public Collection<OrdineBean> doRetrieveAllByClientId(int id) throws SQLException;
	
	public Collection<OrdineBean> doRetrieveByDate(LocalDate after,LocalDate before) throws SQLException ;

	public Collection<OrdineBean> doRetrieveByDateAndCliente(LocalDate after,LocalDate before,int id) throws SQLException ;
	
}
