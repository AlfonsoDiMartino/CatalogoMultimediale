package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Server.Proxy.ProxyAmministratoreServer;
import Server.Proxy.ProxyUtenteServer;


public class StartupServer {

	public static void main(String[] args) {
		initServices(); 
		
	}

	private static void initServices() {
		try {
			LocateRegistry.createRegistry(1099);
			Registry registry = LocateRegistry.getRegistry("localhost");
			ProxyUtenteServer proxyUtente = ProxyUtenteServer.getInstance();
			ProxyAmministratoreServer proxyAmministratore = ProxyAmministratoreServer.getInstance();
			registry.rebind("utente", proxyUtente);
			registry.rebind("amministratore", proxyAmministratore);
			System.out.println("Server avviato. \n");
			
		} catch (RemoteException e) {
			e.printStackTrace();
			System.out.println("Server error!");
		} 
	}

	
}
