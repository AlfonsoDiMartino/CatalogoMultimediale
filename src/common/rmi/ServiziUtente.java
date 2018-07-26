package common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ServiziUtente extends Remote {
	public Integer getId() throws RemoteException;
	public List<String> getListaTipologiaRisorse(Integer id) throws RemoteException;
	public List<String> getListaAutoriPerTipologia(Integer id, String Tipologia) throws RemoteException;
	public Boolean inserisciPreferenza(Integer id, String Utente, String Tipologia, String Autore) throws RemoteException;
}
