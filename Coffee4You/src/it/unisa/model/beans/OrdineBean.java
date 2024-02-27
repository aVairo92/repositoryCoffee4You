package it.unisa.model.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.unisa.model.cart.ProdottoQuantita;

public class OrdineBean implements Serializable {
	
	private final static long serialVersionUID = 1L ;
	
	public OrdineBean() {
		this.acquirente = null ;
		this.totaleSpesa = 0.0f ;
		this.statoOrdine = 0 ;
		this.dataEmissione = null ;
		this.prodottiOrdine = new ArrayList<>() ;
		this.indirizzoConsegna = "" ;
	}
	
	public OrdineBean(int id, ClienteBean acquirente, Float totaleSpesa, int statoOrdine, LocalDate dataEmissione,List<ProdottoQuantita> prodottiOrdine,String indirizzoConsegna) {
		this.id = id;
		this.acquirente = acquirente;
		this.totaleSpesa = totaleSpesa;
		this.statoOrdine = statoOrdine;
		this.dataEmissione = dataEmissione;
		this.prodottiOrdine = prodottiOrdine ;
		this.indirizzoConsegna = indirizzoConsegna ;
	}
	
	//GETTER E SETTER
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the acquirente
	 */
	public ClienteBean getAcquirente() {
		return acquirente;
	}

	/**
	 * @param acquirente the acquirente to set
	 */
	public void setAcquirente(ClienteBean acquirente) {
		this.acquirente = acquirente;
	}

	/**
	 * @return the totaleSpesa
	 */
	public Float getTotaleSpesa() {
		return totaleSpesa;
	}

	/**
	 * @param totaleSpesa the totaleSpesa to set
	 */
	public void setTotaleSpesa(Float totaleSpesa) {
		this.totaleSpesa = totaleSpesa;
	}

	/**
	 * @return the statoOrdine
	 */
	public int getStatoOrdine() {
		return statoOrdine;
	}

	/**
	 * @param statoOrdine the statoOrdine to set
	 */
	public void setStatoOrdine(int statoOrdine) {
		this.statoOrdine = statoOrdine;
	}

	/**
	 * @return the dataEmissione
	 */
	public LocalDate getDataEmissione() {
		return dataEmissione;
	}

	/**
	 * @param dataEmissione the dataEmissione to set
	 */
	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	/**
	 * @return the prodottiOrdine
	 */
	public List<ProdottoQuantita> getProdottiOrdine() {
		return prodottiOrdine;
	}

	/**
	 * @param prodottiOrdine the prodottiOrdine to set
	 */
	public void setProdottiOrdine(List<ProdottoQuantita> prodottiOrdine) {
		this.prodottiOrdine = prodottiOrdine;
	}
	
	/**
	 * @return the indirizzoConsegna
	 */
	public String getIndirizzoConsegna() {
		return indirizzoConsegna;
	}

	/**
	 * @param indirizzoConsegna the indirizzoConsegna to set
	 */
	public void setIndirizzoConsegna(String indirizzoConsegna) {
		this.indirizzoConsegna = indirizzoConsegna;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acquirente == null) ? 0 : acquirente.hashCode());
		result = prime * result + ((dataEmissione == null) ? 0 : dataEmissione.hashCode());
		result = prime * result + id;
		result = prime * result + ((indirizzoConsegna == null) ? 0 : indirizzoConsegna.hashCode());
		result = prime * result + ((prodottiOrdine == null) ? 0 : prodottiOrdine.hashCode());
		result = prime * result + statoOrdine;
		result = prime * result + ((totaleSpesa == null) ? 0 : totaleSpesa.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "OrdineBean [id=" + id + ", acquirente=" + acquirente + ", totaleSpesa=" + totaleSpesa + ", statoOrdine="
				+ statoOrdine + ", dataEmissione=" + dataEmissione + ", prodottiOrdine=" + prodottiOrdine
				+ ", indirizzoConsegna=" + indirizzoConsegna + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdineBean other = (OrdineBean) obj;
		if (acquirente == null) {
			if (other.acquirente != null)
				return false;
		} else if (!acquirente.equals(other.acquirente))
			return false;
		if (dataEmissione == null) {
			if (other.dataEmissione != null)
				return false;
		} else if (!dataEmissione.equals(other.dataEmissione))
			return false;
		if (id != other.id)
			return false;
		if (indirizzoConsegna == null) {
			if (other.indirizzoConsegna != null)
				return false;
		} else if (!indirizzoConsegna.equals(other.indirizzoConsegna))
			return false;
		if (prodottiOrdine == null) {
			if (other.prodottiOrdine != null)
				return false;
		} else if (!prodottiOrdine.equals(other.prodottiOrdine))
			return false;
		if (statoOrdine != other.statoOrdine)
			return false;
		if (totaleSpesa == null) {
			if (other.totaleSpesa != null)
				return false;
		} else if (!totaleSpesa.equals(other.totaleSpesa))
			return false;
		return true;
	}
	
	//VARIABILI D'ISTANZA
	private int id ;
	private ClienteBean acquirente ;
	private Float totaleSpesa ;
	private int statoOrdine ;
	private LocalDate dataEmissione ;
	private List<ProdottoQuantita> prodottiOrdine ;
	public final static int EMESSO = 1 ;
	private String indirizzoConsegna ;
}
