package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.DAO.AutoreDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.util.HibernateUtil;

class TipologiaDAOTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String sql = String.format("delete from amministratore;\n" + 
				"delete from catalogoutente_risorsa;\n" + 
				"delete from tipologia_autore;\n" + 
				"delete from preferenzautente;\n" + 
				"delete from datanoleggio;\n" + 
				"delete from dataacquisto;\n" + 
				"delete from risorsa;\n" + 
				"delete from tipologia_autore;\n" + 
				"delete from tipologia;\n" + 
				"delete from autore;\n" + 
				"update utente set idcatalogo_fk=null;\n" + 
				"update catalogoutente set idutente_fk=null;\n" + 
				"delete from catalogoutente;\n" + 
				"delete from utente;" +
				"alter sequence hibernate_sequence restart;");
		session.createSQLQuery(sql).executeUpdate();
		session.getTransaction().commit();
		session.close();
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void test_12() {
		TipologiaDAO audio = new TipologiaDAO();
		audio.setDescrizione("Audio");
		assertEquals(audio.save(), true);		
	}
	
	@Test
	void test_13() {
		AutoreDAO kubrik = new AutoreDAO();
		kubrik.setNome("Stanley");
		kubrik.setCognome("Kubrik");
		assertEquals(kubrik.save(), true);
		
		AutoreDAO scorzese = new AutoreDAO();
		scorzese.setNome("Martin");
		scorzese.setCognome("Scorzese");
		assertEquals(scorzese.save(), true);
		
		TipologiaDAO video = new TipologiaDAO();
		video.setDescrizione("Video");
		video.addAutore(kubrik);
		video.addAutore(scorzese);
		assertEquals(video.save(), true);
	}
	
	@Test
	void test_14() {
		TipologiaDAO tipologia = TipologiaDAO.getTipologiaById(1);
		assertNotEquals(tipologia,null);
		assertEquals(tipologia.getDescrizione(), "Audio");
	}
	
	@Test
	void test_15() {
		TipologiaDAO tipologia = TipologiaDAO.getTipologiaById(4);
		assertNotEquals(tipologia,null);
		assertEquals(tipologia.getDescrizione(), "Video");
		
		assertEquals(tipologia.getListaAutori().size(), 2);
		for (int i = 0; i < 2; i++)
			assertEquals(tipologia.getListaAutori().get(i).getIdAutore() == 2 || tipologia.getListaAutori().get(i).getIdAutore() == 3, true);
	}
	
	@Test
	void test_16() {
		TipologiaDAO tipologianull = TipologiaDAO.getTipologiaById(999);
		assertEquals(tipologianull, null);
	}
	
	@Test
	void test_17() {
		AutoreDAO warhol = new AutoreDAO();
		warhol.setNome("Andy");
		warhol.setCognome("Warhol");
		assertEquals(warhol.save(), true);
		
		TipologiaDAO immagine = new TipologiaDAO();
		immagine.setDescrizione("Immagine");
		assertEquals(immagine.save(), true);

		immagine.addAutore(warhol);
		assertEquals(immagine.update(), true);
		
		TipologiaDAO tipologia = TipologiaDAO.getTipologiaById(immagine.getIdTipologia());
		assertNotEquals(tipologia,null);
		assertEquals(tipologia.getDescrizione(), "Immagine");
		
		assertEquals(tipologia.getListaAutori().size(), 1);
		assertEquals(tipologia.getListaAutori().get(0).getIdAutore(), warhol.getIdAutore());	
	}
	
	@Test
	void test_18() {
		ArrayList<Integer> listaId = (ArrayList<Integer>) TipologiaDAO.getListaIdTipologie();
		assertNotEquals(listaId, null);
		assertEquals(listaId.size(), 3);
		
		for (int i = 0; i < 3; i++)
			System.out.println(listaId.get(i).toString());
	}

}
