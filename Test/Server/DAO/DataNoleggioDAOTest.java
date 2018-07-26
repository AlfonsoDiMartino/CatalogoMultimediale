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

import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.DataNoleggioDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.util.HibernateUtil;

class DataNoleggioDAOTest {

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
	void test_40() {
		CatalogoUtenteDAO catalogo = new CatalogoUtenteDAO();
		assertEquals(catalogo.save(), true); // 1
		
		RisorsaDAO risorsa = new RisorsaDAO();
		risorsa.setTitolo("Zombie");
		assertEquals(risorsa.save(), true); // 2
		
		DataNoleggioDAO datanoleggio = new DataNoleggioDAO();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			datanoleggio.setDataNoleggio(format.parse("19/02/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		datanoleggio.setRisorsa(risorsa);
		datanoleggio.setCatalogo(catalogo);
		assertEquals(datanoleggio.save(), true); // 3
	}
	
	@Test
	void test_41() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(1);
		assertNotNull(catalogo);
		RisorsaDAO risorsa = RisorsaDAO.getRisorsaById(2);
		assertNotNull(risorsa);
		
		DataNoleggioDAO datanoleggio1 = new DataNoleggioDAO();
		DataNoleggioDAO datanoleggio2 = new DataNoleggioDAO();
		try {
			datanoleggio1.setDataNoleggio(format.parse("19/02/2017"));
			datanoleggio2.setDataNoleggio(format.parse("19/01/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
		datanoleggio1.setRisorsa(risorsa);
		datanoleggio1.setCatalogo(catalogo);
		assertEquals(datanoleggio1.save(), true); // 4
		datanoleggio2.setRisorsa(risorsa);
		datanoleggio2.setCatalogo(catalogo);
		assertEquals(datanoleggio2.save(), true); // 5
		
		ArrayList<DataNoleggioDAO> lista = (ArrayList<DataNoleggioDAO>) DataNoleggioDAO.getDataNoleggioPerMese(2);
		assertEquals(lista.size(), 1);
		assertEquals(format.format(lista.get(0).getDataNoleggio()), "19/02/2018");
		
	}
	
	@Test
	void test_42() {
		ArrayList<DataNoleggioDAO> lista = (ArrayList<DataNoleggioDAO>) DataNoleggioDAO.getDataNoleggioPerMese(3);
		assertEquals(lista.isEmpty(), true);
	}

}
