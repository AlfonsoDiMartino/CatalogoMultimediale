package Server.Entity;

import java.util.ArrayList;

import Server.DAO.AutoreDAO;
import Server.DAO.TipologiaDAO;

public class Tipologia {

	private Integer id;
	private String descrizione;
	private ArrayList<Autore> listaAutori = new ArrayList<Autore>(); 

	public Tipologia(Integer id, String descrizione, ArrayList<Autore> listaAutori) {
		this.id = id;
		this.descrizione = descrizione;
		this.listaAutori = listaAutori;
	}

	public Integer getId() {
		return this.id;
	}

	public String getDescrizione() {
		return this.descrizione;
	}

	public void setDescrizione(String d) {
		this.descrizione = d;
	}
	
	public ArrayList<Autore> getListaAutori() {
		return listaAutori;
	}

	public void setListaAutori(ArrayList<Autore> listaAutori) {
		this.listaAutori = listaAutori;
	}

	public void addAutore(Autore autore) {
		listaAutori.add(autore);
	}

	public Boolean update() {
		TipologiaDAO tipologiaDAO = new TipologiaDAO();
		tipologiaDAO.setIdTipologia(id);
		tipologiaDAO.setDescrizione(descrizione);
		
		for (int i = 0; i < listaAutori.size(); i++) {
			AutoreDAO autoredao = new AutoreDAO();
			autoredao.setIdAutore(listaAutori.get(i).getId());
			autoredao.setNome(listaAutori.get(i).getNome());
			autoredao.setCognome(listaAutori.get(i).getCognome());
			tipologiaDAO.addAutore(autoredao);
		}
		
		return tipologiaDAO.update();
	}
}