package Server.Entity;

import java.util.ArrayList;
import java.util.List;

import Server.DAO.AutoreDAO;

public class ListaAutori {
	
	

	public static List<Autore> getListaAutori() {
		ArrayList<Integer> idAutoriDAO = (ArrayList<Integer>)AutoreDAO.getListaIdAutori();
		ArrayList<Autore> listaAutori= new ArrayList<Autore>();
		for (int i = 0; i < idAutoriDAO.size(); i++)
			listaAutori.add(getAutoreById(idAutoriDAO.get(i)));
		return listaAutori;
	}
	
	public static Autore getAutoreById(Integer idAutore) {
		AutoreDAO autoredao = AutoreDAO.getAutoreById(idAutore);
		if (autoredao != null)
			return new Autore(	autoredao.getIdAutore(),
								autoredao.getNome(),
								autoredao.getCognome());
		else
			return null;
	}
	
	public static Boolean addAutore(String nome, String cognome) {
		Autore autore = new Autore(null, nome, cognome);
		Boolean success = autore.save();
		
//		if (success)
//			listaAutori.add(autore);
		
		return success;
	}

}