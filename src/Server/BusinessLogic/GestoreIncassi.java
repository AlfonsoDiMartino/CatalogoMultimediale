package Server.BusinessLogic;
import java.util.*;
import Server.Entity.*;

public class GestoreIncassi {

	public static List<CatalogoUtente> getListaUtentiPremium() {
		ArrayList<CatalogoUtente> listaCataloghi = (ArrayList<CatalogoUtente>) ListaCataloghiUtenti.getListaCatalogoUtenti();
		ArrayList<CatalogoUtente> listaCataloghiPremium = new ArrayList<CatalogoUtente>();
		
		for (CatalogoUtente catalogo : listaCataloghi) {
			Float totale = new Float(0);
			ArrayList<DataAcquisto> listaAcquisti = (ArrayList<DataAcquisto>) StoricoIncassi.getListaDateAcquistoAnnoSolare(catalogo);
			for (DataAcquisto acquisto : listaAcquisti)
				totale += acquisto.getRisorsa().getCostoAcquisto();
			if (totale > 100)
				listaCataloghiPremium.add(catalogo);
		}
		return listaCataloghiPremium;
	}


}
