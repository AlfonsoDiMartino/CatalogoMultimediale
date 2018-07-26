package Server.Proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Server.Coordinator.CoordinatoreAmministratore;
import common.rmi.ServiziAmministratore;

public class ProxyAmministratoreServer extends UnicastRemoteObject implements ServiziAmministratore{
	
	private static final long serialVersionUID = -6989929350766364227L;
	private static ProxyAmministratoreServer uniqueInstance = null;
	private Random rand = null;
	private HashMap<Integer, CoordinatoreAmministratore> mapCoord = null;

	public static synchronized ProxyAmministratoreServer getInstance() throws RemoteException {
		if (uniqueInstance == null)
			uniqueInstance = new ProxyAmministratoreServer();
		return uniqueInstance;
	}
	
	private ProxyAmministratoreServer() throws RemoteException {
		super();
		rand = new Random();
		mapCoord = new HashMap<Integer, CoordinatoreAmministratore>();
	}

	@Override
	public Integer getId() throws RemoteException {
		Integer index;
		do
			index = Integer.valueOf(rand.nextInt());
		while (mapCoord.get(index) != null);
		
		CoordinatoreAmministratore coord = new CoordinatoreAmministratore();
		mapCoord.put(index, coord);
		return index;
	}
	
	@Override
	public List<String> getListaTipologiaRisorse(Integer id) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.getListaTipologiaRisorse();
		}
		return null;
	}
	
	@Override
	public List<String> getListaAutori(Integer id) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.getListaAutori();
		}
		return null;
	}

	@Override
	public List<String> getListaUtentiPremium(Integer id) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.getListaUtentiPremium();
		}
		return null;
	}

	@Override
	public Boolean inserisciRisorsa (Integer id, String tipologia, String autore, String titolo, Float costoAcquisto, Float costoNoleggio, String file) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, file);
		}
		return false;
	}
	
	@Override
	public Boolean inserisciAutore (Integer id, String NomeAutore, String CognomeAutore) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.inserisciAutore(  NomeAutore, CognomeAutore);
		}
		return false;
	}


	@Override
	public Float generaReport(Integer id, int mese, String tipologia) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.generaReport(mese, tipologia);
		}
		return Float.valueOf(0);
	}
	

	@Override
	public Boolean aggiungiRisorseACatalogoUtente(Integer id, String utente, String[] risorsa) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.aggiungiRisorseACatalogoUtente( utente,  risorsa);
		}
		return null;
	}

	@Override
	public List<String> getRisorseNonPossedute(Integer id, String utente) throws RemoteException {
		if (id != null) {
			CoordinatoreAmministratore coord = mapCoord.get(id);
			if (coord != null)
				return coord.getRisorseNonPossedute(utente);
		}
		return null;
	}

}
