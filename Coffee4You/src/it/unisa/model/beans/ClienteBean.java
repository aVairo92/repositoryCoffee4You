package it.unisa.model.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteBean implements Serializable {

	private final static long serialVersionUID = 1L;

	// COSTRUTTORI
	/**
	 * Costruttore vuoto per il database.
	 */
	public ClienteBean() {
		this.id = -1;
		this.nome = "";
		this.cognome = "";
		this.username = "";
		this.password = "";
		this.sesso = "D";
		this.email = "";
		this.telefono = "" ;
		this.codiceFiscale ="" ;
		this.listaIndirizzi = new ArrayList<>();
		this.listaCarte = new ArrayList<>();
		this.listaOrdini = new ArrayList<>();
	}

	public ClienteBean(int id, String nome, String cognome, String username, String password, String sesso,
			String email, String telefono,String codiceFiscale , List<IndirizzoSpedizioneBean> indirizzi, List<CartaDiCreditoBean> carte) {

		this.id = id;
		this.cognome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.sesso = sesso;
		this.email = email;
		this.telefono = telefono ;
		this.codiceFiscale = codiceFiscale ;
		this.listaIndirizzi = indirizzi;
		this.listaCarte = carte;
		this.listaOrdini = new ArrayList<>();

	}

	// GETTER E SETTER
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
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the listaIndirizzi
	 */
	public List<IndirizzoSpedizioneBean> getListaIndirizzi() {
		return listaIndirizzi;
	}

	/**
	 * @param listaIndirizzi the listaIndirizzi to set
	 */
	public void setListaIndirizzi(List<IndirizzoSpedizioneBean> listaIndirizzi) {
		this.listaIndirizzi = listaIndirizzi;
	}

	/**
	 * @return the listaCarte
	 */
	public List<CartaDiCreditoBean> getListaCarte() {
		return listaCarte;
	}

	/**
	 * @param listaCarte the listaCarte to set
	 */
	public void setListaCarte(List<CartaDiCreditoBean> listaCarte) {
		this.listaCarte = listaCarte;
	}

	/**
	 * @return the listaProdotti
	 */
	public List<OrdineBean> getListaOrdini() {
		return listaOrdini;
	}

	/**
	 * @param listaProdotti the listaProdotti to set
	 */
	public void setListaOrdini(List<OrdineBean> listaOrdini) {
		this.listaOrdini = listaOrdini;
	}

	// Metodi Ereditati

	@Override
	public String toString() {
		return "ClienteBean [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", username=" + username
				+ ", password=" + password + ", sesso=" + sesso + ", codiceFiscale=" + codiceFiscale + ", email="
				+ email + ", telefono=" + telefono + ", listaIndirizzi=" + listaIndirizzi + ", listaCarte=" + listaCarte
				+ ", listaOrdini=" + listaOrdini + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceFiscale == null) ? 0 : codiceFiscale.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((listaCarte == null) ? 0 : listaCarte.hashCode());
		result = prime * result + ((listaIndirizzi == null) ? 0 : listaIndirizzi.hashCode());
		result = prime * result + ((listaOrdini == null) ? 0 : listaOrdini.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		ClienteBean other = (ClienteBean) obj;
		if (codiceFiscale == null) {
			if (other.codiceFiscale != null)
				return false;
		} else if (!codiceFiscale.equals(other.codiceFiscale))
			return false;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (listaCarte == null) {
			if (other.listaCarte != null)
				return false;
		} else if (!listaCarte.equals(other.listaCarte))
			return false;
		if (listaIndirizzi == null) {
			if (other.listaIndirizzi != null)
				return false;
		} else if (!listaIndirizzi.equals(other.listaIndirizzi))
			return false;
		if (listaOrdini == null) {
			if (other.listaOrdini != null)
				return false;
		} else if (!listaOrdini.equals(other.listaOrdini))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (sesso == null) {
			if (other.sesso != null)
				return false;
		} else if (!sesso.equals(other.sesso))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	// VARIABILI D?ISTANZA
	private int id;
	private String nome;
	private String cognome;
	private String username;
	private String password;
	private String sesso;
	private String codiceFiscale;
	private String email;
	private String telefono;
	private List<IndirizzoSpedizioneBean> listaIndirizzi;
	private List<CartaDiCreditoBean> listaCarte;
	private List<OrdineBean> listaOrdini;

}