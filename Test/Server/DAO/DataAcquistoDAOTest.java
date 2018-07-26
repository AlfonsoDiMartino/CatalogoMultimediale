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
import Server.DAO.DataAcquistoDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.util.HibernateUtil;

class DataAcquistoDAOTest {

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
	void test_36() {
		CatalogoUtenteDAO catalogo = new CatalogoUtenteDAO();
		assertEquals(catalogo.save(), true); // 1
		
		RisorsaDAO risorsa = new RisorsaDAO();
		risorsa.setTitolo("Zombie");
		assertEquals(risorsa.save(), true); // 2
		
		DataAcquistoDAO dataacquisto = new DataAcquistoDAO();
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataacquisto.setDataAcquisto(format.parse("19/02/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		dataacquisto.setRisorsa(risorsa);
		dataacquisto.setCatalogo(catalogo);
		assertEquals(dataacquisto.save(), true); // 3
	}
	
	@Test
	void test_37() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(1);
		assertNotNull(catalogo);
		RisorsaDAO risorsa = RisorsaDAO.getRisorsaById(2);
		assertNotNull(risorsa);
		
		DataAcquistoDAO dataacquisto1 = new DataAcquistoDAO();
		DataAcquistoDAO dataacquisto2 = new DataAcquistoDAO();
		try {
			dataacquisto1.setDataAcquisto(format.parse("19/02/2017"));
			dataacquisto2.setDataAcquisto(format.parse("19/01/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
		}
		
		dataacquisto1.setRisorsa(risorsa);
		dataacquisto1.setCatalogo(catalogo);
		assertEquals(dataacquisto1.save(), true); // 4
		dataacquisto2.setRisorsa(risorsa);
		dataacquisto2.setCatalogo(catalogo);
		assertEquals(dataacquisto2.save(), true); // 5
		
		ArrayList<DataAcquistoDAO> lista = (ArrayList<DataAcquistoDAO>) DataAcquistoDAO.getDateAcquistoPerMese(2);
		assertEquals(lista.size(), 1);
		assertEquals(format.format(lista.get(0).getDataAcquisto()), "19/02/2018");
		
	}
	
	@Test
	void test_38() {
		ArrayList<DataAcquistoDAO> lista = (ArrayList<DataAcquistoDAO>) DataAcquistoDAO.getDateAcquistoPerMese(3);
		assertEquals(lista.isEmpty(), true);
	}
	
	@Test
	void test_39() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(1);
		assertNotNull(catalogo);
		ArrayList<DataAcquistoDAO> listaAnnoSolare = (ArrayList<DataAcquistoDAO>) DataAcquistoDAO.getDataAcquistoAnnoSolare(catalogo);
		assertNotNull(listaAnnoSolare);
		assertEquals(listaAnnoSolare.size(), 2);
		assertEquals(format.format(listaAnnoSolare.get(0).getDataAcquisto()), "19/02/2018");
		assertEquals(format.format(listaAnnoSolare.get(1).getDataAcquisto()), "19/01/2018");
		
	}

}
