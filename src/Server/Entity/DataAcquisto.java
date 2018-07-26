package Server.Entity;

import java.util.Date;

public class DataAcquisto {

	private Date data;
	CatalogoUtente catalogo;
	Risorsa risorsa;


	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Risorsa getRisorsa() {
		return risorsa;
	}

	public void setRisorsa(Risorsa risorsa) {
		this.risorsa = risorsa;
	}

	public CatalogoUtente getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(CatalogoUtente catalogo) {
		this.catalogo = catalogo;
	}
}