package it.unisa.model.dao;

import java.sql.SQLException;
import java.util.Collection;

import it.unisa.model.beans.IndirizzoSpedizioneBean;

/**
 * Interfaccia che espone i servizi per interrogare il database.
 * @author Antony Vairo
 *
 */
public interface IndirizzoSpedizioneModel {

	public void doSave(IndirizzoSpedizioneBean indirizzo) throws SQLException ;
	public boolean doDelete(int residenteId,String provincia,String città,String via,String cap,String nCivico,String nazione) throws SQLException ;
	public Collection<IndirizzoSpedizioneBean> doRetrieveAllByClienteId(int clienteId) throws SQLException ;
	public Collection<IndirizzoSpedizioneBean> doRetrieveAll() throws SQLException ;
	
}
