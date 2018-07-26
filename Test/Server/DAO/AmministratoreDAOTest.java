package Server;

import static org.junit.jupiter.api.Assertions.*;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Server.DAO.AmministratoreDAO;
import Server.DAO.util.HibernateUtil;

class AmministratoreDAOTest {

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
	void test_1() {
		AmministratoreDAO amministratore = new AmministratoreDAO();
		amministratore.setEmail("amministratore@catalogo.com");
		amministratore.setPassword("passw0rd");
		assertEquals(amministratore.save(), true);
		System.out.println(amministratore.getIdAmministratore());
	}

	@Test
	void test_2() {
		AmministratoreDAO amministratore2 = AmministratoreDAO.getAmministratoreById(1);
		assertNotEquals(amministratore2, null);
		System.out.println(amministratore2.getIdAmministratore());
		System.out.println(amministratore2.getEmail());
		System.out.println(amministratore2.getPassword());
		assertEquals(amministratore2.getEmail(), "amministratore@catalogo.com");
		assertEquals(amministratore2.getPassword(), "passw0rd");	
	}

	@Test
	void test_3() {
		AmministratoreDAO amministratore3 = AmministratoreDAO.getAmministratoreById(9999);
		assertEquals(amministratore3, null);
	}
	
	@Test
	void test_4() {
		AmministratoreDAO amministratore2 = AmministratoreDAO.getAmministratoreById(1);
		assertNotEquals(amministratore2, null);
		amministratore2.setEmail("admin@catalogomultimediale.it");
		amministratore2.setPassword("passwd");
		assertEquals(amministratore2.update(), true);
		
		AmministratoreDAO amministratore4 = AmministratoreDAO.getAmministratoreById(amministratore2.getIdAmministratore());
		assertNotEquals(amministratore4, null);
		System.out.println(amministratore4.getIdAmministratore());
		System.out.println(amministratore4.getEmail());
		System.out.println(amministratore4.getPassword());
		assertEquals(amministratore4.getIdAmministratore(), amministratore2.getIdAmministratore());
		assertEquals(amministratore4.getEmail(), "admin@catalogomultimediale.it");
		assertEquals(amministratore4.getPassword(), "passwd");
	}

}
