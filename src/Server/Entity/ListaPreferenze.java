package Server.Entity;

import java.util.ArrayList;
import java.util.List;

import Server.DAO.PreferenzaUtenteDAO;
import Server.DAO.UtenteDAO;

public class ListaPreferenze {

	public static List<PreferenzaUtente> getListaPreferenzeUtente(Utente utente) {
		if (utente == null)
			return new ArrayList<PreferenzaUtente>();
		UtenteDAO utentedao = UtenteDAO.getUtenteById(utente.getId());
		ArrayList<PreferenzaUtenteDAO> preferenzeDAO = (ArrayList<PreferenzaUtenteDAO>) PreferenzaUtenteDAO.getListaPreferenzeUtente(utentedao);
		
		ArrayList<PreferenzaUtente> listaPreferenze= new ArrayList<PreferenzaUtente>();
		for (int i = 0; i < preferenzeDAO.size(); i++) {
			Autore autore = ListaAutori.getAutoreById(preferenzeDAO.get(i).getAutore().getIdAutore());
			Tipologia tipologia = ListaTipologie.getTipologiaById(preferenzeDAO.get(i).getTipologia().getIdTipologia());
			
			PreferenzaUtente preferenza = new PreferenzaUtente();
			preferenza.setUtente(utente);
			preferenza.setAutore(autore);
			preferenza.setTipologia(tipologia);
			listaPreferenze.add(preferenza);
		}
		
		return listaPreferenze;
	}
	
	public static Boolean aggiungiPreferenza(Utente utente, Autore autore, Tipologia tipologia) {
		if (utente == null || autore == null || tipologia == null)
			return false;
		
		PreferenzaUtente preferenza = new PreferenzaUtente();
		preferenza.setAutore(autore);
		preferenza.setTipologia(tipologia);
		preferenza.setUtente(utente);
		return preferenza.save();
	}

}