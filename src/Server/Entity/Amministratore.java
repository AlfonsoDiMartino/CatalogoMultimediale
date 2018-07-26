package Server.Entity;

import Server.DAO.AmministratoreDAO;

public class Amministratore {

	private Integer id;
	private String email;
	private String password;

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

	public Boolean save() {
		AmministratoreDAO amministratore = new AmministratoreDAO();
		amministratore.setEmail(email);
		amministratore.setPassword(password);
		return amministratore.save();
	}

}