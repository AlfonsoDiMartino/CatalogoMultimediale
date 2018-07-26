package Server.Coordinator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Server.BusinessLogic.GestoreCatalogo;
import Server.BusinessLogic.GestorePreferenze;
import Server.Entity.*;

public class CoordinatoreUtente {
	
	HashMap<String, Integer> mapTipologia;
	HashMap<String, Integer> mapAutore;
	HashMap<String, Integer> mapUtente;
	
	public CoordinatoreUtente() {
		// solo per debug!
		mapUtente = new HashMap<String, Integer>();
		mapUtente.put("Barone Salvatore", 24);
		mapUtente.put("Di Martino Alfonso", 25);
		mapUtente.put("Liguori Pietro", 26);
	}

	public List<String> getListaTipologiaRisorse() {
		ArrayList<String> lista = new ArrayList<String>();
		ArrayList<Tipologia> listaTipologie = (ArrayList<Tipologia>) GestoreCatalogo.getListaTipologia();
		mapTipologia = new HashMap<String, Integer>();
		for (Tipologia tipologia : listaTipologie) {
			mapTipologia.put(tipologia.getDescrizione(), tipologia.getId());
			lista.add(tipologia.getDescrizione());
		}
		
		return lista;
	}

	public List<String> getListaAutoriPerTipologia(String tipologia) {
		ArrayList<String> lista = new ArrayList<String>();
	
		if (mapTipologia != null) {
			Integer id = mapTipologia.get(tipologia);
			if (id != null) {
				Tipologia t = ListaTipologie.getTipologiaById(id);
				ArrayList<Autore> listaAutori = t.getListaAutori();
				mapAutore = new HashMap<String, Integer>();
				for (Autore autore : listaAutori) {
					mapAutore.put(autore.getCognome() + " " + autore.getNome(), autore.getId());
					lista.add(autore.getCognome() + " " + autore.getNome());
				}
			}
		}
		return lista;
	}

	public Boolean inserisciPreferenza(String utente, String tipologia, String autore) {
		Boolean success = new Boolean(false);
		if (mapAutore != null && mapTipologia != null && mapUtente != null) {
			Integer idAutore = mapAutore.get(autore);
			Integer idTipologia = mapTipologia.get(tipologia);
			Integer idUtente = mapUtente.get(utente);
			
			if (idAutore != null && idTipologia != null && idUtente != null) {
				Autore a = ListaAutori.getAutoreById(idAutore);
				Tipologia t = ListaTipologie.getTipologiaById(idTipologia);
				Utente u = ListaCataloghiUtenti.getUtenteById(idUtente);
				
				return GestorePreferenze.aggiungiPreferenza(u, a, t);
			}
		}
		return success;
	}

}