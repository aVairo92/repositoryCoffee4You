package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.beans.CartaDiCreditoBean;

/**
 * Interfaccia che espone i metodi per gestire una Carta di Credito.
 * Offre i metodi per salvare o eliminare una cartaDiCredito nel database,per ottenere una carta a partire dal codice,
 * ottenere le carte di un particolare cliente a partire dall'id o ottenere tutte
 * le carte nel database.
 * 
 * @author Antony Vairo
 */
public interface CartaDiCreditoModel {

	public void doSave(CartaDiCreditoBean cartaDiCredito) throws SQLException;

	public boolean doUpdateSaldo(String codiceCarta,Float nuovoSaldo) throws SQLException ;
	
	public boolean doDelete(String codiceCarta) throws SQLException;

	public CartaDiCreditoBean doRetrieveByCodiceCarta(String codiceCarta) throws SQLException ;
	
	public Collection<CartaDiCreditoBean> doRetrieveAll() throws SQLException;

	public Collection<CartaDiCreditoBean> doRetrieveAllByClienteId(int id) throws SQLException;

}
