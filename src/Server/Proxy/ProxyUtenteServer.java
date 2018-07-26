package Server.Proxy;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import Server.Coordinator.CoordinatoreUtente;


import common.rmi.ServiziUtente;

public class ProxyUtenteServer extends UnicastRemoteObject implements ServiziUtente{

	private static final long serialVersionUID = -2150537117524062407L;
	private static ProxyUtenteServer uniqueInstance = null;
	private Random rand = null;
	private HashMap<Integer, CoordinatoreUtente> mapCoord = null;
	
	
	public static synchronized ProxyUtenteServer getInstance() throws RemoteException {
		if (uniqueInstance == null)
			uniqueInstance = new ProxyUtenteServer();
		return uniqueInstance;
	}
	
	private ProxyUtenteServer() throws RemoteException {
		super();
		rand = new Random();
		mapCoord = new HashMap<Integer, CoordinatoreUtente>();
	}

	@Override
	public Integer getId() throws RemoteException {
		Integer index;
		do
			index = Integer.valueOf(rand.nextInt());
		while (mapCoord.get(index) != null);
		
		
		CoordinatoreUtente coord = new CoordinatoreUtente();
		mapCoord.put(index, coord);
		return index;
	}

	@Override
	public List<String> getListaTipologiaRisorse(Integer id) throws RemoteException {
		if (id != null) {
			CoordinatoreUtente coord = mapCoord.get(id);
			if (coord != null)
				return coord.getListaTipologiaRisorse();			
		}
		return null;
	}

	@Override
	public List<String> getListaAutoriPerTipologia(Integer id, String tipologia) throws RemoteException {
		if (id != null) {
			CoordinatoreUtente coord = mapCoord.get(id);
			if (coord != null)
				return coord.getListaAutoriPerTipologia(tipologia);
		}
		return null;
	}

	@Override
	public Boolean inserisciPreferenza(Integer id, String utente, String tipologia, String autore) throws RemoteException {
		if (id != null) {
			CoordinatoreUtente coord = mapCoord.get(id);
			if (coord != null)
				return coord.inserisciPreferenza(utente, tipologia,  autore);
		}
		return false;
	}
}
