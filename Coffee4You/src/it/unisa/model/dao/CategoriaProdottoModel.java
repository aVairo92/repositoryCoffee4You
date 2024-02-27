package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.beans.CategoriaProdottoBean;

/**
 * Interfaccia che espone i servizi per interrogare il database.
 * 
 * @author Antony Vairo
 *
 */
public interface CategoriaProdottoModel {

	public int doSave(CategoriaProdottoBean categoria) throws SQLException;

	public boolean doDelete(int id) throws SQLException;

	public CategoriaProdottoBean doRetrieveByKey(int id) throws SQLException;

	public CategoriaProdottoBean doRetrieveByNomeCategoriaProdotto(String nomeCategoria) throws SQLException;

	public Collection<CategoriaProdottoBean> doRetrieveAll() throws SQLException;

}
