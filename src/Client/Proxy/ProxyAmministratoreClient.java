package Client.Proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import common.rmi.ServiziAmministratore;

public class ProxyAmministratoreClient {
	
	private Registry registry = null;
	private ServiziAmministratore proxy = null;
	private Integer id = null;
	
	public ProxyAmministratoreClient() throws RemoteException, NotBoundException {
		registry = LocateRegistry.getRegistry("localhost");
		proxy = (ServiziAmministratore) registry.lookup("amministratore");
		id = proxy.getId();
	}

	public List<String> getListaTipologiaRisorse(){
		try {
			return proxy.getListaTipologiaRisorse(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<String> getListaAutori(){
		try {
			return proxy.getListaAutori(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<String> getListaUtentiPremium() {
		try {
			return proxy.getListaUtentiPremium(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean inserisciRisorsa (String tipologia, String autore, String titolo, Float costoAcquisto, Float costoNoleggio, String file){
		try {
			return proxy.inserisciRisorsa(id, tipologia, autore, titolo, costoAcquisto, costoNoleggio, file);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}
	
	public Boolean inserisciAutore (String NomeAutore, String CognomeAutore){
		try {
			return proxy.inserisciAutore(id, NomeAutore, CognomeAutore);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
	}

	public Float generaReport(int mese, String tipologia) {
		try {
			return proxy.generaReport(id, mese, tipologia);
		}
		catch (Exception e) {
			e.printStackTrace();
			return 0f;
		}
	}

	public Boolean aggiungiRisorseACatalogoUtente(String utente, String[] risorsa) {
		try {
			return proxy.aggiungiRisorseACatalogoUtente(id, utente,  risorsa);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<String> getRisorseNonPossedute(String utente) {
		try {
			return proxy.getRisorseNonPossedute(id, utente);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
}
