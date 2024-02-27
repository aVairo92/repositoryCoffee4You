package it.unisa.model.beans;

import java.io.Serializable;
import java.time.LocalDate;

public class CartaDiCreditoBean implements Serializable {
	
	private final static long serialVersionUID = 1L ;
	
	public CartaDiCreditoBean() {
		this.codiceCarta = "" ;
		this.pin = "" ;
		this.titolare = null ;
		this.dataScadenza = null ;
		this.saldo = 0.0f ;
	}
	
	public CartaDiCreditoBean(String codiceCarta, String pin, ClienteBean titolare, LocalDate dataScadenza, Float saldo) {
		this.codiceCarta = codiceCarta;
		this.pin = pin;
		this.titolare = titolare;
		this.dataScadenza = dataScadenza;
		this.saldo = saldo;
	}

	//GETTER E SETTER
	/**
	 * @return the codiceCarta
	 */
	public String getCodiceCarta() {
		return codiceCarta;
	}
	/**
	 * @param codiceCarta the codiceCarta to set
	 */
	public void setCodiceCarta(String codiceCarta) {
		this.codiceCarta = codiceCarta;
	}
	/**
	 * @return the pin
	 */
	public String getPin() {
		return pin;
	}
	/**
	 * @param pin the pin to set
	 */
	public void setPin(String pin) {
		this.pin = pin;
	}
	/**
	 * @return the titolare
	 */
	public ClienteBean getTitolare() {
		return titolare;
	}
	/**
	 * @param titolare the titolare to set
	 */
	public void setTitolare(ClienteBean titolare) {
		this.titolare = titolare;
	}
	/**
	 * @return the dataScadenza
	 */
	public LocalDate getDataScadenza() {
		return dataScadenza;
	}
	/**
	 * @param dataScadenza the dataScadenza to set
	 */
	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}
	/**
	 * @return the saldo
	 */
	public Float getSaldo() {
		return saldo;
	}
	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	//METODI BUSINESS
	public boolean isValid() {
		
		boolean valid = false ;
		
		LocalDate today = LocalDate.now() ;
		
		//Se la data di scadenza è posteriore alla data odierna,la carta è valida
		if(this.getDataScadenza().isAfter(today)) {
			valid = true ;
		}
		
		return valid ;
	}
	
	//METODI EREDITATI
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceCarta == null) ? 0 : codiceCarta.hashCode());
		result = prime * result + ((dataScadenza == null) ? 0 : dataScadenza.hashCode());
		result = prime * result + ((pin == null) ? 0 : pin.hashCode());
		result = prime * result + ((saldo == null) ? 0 : saldo.hashCode());
		result = prime * result + ((titolare == null) ? 0 : titolare.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartaDiCreditoBean other = (CartaDiCreditoBean) obj;
		if (codiceCarta == null) {
			if (other.codiceCarta != null)
				return false;
		} else if (!codiceCarta.equals(other.codiceCarta))
			return false;
		if (dataScadenza == null) {
			if (other.dataScadenza != null)
				return false;
		} else if (!dataScadenza.equals(other.dataScadenza))
			return false;
		if (pin == null) {
			if (other.pin != null)
				return false;
		} else if (!pin.equals(other.pin))
			return false;
		if (saldo == null) {
			if (other.saldo != null)
				return false;
		} else if (!saldo.equals(other.saldo))
			return false;
		if (titolare == null) {
			if (other.titolare != null)
				return false;
		} else if (!titolare.equals(other.titolare))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CartaDiCreditoBean [codiceCarta=" + codiceCarta + ", pin=" + pin + ", titolare=" + titolare
				+ ", dataScadenza=" + dataScadenza + ", saldo=" + saldo + "]";
	}

	//VARIABILI D'ISTANZA
	private String codiceCarta ;
	private String pin ;
	private ClienteBean titolare ;
	private LocalDate dataScadenza ;
	private Float saldo ;
	
}
