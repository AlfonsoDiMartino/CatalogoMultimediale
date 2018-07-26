package Client.BusinessLogic;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import Client.Proxy.ProxyAmministratoreClient;


public class OperazioniAmministratore {
	
	private ProxyAmministratoreClient proxy = null;
	
	public OperazioniAmministratore() throws RemoteException, NotBoundException {
		proxy = new ProxyAmministratoreClient();		
	}

	public List<String> getListaTipologie() {
		return proxy.getListaTipologiaRisorse();
	}
	
	public List<String> getListaAutori() {
		return proxy.getListaAutori();
	}

	public Float generaReport(Integer mese, String tipologia) {
		return proxy.generaReport(mese, tipologia);
	}

	public List<String> getListaUtentiPremium() {
		return proxy.getListaUtentiPremium();
	}

	public List<String> getRisorseNonPossedute(String utente) {
		return proxy.getRisorseNonPossedute(utente);
	}
	

	public Boolean aggiungiRisorseACatalogoUtente(String utente, String[] risorse) {
		return proxy.aggiungiRisorseACatalogoUtente(utente, risorse);
	}

	public Boolean inserisciRisorsa(String tipologia, String autore, String titolo, Float costoAcquisto, Float costoNoleggio, String file) {
		return proxy.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, file);
	}
	
	public Boolean inserisciAutore( String NomeAutore, String CognomeAutore) {
		return proxy.inserisciAutore( NomeAutore, CognomeAutore);
	}

}