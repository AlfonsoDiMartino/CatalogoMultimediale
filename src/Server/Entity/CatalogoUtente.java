package Server.Entity;

import java.util.ArrayList;
import java.util.List;

import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.UtenteDAO;

public class CatalogoUtente {

	private Integer id;
	private Utente utente;
	private List<Risorsa> risorsePossedute = new ArrayList<Risorsa>();
	
	public CatalogoUtente(Integer Id, Utente utente, ArrayList<Risorsa> risorse) {
		this.id = Id;
		this.utente = utente;
		this.risorsePossedute = risorse;
	}
	
	public Integer getId() {
		return id;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<Risorsa> getListaRisorsePossedute() {
		return risorsePossedute;
	}
	
	public void addRisorsa(Risorsa risorsa) {
		if (risorsa != null)
			risorsePossedute.add(risorsa);
	}

	public Boolean update() {
		CatalogoUtenteDAO catalogodao = new CatalogoUtenteDAO();
		
		catalogodao.setIdCatalogoUtente(id);
		ArrayList<RisorsaDAO> risorse = new ArrayList<RisorsaDAO>();
		for (int i = 0; i < risorsePossedute.size(); i++)
			risorse.add(RisorsaDAO.getRisorsaById(risorsePossedute.get(i).getId()));
		catalogodao.setRisorsePossedute(risorse);
		catalogodao.setUtente(UtenteDAO.getUtenteById(utente.getId()));
		
		return catalogodao.update();
	}

}