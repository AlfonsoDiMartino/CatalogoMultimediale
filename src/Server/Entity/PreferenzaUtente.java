package Server.Entity;

import Server.DAO.AutoreDAO;
import Server.DAO.PreferenzaUtenteDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.UtenteDAO;

public class PreferenzaUtente {

	Utente utente;
	Autore autore;
	Tipologia tipologia;

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Boolean save() {
		AutoreDAO autoredao = AutoreDAO.getAutoreById(autore.getId());
		TipologiaDAO tipologiadao = TipologiaDAO.getTipologiaById(tipologia.getId());
		UtenteDAO utentedao = UtenteDAO.getUtenteById(utente.getId());
		
		PreferenzaUtenteDAO preferenza = new PreferenzaUtenteDAO();
		preferenza.setAutore(autoredao);
		preferenza.setTipologia(tipologiadao);
		preferenza.setUtente(utentedao);
		return preferenza.save();
	}

}