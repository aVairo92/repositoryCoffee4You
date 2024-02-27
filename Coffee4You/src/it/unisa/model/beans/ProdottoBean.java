package it.unisa.model.beans;

import java.io.Serializable;

/**
 * @author Antony Vairo Classe che descrive un Prodotto(@ProdottoBean) con le
 *         informazioni da salvare nel database.
 */
public class ProdottoBean implements Serializable, Comparable<ProdottoBean> {

	private static final long serialVersionUID = 1L;

	// COSTRUTTORI

	/**
	 * Metodo costruttore vuoto per il database.
	 */
	public ProdottoBean() {
		this.id = -1;
		this.nomeProdotto = "";
		this.descrizioneProdotto = "";
		this.categoriaId = -1;
		this.marcaProdotto = "";
		this.disponibilità = 0;
		this.prezzoProdotto = 0.00f;
		this.ivaProdotto = 0;
		this.urlImmagine = "";
		this.promo = false;
	}

	/**
	 * Metodo costruttore che permette di inizializzare un prodotto fornendo il
	 * nome,la descrizione,la categoria,la disponibilit�,il prezzo e l'iva.
	 * 
	 * @param nomeProdotto        Una String che descrive il nome del prodotto.
	 * @param descrizioneProdotto Una String che descrive la descrizione del
	 *                            prodotto.
	 * @param categoriaProdotto   Una String che descrive la categoria del prodotto.
	 * @param disponibilit�       Un int che descrive la disponibilit� del prodotto.
	 * @param prezzoProdotto      Un float che descrive il prezzo del prodotto.
	 * @param ivaProdotto         Un int che descrive l'iva del prodotto.
	 * @param promo               Un boolean che descrive se il prodotto e in promo.
	 */
	public ProdottoBean(String nomeProdotto, String descrizioneProdotto, int categoriaId, String marcaProdotto,
			int disponibilità, float prezzoProdotto, int ivaProdotto, String urlImmagine, boolean promo) {
		this.id = -1;
		this.nomeProdotto = nomeProdotto;
		this.descrizioneProdotto = descrizioneProdotto;
		this.categoriaId = categoriaId;
		this.marcaProdotto = marcaProdotto;
		this.disponibilità = disponibilità;
		this.prezzoProdotto = prezzoProdotto;
		this.ivaProdotto = ivaProdotto;
		this.urlImmagine = urlImmagine;
		this.promo = promo;
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
	 * @return the nomeProdotto
	 */
	public String getNomeProdotto() {
		return nomeProdotto;
	}

	/**
	 * @param nomeProdotto the nomeProdotto to set
	 */
	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	/**
	 * @return the descrizioneProdotto
	 */
	public String getDescrizioneProdotto() {
		return descrizioneProdotto;
	}

	/**
	 * @param descrizioneProdotto the descrizioneProdotto to set
	 */
	public void setDescrizioneProdotto(String descrizioneProdotto) {
		this.descrizioneProdotto = descrizioneProdotto;
	}

	/**
	 * @return the categoriaId
	 */
	public int getCategoriaId() {
		return categoriaId;
	}

	/**
	 * @param categoriaId the categoriaId to set
	 */
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * @return the marcaProdotto
	 */
	public String getMarcaProdotto() {
		return marcaProdotto;
	}

	/**
	 * @param marcaProdotto the marcaProdotto to set
	 */
	public void setMarcaProdotto(String marcaProdotto) {
		this.marcaProdotto = marcaProdotto;
	}

	/**
	 * @return the disponibilit�
	 */
	public int getDisponibilità() {
		return disponibilità;
	}

	/**
	 * @param disponibilit� the disponibilit� to set
	 */
	public void setDisponibilità(int disponibilità) {
		this.disponibilità = disponibilità;
	}

	/**
	 * @return the prezzoProdotto
	 */
	public float getPrezzoProdotto() {
		return prezzoProdotto;
	}

	/**
	 * @param prezzoProdotto the prezzoProdotto to set
	 */
	public void setPrezzoProdotto(float prezzoProdotto) {
		this.prezzoProdotto = prezzoProdotto;
	}

	/**
	 * @return the ivaProdotto
	 */
	public int getIvaProdotto() {
		return ivaProdotto;
	}

	/**
	 * @param ivaProdotto the ivaProdotto to set
	 */
	public void setIvaProdotto(int ivaProdotto) {
		this.ivaProdotto = ivaProdotto;
	}

	/**
	 * @return the categoriaProdotto
	 */
	public String getUrlImmagine() {
		return urlImmagine;
	}

	/**
	 * @param categoriaProdotto the categoriaProdotto to set
	 */
	public void setUrlImmagine(String urlImmagine) {
		this.urlImmagine = urlImmagine;
	}

	/**
	 * @return the promo
	 */
	public boolean getPromo() {
		return promo;
	}

	/**
	 * @param promo the promo to set
	 */
	public void setPromo(boolean promo) {
		this.promo = promo;
	}

	// METODI BUSINESS
	public float calcolaPrezzoIvato() {
		float iva = (this.prezzoProdotto * this.ivaProdotto) / 100;
		return this.prezzoProdotto + iva;
	}

	// METODO COMPARETO
	@Override
	public int compareTo(ProdottoBean o) {

		if (this.prezzoProdotto < o.getPrezzoProdotto())
			return -1;
		else if (this.prezzoProdotto > o.getPrezzoProdotto())
			return +1;

		return 0;
	}

	// METODI EREDITATI toString,equals
	@Override
	public String toString() {
		return "ProdottoBean [id=" + id + ", nomeProdotto=" + nomeProdotto + ", descrizioneProdotto="
				+ descrizioneProdotto + ", marcaProdotto=" + marcaProdotto + ", categoriaId=" + categoriaId
				+ ", disponibilità=" + disponibilità + ", prezzoProdotto=" + prezzoProdotto + ", ivaProdotto="
				+ ivaProdotto + ", urlImmagine=" + urlImmagine + ", promo=" + promo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoriaId;
		result = prime * result + ((descrizioneProdotto == null) ? 0 : descrizioneProdotto.hashCode());
		result = prime * result + disponibilità;
		result = prime * result + id;
		result = prime * result + ivaProdotto;
		result = prime * result + ((marcaProdotto == null) ? 0 : marcaProdotto.hashCode());
		result = prime * result + ((nomeProdotto == null) ? 0 : nomeProdotto.hashCode());
		result = prime * result + Float.floatToIntBits(prezzoProdotto);
		result = prime * result + (promo ? 1231 : 1237);
		result = prime * result + ((urlImmagine == null) ? 0 : urlImmagine.hashCode());
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
		ProdottoBean other = (ProdottoBean) obj;
		if (categoriaId != other.categoriaId)
			return false;
		if (descrizioneProdotto == null) {
			if (other.descrizioneProdotto != null)
				return false;
		} else if (!descrizioneProdotto.equals(other.descrizioneProdotto))
			return false;
		if (disponibilità != other.disponibilità)
			return false;
		if (id != other.id)
			return false;
		if (ivaProdotto != other.ivaProdotto)
			return false;
		if (marcaProdotto == null) {
			if (other.marcaProdotto != null)
				return false;
		} else if (!marcaProdotto.equals(other.marcaProdotto))
			return false;
		if (nomeProdotto == null) {
			if (other.nomeProdotto != null)
				return false;
		} else if (!nomeProdotto.equals(other.nomeProdotto))
			return false;
		if (Float.floatToIntBits(prezzoProdotto) != Float.floatToIntBits(other.prezzoProdotto))
			return false;
		if (promo != other.promo)
			return false;
		if (urlImmagine == null) {
			if (other.urlImmagine != null)
				return false;
		} else if (!urlImmagine.equals(other.urlImmagine))
			return false;
		return true;
	}

	// VARIABILI D'ISTANZA DEL PRODOTTO
	private int id;
	private String nomeProdotto;
	private String descrizioneProdotto;
	private String marcaProdotto;
	private int categoriaId;
	private int disponibilità;
	private float prezzoProdotto;
	private int ivaProdotto;
	private String urlImmagine;
	private boolean promo;

}
