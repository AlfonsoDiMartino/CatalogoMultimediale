package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.DAO.AutoreDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.util.HibernateUtil;

class RisorsaDAOTest {

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
	void test_19() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		TipologiaDAO audio = new TipologiaDAO();
		audio.setDescrizione("Audio");
		assertEquals(audio.save(), true);
		
		AutoreDAO cranberries = new AutoreDAO();
		cranberries.setNome("The Cranberries");
		assertEquals(cranberries.save(), true);
		
		RisorsaDAO risorsa = new RisorsaDAO();
		risorsa.setTitolo("Zombie");
		risorsa.setAutore(cranberries);
		risorsa.setTipologia(audio);
		risorsa.setCostoAcquisto(Float.valueOf("1.20"));
		risorsa.setCostoNoleggio(Float.valueOf("0.20"));
		try {
			risorsa.setDataPubblicazione(format.parse("19/02/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
		assertEquals(risorsa.save(), true);
	}
	
	@Test
	void test_20() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		
		RisorsaDAO risorsa2 = RisorsaDAO.getRisorsaById(3);
		assertNotEquals(risorsa2, null);
		assertEquals(risorsa2.getTipologia().getIdTipologia().toString(), Integer.toString(1));
		assertEquals(risorsa2.getAutore().getIdAutore().toString(), Integer.toString(2));
		assertEquals(risorsa2.getTitolo(), "Zombie");
		assertEquals(risorsa2.getCostoAcquisto(), Float.valueOf("1.20"));
		assertEquals(risorsa2.getCostoNoleggio(), Float.valueOf("0.20"));
		try {
			assertEquals(risorsa2.getDataPubblicazione(), format.parse("19/02/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
	}
	
	@Test
	void test_21() {
		RisorsaDAO risorsanull = RisorsaDAO.getRisorsaById(999);
		assertEquals(risorsanull, null);
	}
	
	@Test
	void test_22() {
		AutoreDAO rhcp = new AutoreDAO();
		rhcp.setNome("Red Hot Chili Peppers");
		rhcp.save();
		AutoreDAO cranberries = AutoreDAO.getAutoreById(2);
		assertNotEquals(cranberries, null);
		
		assertEquals(RisorsaDAO.verificaDuplicato("Zombie", rhcp), false);
		assertEquals(RisorsaDAO.verificaDuplicato("Ode to my family", cranberries), false);
	}
	
	@Test 
	void test_23() {
		AutoreDAO cranberries = AutoreDAO.getAutoreById(2);
		assertNotEquals(cranberries, null);
		assertEquals(RisorsaDAO.verificaDuplicato("Zombie", cranberries), true);
	}
	
	@Test
	void test_24() {
		ArrayList<RisorsaDAO> listaId = (ArrayList<RisorsaDAO>) RisorsaDAO.getListaRisorse();
		assertNotEquals(listaId, null);
		assertEquals(listaId.size(), 1);
		assertEquals(listaId.get(0).getIdRisorsa().toString(), Integer.toString(3));
	}

}
