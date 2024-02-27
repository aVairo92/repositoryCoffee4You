package it.unisa.model.beans;

import java.io.Serializable;

/**
 * @author Antony Vairo Classe che descrive una Categoria di
 *         Prodotto(@ProdottoBean) con le informazioni da salvare nel database.
 */
public class CategoriaProdottoBean implements Serializable {

	private final static long serialVersionUID = 1L;

	// COSTRUTTORI
	/**
	 * Metodo costruttore vuoto per il database.
	 */
	public CategoriaProdottoBean() {
		this.descrizione = "";
		this.nomeCategoria = "";
		this.urlImmagine1 = "";
		this.urlImmagine2 = "";
	}

	/**
	 * Metodo costruttore che permette di inizializzare una categoria di prodotto a
	 * partire dall'id,dalla descrizione e del nome della categoria.
	 * 
	 * @param id            L'int della categoria
	 * @param descrizione   Una stringa che rappresenta la descrizione della
	 *                      categoria
	 * @param nomeCategoria Una stringa che rappresenta il nome della categoria.
	 */
	public CategoriaProdottoBean(int id, String descrizione, String nomeCategoria, String urlImmagine1,
			String urlImmagine2) {

		this.id = id;
		this.descrizione = descrizione;
		this.nomeCategoria = nomeCategoria;
		this.urlImmagine1 = urlImmagine1;
		this.urlImmagine2 = urlImmagine2;
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
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the nomeCategoria
	 */
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	/**
	 * @param nomeCategoria the nomeCategoria to set
	 */
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	/**
	 * @return the urlImmagine1
	 */
	public String getUrlImmagine1() {
		return urlImmagine1;
	}

	/**
	 * @param urlImmagine1 the urlImmagine1 to set
	 */
	public void setUrlImmagine1(String urlImmagine1) {
		this.urlImmagine1 = urlImmagine1;
	}

	/**
	 * @return the urlImmagine2
	 */
	public String getUrlImmagine2() {
		return urlImmagine2;
	}

	/**
	 * @param urlImmagine2 the urlImmagine2 to set
	 */
	public void setUrlImmagine2(String urlImmagine2) {
		this.urlImmagine2 = urlImmagine2;
	}

	// METODI EREDITATI

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + id;
		result = prime * result + ((nomeCategoria == null) ? 0 : nomeCategoria.hashCode());
		result = prime * result + ((urlImmagine1 == null) ? 0 : urlImmagine1.hashCode());
		result = prime * result + ((urlImmagine2 == null) ? 0 : urlImmagine2.hashCode());
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
		CategoriaProdottoBean other = (CategoriaProdottoBean) obj;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (id != other.id)
			return false;
		if (nomeCategoria == null) {
			if (other.nomeCategoria != null)
				return false;
		} else if (!nomeCategoria.equals(other.nomeCategoria))
			return false;
		if (urlImmagine1 == null) {
			if (other.urlImmagine1 != null)
				return false;
		} else if (!urlImmagine1.equals(other.urlImmagine1))
			return false;
		if (urlImmagine2 == null) {
			if (other.urlImmagine2 != null)
				return false;
		} else if (!urlImmagine2.equals(other.urlImmagine2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaProdottoBean [id=" + id + ", descrizione=" + descrizione + ", nomeCategoria=" + nomeCategoria
				+ ", urlImmagine1=" + urlImmagine1 + ", urlImmagine2=" + urlImmagine2 + "]";
	}

	// VARIABILI D'ISTANZA
	private int id;
	private String descrizione;
	private String nomeCategoria;
	private String urlImmagine1;
	private String urlImmagine2;

}