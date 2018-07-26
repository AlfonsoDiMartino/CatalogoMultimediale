package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.DAO.AutoreDAO;
import Server.DAO.util.HibernateUtil;

class AutoreDAOTest {

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
	void test_05() {
		AutoreDAO autore = new AutoreDAO();
		autore.setNome("Mario");
		autore.setCognome("Rossi");
		assertEquals(autore.save(), true);
	}

	@Test
	void test_06() {
		AutoreDAO autore2 = AutoreDAO.getAutoreById(1);
		assertNotEquals(autore2, null);
		System.out.println(autore2.getIdAutore());
		System.out.println(autore2.getCognome());
		System.out.println(autore2.getNome());
		assertEquals(autore2.getCognome(), "Rossi");
		assertEquals(autore2.getNome(), "Mario");
	}

	@Test
	void test_07() {
		AutoreDAO autore3 = AutoreDAO.getAutoreById(2000);
		assertEquals(autore3, null);
	}

	@Test
	void test_08() {
		assertEquals(AutoreDAO.verificaEsistenzaAutore("Mario", "Rossi"), true);
	}

	@Test
	void test_09() {
		assertEquals(AutoreDAO.verificaEsistenzaAutore("Giorgio", "Rossi"), false);
	}
	
	@Test
	void test_10() {
		AutoreDAO autore4 = new AutoreDAO();
		autore4.setNome("Giorgio");
		autore4.setCognome("Bianchi");
		assertEquals(autore4.save(), true);
		autore4.setNome("Vittorio");
		autore4.setCognome("Alfieri");
		assertEquals(autore4.update(), true);
		AutoreDAO autore5 = AutoreDAO.getAutoreById(autore4.getIdAutore());
		assertNotEquals(autore5, null);
		assertEquals(autore5.getCognome(), "Alfieri");
		assertEquals(autore5.getNome(), "Vittorio");
	}
	
	@Test
	void test_11() {
		ArrayList<Integer> listaId = (ArrayList<Integer>) AutoreDAO.getListaIdAutori();
		assertNotEquals(listaId, null);
		assertEquals(listaId.size(), 2);
		
		
		assertNotEquals(listaId.get(0), Integer.toString(2));
		assertNotEquals(listaId.get(1), Integer.toString(1));
	}

}
