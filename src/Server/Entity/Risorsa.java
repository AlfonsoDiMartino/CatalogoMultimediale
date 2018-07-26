package Server.Entity;

import java.util.*;

import Server.DAO.AutoreDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.TipologiaDAO;

public class Risorsa {

	private Integer id;
	private String titolo;
	private Float costoNoleggio;
	private Float costoAcquisto;
	private Date dataPubblicazione;
	private String file;
	private Tipologia tipologia;
	private Autore autore;
	
	public Risorsa(	Integer idRisorsa,
					String titolo,
					Float costoNoleggio,
					Float costoAcquisto,
					Date dataPubblicazione,
					String file,
					Autore autore,
					Tipologia tipologia) {
		this.id = idRisorsa;
		this.titolo = titolo;
		this.costoNoleggio = costoNoleggio;
		this.costoAcquisto = costoAcquisto;
		this.dataPubblicazione = dataPubblicazione;
		this.file = file;
		this.autore = autore;
		this.tipologia = tipologia;
	}

	public Integer getId() {
		return id;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Float getCostoNoleggio() {
		return this.costoNoleggio;
	}

	public void setCostoNoleggio(Float costoNoleggio) {
		this.costoNoleggio = costoNoleggio;
	}

	public Float getCostoAcquisto() {
		return this.costoAcquisto;
	}

	public void setCostoAcquisto(Float costoAcquisto) {
		this.costoAcquisto = costoAcquisto;
	}

	public Date getDataPubblicazione() {
		return dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione) {
		this.dataPubblicazione = dataPubblicazione;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Tipologia getTipologia() {
		return tipologia;
	}

	public void setTipologia(Tipologia tipologia) {
		this.tipologia = tipologia;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}
	
	@Override
	public boolean equals(Object other) {
	    boolean result = false;
	    if (other instanceof Risorsa) {
	        Risorsa that = (Risorsa) other;
	        result = (this.getId() == that.getId());
	    }
	    return result;
	}

	public Boolean save() {
		AutoreDAO autoredao = AutoreDAO.getAutoreById(autore.getId());
		TipologiaDAO tipologiadao = TipologiaDAO.getTipologiaById(tipologia.getId());
		RisorsaDAO risorsa = new RisorsaDAO();
		risorsa.setAutore(autoredao);
		risorsa.setCostoAcquisto(costoAcquisto);
		risorsa.setCostoNoleggio(costoNoleggio);
		risorsa.setDataPubblicazione(dataPubblicazione);
		risorsa.setTipologia(tipologiadao);
		risorsa.setTitolo(titolo);
		risorsa.setFile(file);
		return risorsa.save();
	}

}