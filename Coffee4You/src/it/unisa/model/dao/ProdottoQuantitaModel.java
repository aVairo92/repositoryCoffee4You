package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.cart.ProdottoQuantita;

/**
 * Interfaccia che espone i metodi per gestire un Cliente.
 * Offre i metodi per salvare o eliminare un cliente nel database,
 * ottenere un particolare cliente a partire dall'id o ottenere tutti
 * i clienti nel database.
 * 
 * @author Antony Vairo
 *
 */
public interface ProdottoQuantitaModel {
	
	public void doSave(int ordine_id,ProdottoQuantita prod) throws SQLException ;
	public Collection<ProdottoQuantita> retrieveAllByOrdineId(int id) throws SQLException ;
	
}
