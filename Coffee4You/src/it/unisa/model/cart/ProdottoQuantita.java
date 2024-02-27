package it.unisa.model.cart;

import it.unisa.model.beans.ProdottoBean;

/**
 * Classe che descrive un prodotto nel carrello,specificando il prodotto
 * e la relativa quantit�.
 * @author Antony Vairo
 *
 */
public class ProdottoQuantita {
	
	//VARIABILI D'ISTANZA
	private ProdottoBean prodotto ;
	private int quantita ;
	private String nome ;
	private float prezzo ;
	private int iva ;
	private String img ;
	
	//COSTRUTTORI
	/**
	 * Costruttire che inizializza un ProdottoNelCarrello 
	 * settando la quantit� ad 1.
	 * @param prodotto Il Prodotto da aggiungere nel carrello.
	 */
	public ProdottoQuantita(ProdottoBean prodotto) {
		this.prodotto = prodotto ;
		this.quantita = 1 ;
		this.nome = prodotto.getNomeProdotto() ;
		this.prezzo = prodotto.getPrezzoProdotto() ;
		this.iva = prodotto.getIvaProdotto() ;
		this.img = prodotto.getUrlImmagine() ;
	}
	
	/**
	 * Costruttore che inizializza un ProdottoNelCarrello 
	 * settando la quantit� a quella fornita come parametro.
	 * 
	 * @param prodotto Il Prodotto da aggiungere nel carrello.
	 * @param quantit� Int che descrive la quantit�
	 */
	public ProdottoQuantita(ProdottoBean prodotto,int quantità) {
		this.prodotto = prodotto ;
		this.quantita = quantità ;
		this.nome = prodotto.getNomeProdotto() ;
		this.prezzo = prodotto.getPrezzoProdotto() ;
		this.iva = prodotto.getIvaProdotto() ;
		this.img = prodotto.getUrlImmagine() ;
	}
	
	/**
	 * @return the prodotto
	 */
	public ProdottoBean getProdotto() {
		return prodotto;
	}
	
	/**
	 * @param prodotto the prodotto to set
	 */
	public void setProdotto(ProdottoBean prodotto) {
		this.prodotto = prodotto;
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
	 * @return the quantit�
	 */
	public int getQuantita() {
		return quantita;
	}
	/**
	 * @param quantit� the quantit� to set
	 */
	public void setQuantita(int quantità) {
		this.quantita = quantità;
	}

	public float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}

	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
	}
	
	public String getImg() {
		return img ;
	}
	
	public void setImg(String img) {
		this.img = img ;
	}
	

}
