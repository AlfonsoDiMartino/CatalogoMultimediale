package Server.Coordinator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Server.BusinessLogic.GestoreCatalogo;
import Server.BusinessLogic.GestoreIncassi;
import Server.BusinessLogic.GestoreReport;
import Server.Entity.Autore;
import Server.Entity.CatalogoMultimediale;
import Server.Entity.CatalogoUtente;
import Server.Entity.ListaAutori;
import Server.Entity.ListaCataloghiUtenti;
import Server.Entity.ListaTipologie;
import Server.Entity.Risorsa;
import Server.Entity.Tipologia;

public class CoordinatoreAmministratore {
	
	HashMap<String, Integer> mapTipologia;
	HashMap<String, Integer> mapAutore;
	HashMap<String, Integer> mapCataloghi;
	HashMap<String, Integer> mapRisorse;
	
	public List<String> getListaTipologiaRisorse() {
		
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Tipologia> listaTipologie = (ArrayList<Tipologia>) ListaTipologie.getListaTipologie();
		mapTipologia = new HashMap<String, Integer>();
		for (Tipologia tipologia : listaTipologie) {
			mapTipologia.put(tipologia.getDescrizione(), tipologia.getId());
			lista.add(tipologia.getDescrizione());
		}
		return lista;
	}
	
	public List<String> getListaAutori() {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Autore> listaAutori = (ArrayList<Autore>) ListaAutori.getListaAutori();
		mapAutore = new HashMap<String, Integer>();
		for (Autore autore : listaAutori) {
			mapAutore.put(autore.getCognome() + " " + autore.getNome(), autore.getId());
			lista.add(autore.getCognome() + " " + autore.getNome());
		}
		return lista;
	}

	public Boolean inserisciRisorsa(String tipologia, String autore, String titolo, Float costoAcquisto, Float costoNoleggio, String file) {
		Boolean success = new Boolean(false);
		if (mapAutore != null && mapTipologia != null) {
			Integer idAutore = mapAutore.get(autore);
			Integer idTipologia = mapTipologia.get(tipologia);
			
			if (idAutore != null && idTipologia != null) {
				Autore a = ListaAutori.getAutoreById(idAutore);
				Tipologia t = ListaTipologie.getTipologiaById(idTipologia);
				return GestoreCatalogo.inserisciRisorsa(t, a, titolo, costoNoleggio, costoAcquisto, file);
			}
		}
		return success;
	}

	public Float generaReport(Integer mese, String tipologia) {
		if (mese == null) {
			System.out.println("mese è null");
			return Float.valueOf(0);
		}
		if (tipologia == null) {
			System.out.println("tipologia è null");
			return Float.valueOf(0);
		}
		
		if (mapTipologia != null) {
			Integer idTipologia = mapTipologia.get(tipologia);
			if (idTipologia != null) {
				Tipologia t = ListaTipologie.getTipologiaById(idTipologia);
				try {
					return GestoreReport.getInstance().getReport(Integer.valueOf(mese), t);
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					return Float.valueOf(0);
				}
			}
		}
		return new Float(0);
	}

	public List<String> getListaUtentiPremium() {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<CatalogoUtente> listaCataloghi = (ArrayList<CatalogoUtente>) GestoreIncassi.getListaUtentiPremium();
		mapCataloghi = new HashMap<String, Integer>();
		for (CatalogoUtente catalogo : listaCataloghi) {
			mapCataloghi.put(catalogo.getUtente().getCognome() + " " + catalogo.getUtente().getNome(), catalogo.getId());
			lista.add(catalogo.getUtente().getCognome() + " " + catalogo.getUtente().getNome());
		}
		return lista;
	}

	public List<String> getRisorseNonPossedute(String utente) {
		ArrayList<String> lista = new ArrayList<String>();
		if (mapCataloghi != null) {
			Integer idCatalogo = mapCataloghi.get(utente);
			if (idCatalogo != null) {
				CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(idCatalogo);
				ArrayList<Risorsa> listaRisorse = (ArrayList<Risorsa>) GestoreCatalogo.getListaRisorseNonPossedute(catalogo);
				mapRisorse = new HashMap<String, Integer>();
				for (Risorsa risorsa : listaRisorse) {
					mapRisorse.put(risorsa.getTitolo() + "; " + risorsa.getAutore().getCognome() + " " + risorsa.getAutore().getNome(), risorsa.getId());
					lista.add(risorsa.getTitolo() + "; " + risorsa.getAutore().getCognome() + " " + risorsa.getAutore().getNome());
				}
			}
		}
		return lista;
	}

	public Boolean aggiungiRisorseACatalogoUtente(String utente, String[] risorse) {
		Boolean success = new Boolean(false);
		if (mapCataloghi != null && mapRisorse != null && risorse != null) {
			Integer idCatalogo = mapCataloghi.get(utente);
			Integer idRisorsa0 = mapRisorse.get(risorse[0]);
			Integer idRisorsa1 = mapRisorse.get(risorse[1]);
			Integer idRisorsa2 = mapRisorse.get(risorse[2]);
			
			if (idCatalogo != null && idRisorsa0 != null && idRisorsa1 != null && idRisorsa2 != null) {
				CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(idCatalogo);
				Risorsa[] arrayRisorse = new Risorsa[3];
				arrayRisorse[0] = CatalogoMultimediale.getRisorsaById(idRisorsa0);
				arrayRisorse[1] = CatalogoMultimediale.getRisorsaById(idRisorsa1);
				arrayRisorse[2] = CatalogoMultimediale.getRisorsaById(idRisorsa2);
				return GestoreCatalogo.addOfferta(catalogo, arrayRisorse);
			}
		}
		return success;
	}
	
	public Boolean inserisciAutore(String nome, String cognome) {
		return GestoreCatalogo.aggiungiAutore(nome, cognome);
	}

}
