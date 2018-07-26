package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.DAO.AutoreDAO;
import Server.DAO.PreferenzaUtenteDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.UtenteDAO;
import Server.DAO.util.HibernateUtil;

class UtenteDAOTest {

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
	void test_25() {
		UtenteDAO utente = new UtenteDAO();
		utente.setEmail("utente@email.com");
		utente.setPassword("password");
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		assertEquals(utente.save(), true);
	}
	
	@Test
	void test_26() {
		UtenteDAO utente2 = UtenteDAO.getUtenteById(1);
		assertNotNull(utente2);
		
		assertEquals(utente2.getEmail(), "utente@email.com");
		assertEquals(utente2.getPassword(), "password");
		assertEquals(utente2.getNome(), "Mario");
		assertEquals(utente2.getCognome(), "Rossi");
		
	}
	
	@Test
	void test_27() {
		UtenteDAO utente2 = UtenteDAO.getUtenteById(999);
		assertEquals(utente2, null);
	}

	@Test
	void test_28() {
		AutoreDAO rhcp = new AutoreDAO(); // 2
		rhcp.setNome("Red Hot Chili Peppers");
		assertEquals(rhcp.save(), true);

		AutoreDAO kubrik = new AutoreDAO();
		kubrik.setNome("Stanley"); // 3
		kubrik.setCognome("Kubrik");
		assertEquals(kubrik.save(), true);
	
				
		TipologiaDAO audio = new TipologiaDAO(); // 4
		audio.setDescrizione("Audio");
		assertEquals(audio.save(), true);
		
		TipologiaDAO video = new TipologiaDAO(); // 5
		video.setDescrizione("Video");
		assertEquals(video.save(), true);
		
		UtenteDAO utente = UtenteDAO.getUtenteById(1);
		assertNotNull(utente);
		
		PreferenzaUtenteDAO preferenza1 = new PreferenzaUtenteDAO();
		preferenza1.setUtente(utente);
		preferenza1.setAutore(rhcp);
		preferenza1.setTipologia(audio);
		assertEquals(preferenza1.save(), true); // 6
		
		PreferenzaUtenteDAO preferenza2 = new PreferenzaUtenteDAO();
		preferenza2.setUtente(utente);
		preferenza2.setAutore(kubrik);
		preferenza2.setTipologia(video);
		assertEquals(preferenza2.save(), true); // 7
	}
		
	@Test
	void test_29() {
		UtenteDAO utente = UtenteDAO.getUtenteById(1);
		assertNotNull(utente);
		ArrayList<PreferenzaUtenteDAO> preferenze = (ArrayList<PreferenzaUtenteDAO>) PreferenzaUtenteDAO.getListaPreferenzeUtente(utente);
		assertEquals(preferenze.size(), 2);
		assertEquals(preferenze.get(0).getAutore().getIdAutore().toString(), Integer.toString(2));
		assertEquals(preferenze.get(1).getAutore().getIdAutore().toString(), Integer.toString(3));
		assertEquals(preferenze.get(0).getTipologia().getIdTipologia().toString(), Integer.toString(4));
		assertEquals(preferenze.get(1).getTipologia().getIdTipologia().toString(), Integer.toString(5));
	}
	
	@Test
	void test_30() {
		UtenteDAO utente = UtenteDAO.getUtenteById(1);
		assertNotNull(utente);
		ArrayList<PreferenzaUtenteDAO> preferenze = (ArrayList<PreferenzaUtenteDAO>) utente.getListPreferenze();
		assertEquals(preferenze.size(), 2);
		assertEquals(preferenze.get(0).getAutore().getIdAutore().toString(), Integer.toString(2));
		assertEquals(preferenze.get(1).getAutore().getIdAutore().toString(), Integer.toString(3));
		assertEquals(preferenze.get(0).getTipologia().getIdTipologia().toString(), Integer.toString(4));
		assertEquals(preferenze.get(1).getTipologia().getIdTipologia().toString(), Integer.toString(5));
	}

}
