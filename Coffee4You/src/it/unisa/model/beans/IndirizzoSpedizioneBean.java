package it.unisa.model.beans;

import java.io.Serializable;
import java.util.Objects;

public class IndirizzoSpedizioneBean implements Serializable {

	private final static long serialVersionUID = 1L ;
	
	//COSTRUTTORI 
	public IndirizzoSpedizioneBean() {
		
		this.residente = null ;
		this.provincia = "" ;
		this.città = "" ;
		this.via = "" ;
		this.cap = "" ;
		this.nCivico = "" ;
		this.nazione = "" ;
	}
	
	public IndirizzoSpedizioneBean(ClienteBean residente, String provincia, String città, String via, String cap,
			String nCivico,String nazione) {

		this.residente = residente;
		this.provincia = provincia;
		this.città = città;
		this.via = via;
		this.cap = cap;
		this.nCivico = nCivico;
		this.nazione = nazione ;
	}
	
	//GETTER e SETTER
	
	/**
	 * @return the residente
	 */
	public ClienteBean getResidente() {
		return residente;
	}
	/**
	 * @param residente the residente to set
	 */
	public void setResidente(ClienteBean residente) {
		this.residente = residente;
	}
	/**
	 * @return the provincia
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * @param provincia the provincia to set
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * @return the citt�
	 */
	public String getCittà() {
		return città;
	}
	/**
	 * @param citt� the citt� to set
	 */
	public void setCittà(String città) {
		this.città = città;
	}
	/**
	 * @return the via
	 */
	public String getVia() {
		return via;
	}
	/**
	 * @param via the via to set
	 */
	public void setVia(String via) {
		this.via = via;
	}
	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}
	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}
	/**
	 * @return the nCivico
	 */
	public String getnCivico() {
		return nCivico;
	}
	/**
	 * @param nCivico the nCivico to set
	 */
	public void setnCivico(String nCivico) {
		this.nCivico = nCivico;
	}
	
	/**
	 * @return the nazione
	 */
	public String getNazione() {
		return nazione;
	}

	/**
	 * @param nazione the nazione to set
	 */
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	
	//METODI EREDITATI
	
		@Override
	public int hashCode() {
		return Objects.hash(cap, città, nCivico, nazione, provincia, residente, via);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndirizzoSpedizioneBean other = (IndirizzoSpedizioneBean) obj;
		return Objects.equals(cap, other.cap) && Objects.equals(città, other.città)
				&& Objects.equals(nCivico, other.nCivico) && Objects.equals(nazione, other.nazione)
				&& Objects.equals(provincia, other.provincia) && Objects.equals(residente, other.residente)
				&& Objects.equals(via, other.via);
	}
	
		@Override
	public String toString() {
		return "IndirizzoSpedizioneBean [residente=" + residente + ", provincia=" + provincia + ", città=" + città
				+ ", via=" + via + ", cap=" + cap + ", nCivico=" + nCivico + ", nazione=" + nazione + "]";
	}


		//VARIABILI D'ISTANZA
		private ClienteBean residente ;
		private String provincia ;
		private String città ;
		private String via ;
		private String cap ;
		private String nCivico ;	
		private String nazione ;
}