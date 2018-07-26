package Server.Entity;

import java.util.ArrayList;
import java.util.List;

import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.UtenteDAO;

public class ListaCataloghiUtenti {

	public static List<CatalogoUtente> getListaCatalogoUtenti() {
		ArrayList<Integer> listaID = (ArrayList<Integer>) CatalogoUtenteDAO.getListaIdCataloghi();
		ArrayList<CatalogoUtente> lista = new ArrayList<CatalogoUtente>();
		for (int i = 0; i < listaID.size(); i++)
			lista.add(getCatalogoUtenteById(listaID.get(i)));
		
		return lista;

	}
	
	public static CatalogoUtente getCatalogoUtenteById(Integer id) {
		CatalogoUtenteDAO catalogodao = CatalogoUtenteDAO.getCatalogoUtenteById(id);
		if (catalogodao != null) {
			Integer Id = catalogodao.getIdCatalogoUtente();
			
			ArrayList<Risorsa> lista = new ArrayList<Risorsa>();
			for (int i = 0; i < catalogodao.getRisorsePossedute().size(); i++)
				lista.add(CatalogoMultimediale.getRisorsaById(catalogodao.getRisorsePossedute().get(i).getIdRisorsa()));
			
			CatalogoUtente catalogo = new CatalogoUtente(Id, null, lista);

			Utente utente = new Utente(
					catalogodao.getUtente().getIdUtente(),
					catalogodao.getUtente().getEmail(),
					catalogodao.getUtente().getPassword(),
					catalogodao.getUtente().getNome(),
					catalogodao.getUtente().getCognome(),
					catalogo);
			catalogo.setUtente(utente);				
			
			return catalogo;
		}
		else
			return null;
	}	
	
	public static Utente getUtenteById(Integer idUtente) {
		UtenteDAO utentedao = UtenteDAO.getUtenteById(idUtente);
		if (utentedao != null) {
			CatalogoUtente catalogo = getCatalogoUtenteById(utentedao.getCatalogo().getIdCatalogoUtente());
			
			Utente utente = new Utente(
					utentedao.getIdUtente(),
					utentedao.getEmail(),
					utentedao.getPassword(),
					utentedao.getNome(),
					utentedao.getCognome(),
					catalogo);
			
			return utente;
		}
		else
			return null;
		
	}


}