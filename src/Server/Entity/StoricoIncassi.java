package Server.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.DataAcquistoDAO;
import Server.DAO.DataNoleggioDAO;

public class StoricoIncassi {

	public static List<DataAcquisto> getListaDateAcquistoAnnoSolare(CatalogoUtente catalogoUtente) {
		if (catalogoUtente == null)
			return new ArrayList<DataAcquisto>();
		CatalogoUtenteDAO catalogoDAO = CatalogoUtenteDAO.getCatalogoUtenteById(catalogoUtente.getId());
		ArrayList<DataAcquistoDAO> dateAcqDAO = (ArrayList<DataAcquistoDAO>) DataAcquistoDAO.getDataAcquistoAnnoSolare(catalogoDAO);
		ArrayList<DataAcquisto> dateAcq = new ArrayList<DataAcquisto>();
		
		for (int i = 0; i < dateAcqDAO.size(); i++) {
			CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(dateAcqDAO.get(i).getCatalogo().getIdCatalogoUtente());
			Risorsa risorsa = CatalogoMultimediale.getRisorsaById(dateAcqDAO.get(i).getRisorsa().getIdRisorsa());
			Date data = dateAcqDAO.get(i).getDataAcquisto();
			
			DataAcquisto d = new DataAcquisto();
			d.setCatalogo(catalogo);
			d.setData(data);
			d.setRisorsa(risorsa);
			dateAcq.add(d);
		}
		
		return dateAcq;
	}
	
	public static List<DataAcquisto> getListaDateAcquistoPerMese(Integer mese) {
		if (mese == null)
			return new ArrayList<DataAcquisto>();
		ArrayList<DataAcquistoDAO> dateAcqDAO = (ArrayList<DataAcquistoDAO>) DataAcquistoDAO.getDateAcquistoPerMese(mese);
		ArrayList<DataAcquisto> dateAcq = new ArrayList<DataAcquisto>();
		
		for (int i = 0; i < dateAcqDAO.size(); i++) {
			CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(dateAcqDAO.get(i).getCatalogo().getIdCatalogoUtente());
			Risorsa risorsa = CatalogoMultimediale.getRisorsaById(dateAcqDAO.get(i).getRisorsa().getIdRisorsa());
			Date data = dateAcqDAO.get(i).getDataAcquisto();
			
			DataAcquisto d = new DataAcquisto();
			d.setCatalogo(catalogo);
			d.setData(data);
			d.setRisorsa(risorsa);
			dateAcq.add(d);
		}
		
		return dateAcq;
	}

	public static List<DataNoleggio> getListaDateNoleggioPerMese(Integer mese) {
		if (mese == null)
			return new ArrayList<DataNoleggio>();
		ArrayList<DataNoleggioDAO> dateNDAO = (ArrayList<DataNoleggioDAO>) DataNoleggioDAO.getDataNoleggioPerMese(mese);
		ArrayList<DataNoleggio> dateNol = new ArrayList<DataNoleggio>();
		
		for (int i = 0; i < dateNDAO.size(); i++) {
			CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(dateNDAO.get(i).getCatalogo().getIdCatalogoUtente());
			Risorsa risorsa = CatalogoMultimediale.getRisorsaById(dateNDAO.get(i).getRisorsa().getIdRisorsa());
			Date data = dateNDAO.get(i).getDataNoleggio();
			
			DataNoleggio d = new DataNoleggio();
			d.setCatalogo(catalogo);
			d.setData(data);
			d.setRisorsa(risorsa);
			dateNol.add(d);
		}
		
		return dateNol;
	}

}