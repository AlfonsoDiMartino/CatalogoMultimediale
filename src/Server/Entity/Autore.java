package Server.Entity;

import Server.DAO.AutoreDAO;

public class Autore {

	private Integer id;
	private String nome;
	private String cognome;
	
	public Autore(Integer id, String nome, String cognome) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
	}

	public Integer getId() {
		return this.id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Boolean save() {
		AutoreDAO autore = new AutoreDAO();
		autore.setNome(nome);
		autore.setCognome(cognome);
		return autore.save();
	}
}