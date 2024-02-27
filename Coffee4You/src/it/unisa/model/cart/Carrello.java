package it.unisa.model.cart;

import java.util.ArrayList;
import java.util.List;

import it.unisa.model.beans.ProdottoBean;

/**
 * Classe che descrive un oggetto Carrello dove vengono salvati i prodotti
 * che un @Utente intende acquistare.
 * @author Antony Vairo
 *
 */
public class Carrello {
	
	//VARIABILE D'ISTANZA
	private List<ProdottoQuantita> prodotti ;
	
	//COSTRUTTORE
	public Carrello() {
		prodotti = new ArrayList<>() ;
	}
	
	//METODI BUSINESS
	/**
	 * Metodo che permette di aggiungere un prodotto nel carrello.
	 * @param prodotto Il prodotto da aggiungere.
	 */
	public void addProdotto(ProdottoBean prodotto,int quantità) {
		
		boolean presente = false ;
		
		for(ProdottoQuantita p : prodotti) {
			if(p.getProdotto().getId() == prodotto.getId()) {
				p.setQuantita(p.getQuantita() + quantità) ;
				presente = true ;
			}
		}
		if(presente == false) {
			ProdottoQuantita prod = new ProdottoQuantita(prodotto,quantità) ;
			prodotti.add(prod) ;
		}		
	}
	
	/**
	 * Metodo che permette di rimuovere un prodotto dal carrello.
	 * @param prodotto Il prodotto da rimuovere.
	 */
	public void deleteProdotto(ProdottoBean prodotto) {
		for(ProdottoQuantita p : prodotti) {
			if(p.getProdotto().getId() == prodotto.getId()) {
				prodotti.remove(p) ;
				break;
			}
		}
	}
	
	/**
	 * Metodo che restituisce la lista dei prodotti nel carrello.
	 * @return La lista dei prodotti
	 */
	public List<ProdottoQuantita> getProdotti() {
		return prodotti ;
	}
	
	/**
	 * Metodo che permette di calcolare il prezzo totale di tutti i prodotti
	 * presenti nel carrello.
	 */
	public float calcolaTotale() {
		
		float totale = 0.0f ;
		
		for(ProdottoQuantita p : prodotti) {
			float prezzoProdotto ;
			prezzoProdotto = p.getProdotto().calcolaPrezzoIvato() * p.getQuantita() ;
			totale = totale + prezzoProdotto ;
		}
		return totale ;
	}
	
	/**
	 * Metodo che permette di ripulire il carrello dai prodotti.
	 */
	public void clearCarrello() {
		prodotti = new ArrayList<>() ;
	}
}
