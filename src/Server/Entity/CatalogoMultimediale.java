package Server.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Server.DAO.AutoreDAO;
import Server.DAO.RisorsaDAO;

public class CatalogoMultimediale {

	public static List<Risorsa> getListaRisorse() {
		ArrayList<RisorsaDAO> risorsedao = (ArrayList<RisorsaDAO>) RisorsaDAO.getListaRisorse();
		ArrayList<Risorsa> risorse = new ArrayList<Risorsa>();
		
		for (int i = 0; i < risorsedao.size(); i++) {
			RisorsaDAO risorsaDAO = risorsedao.get(i);
			Risorsa risorsa = new Risorsa(
					risorsaDAO.getIdRisorsa(),
					risorsaDAO.getTitolo(),
					risorsaDAO.getCostoNoleggio(),
					risorsaDAO.getCostoAcquisto(),
					risorsaDAO.getDataPubblicazione(),
					risorsaDAO.getFile(),
					ListaAutori.getAutoreById(risorsaDAO.getAutore().getIdAutore()),
					ListaTipologie.getTipologiaById(risorsaDAO.getTipologia().getIdTipologia()));
					
			risorse.add(risorsa);
		}
		
		return risorse;
	}
	
	public static Risorsa getRisorsaById(Integer idRisorsa) {
		RisorsaDAO risorsaDAO = RisorsaDAO.getRisorsaById(idRisorsa);
		if (risorsaDAO != null) 
			return new Risorsa(
				risorsaDAO.getIdRisorsa(),
				risorsaDAO.getTitolo(),
				risorsaDAO.getCostoNoleggio(),
				risorsaDAO.getCostoAcquisto(),
				risorsaDAO.getDataPubblicazione(),
				risorsaDAO.getFile(),
				ListaAutori.getAutoreById(risorsaDAO.getAutore().getIdAutore()),
				ListaTipologie.getTipologiaById(risorsaDAO.getTipologia().getIdTipologia()));
		else
			return null;
	}
	
	public static Boolean verificaDuplicato(String titolo, Autore autore) {
		if (titolo == null || autore == null)
			return true;
		return RisorsaDAO.verificaDuplicato(titolo, AutoreDAO.getAutoreById(autore.getId()));
	}
	
	public static Boolean inserisciRisorsa(String titolo, Float costoAcquisto, Float costoNoleggio, Autore autore, Tipologia tipologia, String file) {
		if (	tipologia == null ||
				autore == null ||
				titolo == null ||
				costoNoleggio == null ||
				costoAcquisto == null ||
				file == null
			)
			return false;
		
		Risorsa risorsa = new Risorsa(
								0,
								titolo,
								costoNoleggio,
								costoAcquisto,
								new Date(),
								file,
								autore,
								tipologia);

		return risorsa.save();
	}


}