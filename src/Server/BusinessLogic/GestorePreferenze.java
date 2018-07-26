package Server.BusinessLogic;
import java.util.*;
import Server.Entity.*;
import Server.Proxy.ProxySistemaMail;

public class GestorePreferenze {

	public static List<PreferenzaUtente> getListaPreferenzeUtente(Utente utente) {
		return ListaPreferenze.getListaPreferenzeUtente(utente);
	}
	
	public static Boolean aggiungiPreferenza(Utente utente, Autore autore, Tipologia tipologia) {
		return ListaPreferenze.aggiungiPreferenza(utente, autore, tipologia);
	}

	public static void notificaUtenti(Autore autore, Tipologia tipologia) {
		ArrayList<CatalogoUtente> listaCataloghi = (ArrayList<CatalogoUtente>) ListaCataloghiUtenti.getListaCatalogoUtenti();
		for (CatalogoUtente catalogo : listaCataloghi) {
			ArrayList<PreferenzaUtente> listaPreferenze = (ArrayList<PreferenzaUtente>) ListaPreferenze.getListaPreferenzeUtente(catalogo.getUtente());
			Boolean match = new Boolean(false);
			int i = 0;
			while (i < listaPreferenze.size() && !match) {
				if (
						listaPreferenze.get(i).getAutore().getId() == autore.getId() ||
						listaPreferenze.get(i).getTipologia().getId() == tipologia.getId()
						) {
					match = true;
					ProxySistemaMail.inviaMail("catalogomultimediale@email.com", catalogo.getUtente().getEmail(), "Sono state aggiunte nuove risorse che potrebbero interessarti!");
				}
				i++;
			}
		}
	}

}
