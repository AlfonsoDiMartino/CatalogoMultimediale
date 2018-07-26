package Server.Entity;

import java.util.ArrayList;
import java.util.List;

import Server.DAO.TipologiaDAO;;

public class ListaTipologie {

	public static List<Tipologia> getListaTipologie() {
		ArrayList<Integer> idTipologieDAO = (ArrayList<Integer>)TipologiaDAO.getListaIdTipologie();
		ArrayList<Tipologia> listaTipologie= new ArrayList<Tipologia>();
		for (int i = 0; i < idTipologieDAO.size(); i++)
			listaTipologie.add(getTipologiaById(idTipologieDAO.get(i)));
		return listaTipologie;
	}
	
	public static Tipologia getTipologiaById(Integer idTipologia) {
		TipologiaDAO tipologiadao = TipologiaDAO.getTipologiaById(idTipologia);
		if (tipologiadao != null) {
			ArrayList<Autore> listaAutori = new ArrayList<Autore>();
			for (int i = 0; i < tipologiadao.getListaAutori().size(); i++) {
				Autore autore = ListaAutori.getAutoreById(tipologiadao.getListaAutori().get(i).getIdAutore());
				listaAutori.add(autore);
			}
			return new Tipologia(tipologiadao.getIdTipologia(), tipologiadao.getDescrizione(), listaAutori);
		}
		else
			return null;
	}



}