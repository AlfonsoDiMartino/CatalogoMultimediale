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
import Server.DAO.CatalogoUtenteDAO;
import Server.DAO.RisorsaDAO;
import Server.DAO.TipologiaDAO;
import Server.DAO.UtenteDAO;
import Server.DAO.util.HibernateUtil;

class CatalogoUtenteDAOTest {

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
				"delete from utente;\n" + 
				"alter sequence hibernate_sequence restart;\n" + 
				"insert into tipologia (idtipologia, descrizione) values (1, 'Audio');\n" + 
				"insert into tipologia (idtipologia, descrizione) values (2, 'Video');\n" + 
				"insert into tipologia (idtipologia, descrizione) values (3, 'Immagine');\n" + 
				"insert into tipologia (idtipologia, descrizione) values (4, 'Podcast');\n" + 
				"insert into autore (idautore, nome, cognome) values (5, 'Ennio', 'Morricone');\n" + 
				"insert into autore (idautore, nome, cognome) values (6, 'W. A.', 'Mozart');\n" + 
				"insert into autore (idautore, nome, cognome) values (7, 'Ludwig', 'van Beethoven');\n" + 
				"insert into autore (idautore, nome, cognome) values (8, 'Stanley', 'Kubrick');\n" + 
				"insert into autore (idautore, nome, cognome) values (9, 'Martin', 'Scorzese');\n" + 
				"insert into autore (idautore, nome, cognome) values (10, 'Sergio', 'Leone');\n" + 
				"insert into autore (idautore, nome, cognome) values (11, 'Steve', 'McCurry');\n" + 
				"insert into autore (idautore, nome, cognome) values (12, 'Kenro', 'Izu');\n" + 
				"insert into autore (idautore, nome, cognome) values (13, 'Andy', 'Warhol');\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (14, 'The Ecstasy of Gold', 10.00, 3.00, 'ecstasy.mp3', '2018-02-22', 5, 1);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (15, 'Eine Kleine Nachtmusik', 2.00, 1.00, 'eineKleineNachtmusik.mp3', '2018-02-22', 6, 1);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (16, 'Symphony No. 9', 2.00, 1.00, 'symphony9', '2018-02-22', 7, 1);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (17, 'The Shining', 12.00, 4.50, 'shining.mp4', '2018-02-22', 8, 2);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (18, 'The Aviator', 9, 2, 'theAviator.mp4', '2018-02-22', 9, 2);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (19, 'Il buono, il brutto, il cattivo', 7.00, 5.5, 'buonoBruttoCattivo.mp4', '2018-02-22', 10, 2);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (20, 'Cowboy solitari', 4.5, 0.3, 'cowboySolitari.mp4', '2018-02-22', 13, 2);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (21, 'Ragazza afgana', 3, 1, 'ragazza.jpg', '2018-02-22', 11, 3);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (22, 'Requiem', 5, 0.7, 'requiem.jpg', '2018-02-22', 12, 3);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (23, 'Marilyn Monroe', 4.5, 0.3, 'marilyn.jpg', '2018-02-22', 13, 3);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (5, 1);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (6, 1);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (7, 1);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (8, 2);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (9, 2);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (10, 2);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (11, 3);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (12, 3);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (13, 2);\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (13, 3);\n" + 
				"insert into utente (idutente, nome, cognome, email, password) values (24, 'Salvatore', 'Barone', 'salvatorebarone@email.com', 'passw0rd');\n" + 
				"insert into utente (idutente, nome, cognome, email, password) values (25, 'Alfonso', 'Di Martino', 'alfonsodimartino@email.com', 'Pa55uord');\n" + 
				"insert into utente (idutente, nome, cognome, email, password) values (26, 'Pietro', 'Liguori', 'pilig@email.com', 'p4sswOrd');\n" + 
				"insert into catalogoutente(idcatalogo, idutente_fk) values (27, 24);\n" + 
				"insert into catalogoutente(idcatalogo, idutente_fk) values (28, 25);\n" + 
				"insert into catalogoutente(idcatalogo, idutente_fk) values (29, 26);\n" + 
				"update utente set idcatalogo_fk = 27 where idutente = 24;\n" + 
				"update utente set idcatalogo_fk = 28 where idutente = 25;\n" + 
				"update utente set idcatalogo_fk = 29 where idutente = 26;\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (30, 24, 7, 1);\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (31, 24, 12, 3);\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (32, 25, 13, 3);\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (33, 26, 5, 1);\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (34, 26, 6, 1);\n" + 
				"insert into preferenzautente (idpreferenza, idutente_fk, idautore_fk, idtipologia_fk) values (35, 26, 10, 2);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (36, '2018-02-22', 27, 23);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (37, '2017-02-22', 27, 18);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (38, '2018-02-22', 28, 15);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (39, '2017-12-22', 28, 16);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (40, '2017-12-22', 28, 22);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (41, '2018-01-22', 29, 16);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (42, '2018-01-22', 29, 15);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (43, '2018-02-22', 29, 19);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (27, 23);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (27, 18);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (28, 15);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (28, 16);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (28, 22);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (29, 16);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (29, 15);\n" + 
				"insert into catalogoutente_risorsa (catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (29, 19);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (44, '2018-02-22', 27, 21);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (45, '2017-02-22', 27, 19);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (46, '2018-01-22', 28, 14);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (47, '2018-02-22', 28, 20);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (48, '2017-12-22', 28, 23);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (49, '2017-12-22', 29, 14);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (50, '2018-01-22', 29, 21);\n" + 
				"insert into datanoleggio (idnoleggio, datanoleggio, idcatalogo_fk, idrisorsa_fk) values (51, '2018-02-22', 29, 23);\n" + 
				"insert into autore (idautore, nome, cognome) values (52, 'Occhio', 'De Falco');\n" + 
				"insert into tipologia_autore (listaautori_idautore, tipologia_idtipologia) values (52, 3);\n" + 
				"insert into risorsa (idrisorsa, titolo, costoacquisto, costonoleggio, filename, datapubblicazione, idautore_fk, idtipologia_fk) values (53, 'Foto del prossimo acquisto decente del napoli', 110.00, 110.00, 'hahaha.jpg', '2018-02-25', 52, 3);\n" + 
				"insert into dataacquisto (idacquisto, dataacquisto, idcatalogo_fk, idrisorsa_fk) values (54, '2018-02-25', 29, 53);\n" + 
				"insert into catalogoutente_risorsa(catalogoutente_idcatalogo, risorsepossedute_idrisorsa) values (29, 53);\n" + 
				"");
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
	void test_31() {
		// test 31
		UtenteDAO utente = new UtenteDAO();
		utente.setEmail("utente@email.com");
		utente.setPassword("password");
		utente.setNome("Mario");
		utente.setCognome("Rossi");
		
		CatalogoUtenteDAO catalogo = new CatalogoUtenteDAO();
		assertEquals(catalogo.save(), true);
		utente.setCatalogo(catalogo);
		assertEquals(utente.save(), true);
		catalogo.setUtente(utente);
		assertEquals(catalogo.update(), true);
	}
	
	@Test
	void test_32() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(1);
		assertNotNull(catalogo);
		
		TipologiaDAO audio = new TipologiaDAO();
		audio.setDescrizione("Audio");
		assertEquals(audio.save(), true);
		
		AutoreDAO cranberries = new AutoreDAO();
		cranberries.setNome("The Cranberries");
		assertEquals(cranberries.save(), true);
		
				
		RisorsaDAO risorsa1 = new RisorsaDAO();
		RisorsaDAO risorsa2 = new RisorsaDAO();
		
		risorsa1.setTitolo("Zombie");
		risorsa1.setAutore(cranberries);
		risorsa1.setTipologia(audio);
		risorsa1.setCostoAcquisto(Float.valueOf("1.20"));
		risorsa1.setCostoNoleggio(Float.valueOf("0.20"));
		
		risorsa2.setTitolo("Ode to my family");
		risorsa2.setAutore(cranberries);
		risorsa2.setTipologia(audio);
		risorsa2.setCostoAcquisto(Float.valueOf("1.20"));
		risorsa2.setCostoNoleggio(Float.valueOf("0.20"));
		
		try {
			risorsa1.setDataPubblicazione(format.parse("19/02/2018"));
			risorsa2.setDataPubblicazione(format.parse("19/02/2018"));
		} catch (ParseException e) {
			fail(e.getMessage());
			return;
		}
		
		assertEquals(risorsa1.save(), true);
		assertEquals(risorsa2.save(), true);
		
		catalogo.addRisorsa(risorsa1);
		catalogo.addRisorsa(risorsa2);
		assertEquals(catalogo.update(), true);
	}
	
	@Test
	void test_33() {
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(1);
		UtenteDAO utente = UtenteDAO.getUtenteById(2);
		RisorsaDAO risorsa1 = RisorsaDAO.getRisorsaById(5);
		RisorsaDAO risorsa2 = RisorsaDAO.getRisorsaById(6);
		assertNotNull(catalogo);
		assertNotNull(utente);
		assertNotNull(risorsa1);
		assertNotNull(risorsa2);
		
		assertEquals(catalogo.getUtente().getIdUtente().toString(), utente.getIdUtente().toString());
		assertEquals(catalogo.getRisorsePossedute().size(), 2);
		assertEquals(catalogo.getRisorsePossedute().get(0).getIdRisorsa().toString(), risorsa1.getIdRisorsa().toString());
		assertEquals(catalogo.getRisorsePossedute().get(1).getIdRisorsa().toString(), risorsa2.getIdRisorsa().toString());
	}
	
	@Test
	void test_34() {
		CatalogoUtenteDAO catalogo = CatalogoUtenteDAO.getCatalogoUtenteById(9999);
		assertEquals(catalogo, null);
	}
	
	@Test
	void test_35() {
		ArrayList<Integer> listaId = (ArrayList<Integer>) CatalogoUtenteDAO.getListaIdCataloghi();
		assertNotNull(listaId);
		assertEquals(listaId.size(), 1);
		assertEquals(listaId.get(0).toString(), Integer.toString(1));
	}

}
