package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.beans.ProdottoBean;

/**
 * Interfaccia che espone i metodi per gestire il prodotto. Offre i metodi per
 * salvare o eliminare un prodotto nel database; ottenere un particolare
 * prodotto a partire dall'id o ottenere tutti i prodotti nel database.
 * 
 * @author Antony Vairo
 *
 */
public interface ProdottoModel {
	public void doSave(ProdottoBean prodotto) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public void doUpdateDisponibilità(ProdottoBean prodotto, int quantità) throws SQLException;

	public ProdottoBean doRetrieveByKey(int id) throws SQLException;

	public Collection<ProdottoBean> doRetrieveAll() throws SQLException;
	
	public ProdottoBean doRetrieveByNomeProdotto(String nome) throws SQLException ;

	public Collection<ProdottoBean> doRetrieveAllByCategoriaProdotto(int idCategoria) throws SQLException;

	public Collection<ProdottoBean> doRetrieveAllByMarca(String marca) throws SQLException;

	public Collection<ProdottoBean> doRetrieveAllByPromo() throws SQLException;
	
	public Collection<ProdottoBean> doRetrieveAllBySuggest(String suggest) throws SQLException;
	

}