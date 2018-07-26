package Server.Entity;

public class Utente {

	private Integer id;
	private String email;
	private String password;
	private String nome;
	private String cognome;
	private CatalogoUtente catalogo;
	
	public Utente(Integer id, String email, String password, String nome, String cognome, CatalogoUtente catalogo) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.catalogo = catalogo;
	}

	public Integer getId() {
		return this.id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public CatalogoUtente getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(CatalogoUtente catalogo) {
		this.catalogo = catalogo;
	}
}