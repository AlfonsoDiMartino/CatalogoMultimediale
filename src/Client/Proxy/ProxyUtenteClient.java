package Client.Proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import common.rmi.ServiziUtente;


public class ProxyUtenteClient {

	private Registry registry = null;
	private ServiziUtente proxy = null;
	private Integer id = null;
	
	public ProxyUtenteClient() throws RemoteException, NotBoundException {
			registry = LocateRegistry.getRegistry("localhost");
			proxy = (ServiziUtente) registry.lookup("utente");
			id = proxy.getId();
	}
	
	public  List<String> getListaTipologiaRisorse() {
		try {
			return proxy.getListaTipologiaRisorse(id);
		} catch (RemoteException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getListaAutoriPerTipologia(String tipologia) {
		try {
			return proxy.getListaAutoriPerTipologia(id, tipologia);
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Boolean inserisciPreferenza(String utente, String tipologia, String autore) {
		try {
			return proxy.inserisciPreferenza(id, utente, tipologia, autore);
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
