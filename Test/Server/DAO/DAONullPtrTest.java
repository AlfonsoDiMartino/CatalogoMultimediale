package Server;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Server.DAO.AmministratoreDAO;
import Server.DAO.AutoreDAO;
import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.DataAcquistoDAO;
import Server.DAO.DataNoleggioDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.UtenteDAO;

class DAONullPtrTest {

	@Test
	void test_43() {
		assertNull(AmministratoreDAO.getAmministratoreById(null));
	}
	
	@Test
	void test_44() {
		assertNull(AutoreDAO.getAutoreById(null));
	}
	
	@Test
	void test_45() {
		assertEquals(AutoreDAO.verificaEsistenzaAutore(null, "Rossi"), false);
		assertEquals(AutoreDAO.verificaEsistenzaAutore("Mario", null), false);
	}
	
	@Test
	void test_46() {
		assertNull(TipologiaDAO.getTipologiaById(null));
	}
	
	@Test
	void test_47() {
		assertNull(RisorsaDAO.getRisorsaById(null));
	}
	
	@Test
	void test_48() {
		AutoreDAO autore = new AutoreDAO();
		assertEquals(RisorsaDAO.verificaDuplicato(null, autore), true);
		assertEquals(RisorsaDAO.verificaDuplicato("Titolo", null), true);
	}
	
	@Test
	void test_49() {
		assertNull(UtenteDAO.getUtenteById(null));
	}
	
	@Test
	void test_50() {
		assertNull(CatalogoUtenteDAO.getCatalogoUtenteById(null));
	}
	
	@Test
	void test_51() {
		assertEquals(DataAcquistoDAO.getDateAcquistoPerMese(null).isEmpty(), true);
	}
	
	@Test
	void test_52() {
		assertEquals(DataAcquistoDAO.getDataAcquistoAnnoSolare(null).isEmpty(), true);
	}
	
	@Test
	void test_53() {
		assertEquals(DataNoleggioDAO.getDataNoleggioPerMese(null).isEmpty(), true);
	}

}
