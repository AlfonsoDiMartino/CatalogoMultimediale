package Client.BusinessLogic;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;


import Client.Proxy.ProxyUtenteClient;
public class OperazioniUtente {
	
	ProxyUtenteClient proxy = null;
	
	public OperazioniUtente() throws RemoteException, NotBoundException {
		proxy = new ProxyUtenteClient();
	}

	public List<String> getListaTipologiaRisorse() {
		return proxy.getListaTipologiaRisorse();
	}

	public List<String> getListaAutoriPerTipologia(String tipologia) {
		return proxy.getListaAutoriPerTipologia(tipologia);
	}

	public Boolean inserisciPreferenza(String utente, String tipologia, String autore) {
		return proxy.inserisciPreferenza(utente, tipologia, autore);
	}

}