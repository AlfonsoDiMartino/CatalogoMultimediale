package Server.BusinessLogic;
import java.util.*;
import Server.Entity.*;
import Server.Proxy.ProxySistemaMail;

public class GestoreCatalogo {

	public static List<Tipologia> getListaTipologia() {
		return ListaTipologie.getListaTipologie();
	}
	
	public static List<Autore> getListaAutori() {
		return ListaAutori.getListaAutori();
	}

	public static List<Risorsa> getListaRisorseNonPossedute(CatalogoUtente catalogoUtente) {
		if (catalogoUtente == null)
			return new ArrayList<Risorsa>();
		
		ArrayList<Risorsa> listaTutteLeRisorse = (ArrayList<Risorsa>) CatalogoMultimediale.getListaRisorse();
		ArrayList<Risorsa> listaRisorseInCatalogo = (ArrayList<Risorsa>) catalogoUtente.getListaRisorsePossedute();
		listaTutteLeRisorse.removeAll(listaRisorseInCatalogo);
		return listaTutteLeRisorse;
	}

	public static Boolean addOfferta(CatalogoUtente catalogo, Risorsa[] risorse) {
		if (catalogo == null || risorse == null || risorse[0] == null || risorse[2] == null || risorse[2] == null)
			return false;
		catalogo.addRisorsa(risorse[0]);
		catalogo.addRisorsa(risorse[1]);
		catalogo.addRisorsa(risorse[2]);
		Boolean esito = catalogo.update();
		
		if (esito) {
			ProxySistemaMail.inviaMail("catalogomultimediale@email.com", catalogo.getUtente().getEmail(), "Nuove risorse sono state aggiunte al tuo catalogo!");
		}
		
		return esito;
	}

	public static Boolean inserisciRisorsa(	Tipologia tipologia,
											Autore autore,
											String titolo,
											Float costoNoleggio,
											Float costoAcquisto,
											String file) 
	{

		if (	tipologia == null ||
				autore == null ||
				titolo == null ||
				costoNoleggio == null ||
				costoAcquisto == null ||
				file == null
			)
			return false;
		
		if (!CatalogoMultimediale.verificaDuplicato(titolo, autore)) {
			
			Boolean esito = CatalogoMultimediale.inserisciRisorsa(titolo, costoAcquisto, costoNoleggio, autore, tipologia, file);
			
			ArrayList<Autore> autoriInTipologia = tipologia.getListaAutori();
			Boolean found = false;
			int i = 0;
			while (i < autoriInTipologia.size() && !found)
				if (autoriInTipologia.get(i).getId() == autore.getId())
					found = true;
				else
					i++;
			if (!found) {
				tipologia.addAutore(autore);
				tipologia.update();
			}
			
			if (esito)
				GestorePreferenze.notificaUtenti(autore, tipologia);
			
			return esito;
		}
		
		return false;
	}
	
	public static Boolean aggiungiAutore(String nome, String cognome) {
		if (nome == null || cognome == null)
			return false;
		
		Autore autore = new Autore(null, nome, cognome);
		return autore.save();
	}

	
}
