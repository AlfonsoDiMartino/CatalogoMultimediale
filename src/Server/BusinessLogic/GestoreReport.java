package Server.BusinessLogic;
import java.util.*;
import java.util.concurrent.Semaphore;

import Server.Entity.*;

public class GestoreReport {
	
	private static GestoreReport uniqueInstance = null;
	private Semaphore available;

	
	private GestoreReport() {
		this.available = new Semaphore(3, true);
	}
	
	public static synchronized GestoreReport getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new GestoreReport();
		return uniqueInstance;
	}

	public Float getReport(Integer mese, Tipologia tipologia) throws InterruptedException {
		if (mese == null || tipologia == null)
			return Float.valueOf(0);
		Float totale = new Float(0);
		
		available.acquire();
		
		ArrayList<DataAcquisto> listaAcquisti = (ArrayList<DataAcquisto>) StoricoIncassi.getListaDateAcquistoPerMese(mese);
		ArrayList<DataNoleggio> listaNoleggi = (ArrayList<DataNoleggio>) StoricoIncassi.getListaDateNoleggioPerMese(mese);
		
		for (DataAcquisto acquisto : listaAcquisti)
			if (acquisto.getRisorsa().getTipologia().getId() == tipologia.getId())
				totale += acquisto.getRisorsa().getCostoAcquisto();
		
		for (DataNoleggio noleggio : listaNoleggi)
			if (noleggio.getRisorsa().getTipologia().getId() == tipologia.getId())
				totale += noleggio.getRisorsa().getCostoNoleggio();
		
		available.release();
		
		return totale;
	}

}
