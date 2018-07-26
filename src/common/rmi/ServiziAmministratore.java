package common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ServiziAmministratore extends Remote{
	public Integer getId() throws RemoteException;
	public List<String> getListaTipologiaRisorse(Integer id) throws RemoteException;
	public List<String> getListaAutori(Integer id) throws RemoteException;
	public List<String> getListaUtentiPremium(Integer id) throws RemoteException ;
	public Boolean inserisciRisorsa (Integer id, String tipologia, String autore, String titolo, Float costoAcquisto, Float costoNoleggio, String file) throws RemoteException;
	public Boolean inserisciAutore (Integer id, String NomeAutore, String CognomeAutore) throws RemoteException;
	public Float generaReport(Integer id, int mese, String tipologia) throws RemoteException;
	public Boolean  aggiungiRisorseACatalogoUtente(Integer id, String utente, String[] risorsa) throws RemoteException;
	public List<String> getRisorseNonPossedute(Integer id, String utente) throws RemoteException;
}
