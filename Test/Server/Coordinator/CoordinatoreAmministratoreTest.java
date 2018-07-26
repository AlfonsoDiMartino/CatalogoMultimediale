package Server;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Server.Coordinator.CoordinatoreAmministratore;
import Server.DAO.util.HibernateUtil;

class CoordinatoreAmministratoreTest {

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
	
	@Test
	void test_CA01() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		assertEquals(listaTipologie.size(), 4);
		assertEquals(listaTipologie.get(0), "Audio");
		assertEquals(listaTipologie.get(1), "Video");
		assertEquals(listaTipologie.get(2), "Immagine");
		assertEquals(listaTipologie.get(3), "Podcast");
	}
	
	@Test
	void test_CA02() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaAutori = (ArrayList<String>) coordinatore.getListaAutori();
		assertNotNull(listaAutori);
		
		assertEquals(listaAutori.size(), 10);
		assertEquals(listaAutori.get(0), "De Falco Occhio");
		assertEquals(listaAutori.get(1), "Izu Kenro");
		assertEquals(listaAutori.get(2), "Kubrick Stanley");
		assertEquals(listaAutori.get(3), "Leone Sergio");
		assertEquals(listaAutori.get(4), "McCurry Steve");
		assertEquals(listaAutori.get(5), "Morricone Ennio");
		assertEquals(listaAutori.get(6), "Mozart W. A.");
		assertEquals(listaAutori.get(7), "Scorzese Martin");
		assertEquals(listaAutori.get(8), "van Beethoven Ludwig");
		assertEquals(listaAutori.get(9), "Warhol Andy");

	}
	
	@Test
	void test_CA03() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		String tipologia = "Audio";
		String autore = "RHCP";
		String titolo = "Dani California";
		Float costoAcquisto = Float.valueOf(10);
		Float costoNoleggio = Float.valueOf(1);
		String file = "daniCalifornia.mp3";
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, file), false);
	}

	@Test
	void test_CA04() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		String tipologia = "Audio";
		String autore = "RHCP";
		String titolo = "Dani California";
		Float costoAcquisto = Float.valueOf(10);
		Float costoNoleggio = Float.valueOf(1);
		String file = "daniCalifornia.mp3";
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, file), false);
	}

	@Test
	void test_CA05() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		ArrayList<String> listaAutori = (ArrayList<String>) coordinatore.getListaAutori();
		assertNotNull(listaAutori);
		
		String titolo = "Dani California";
		Float costoAcquisto = Float.valueOf(10);
		Float costoNoleggio = Float.valueOf(1);
		String file = "daniCalifornia.mp3";
		assertEquals(coordinatore.inserisciRisorsa("Musica", "Morricone Ennio", titolo, costoAcquisto, costoNoleggio, file), false);
		assertEquals(coordinatore.inserisciRisorsa("Audio", "RHCP", titolo, costoAcquisto, costoNoleggio, file), false);
	}

	
	@Test
	void test_CA06() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		ArrayList<String> listaAutori = (ArrayList<String>) coordinatore.getListaAutori();
		assertNotNull(listaAutori);
		String tipologia = "Audio";
		String autore = "Morricone Ennio";
		String titolo = "The Hateful Eight Soundtrack";
		Float costoAcquisto = Float.valueOf(10);
		Float costoNoleggio = Float.valueOf(1);
		String file = "thehateful8.mp3";
		assertEquals(coordinatore.inserisciRisorsa(null, autore, titolo, costoAcquisto, costoNoleggio, file), false);
		assertEquals(coordinatore.inserisciRisorsa(tipologia, null, titolo, costoAcquisto, costoNoleggio, file), false);
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, null, costoAcquisto, costoNoleggio, file), false);
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, null, costoNoleggio, file), false);
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, null, file), false);
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, null), false);
	}
	
	@Test
	void test_CA07() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		ArrayList<String> listaAutori = (ArrayList<String>) coordinatore.getListaAutori();
		assertNotNull(listaAutori);
		String tipologia = "Audio";
		String autore = "Morricone Ennio";
		String titolo = "The Hateful Eight Soundtrack";
		Float costoAcquisto = Float.valueOf(10);
		Float costoNoleggio = Float.valueOf(1);
		String file = "thehateful8.mp3";
		assertEquals(coordinatore.inserisciRisorsa(tipologia, autore, titolo, costoAcquisto, costoNoleggio, file), true);
	}
	
	@Test
	void test_CA08() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		assertEquals(coordinatore.generaReport(2, "Video"), Float.valueOf(0));
	}
	
	@Test
	void test_CA09() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		assertEquals(coordinatore.generaReport(2, "Musica"), Float.valueOf(0));
	}
	
	@Test
	void test_CA10() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		assertEquals(coordinatore.generaReport(null, "Video"), Float.valueOf(0));
		assertEquals(coordinatore.generaReport(2, null), Float.valueOf(0));
	}
	
	@Test
	void test_CA11() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaTipologie = (ArrayList<String>) coordinatore.getListaTipologiaRisorse();
		assertNotNull(listaTipologie);
		assertEquals(coordinatore.generaReport(2, "Video"), Float.valueOf("7.3"));
	}
	
	@Test
	void test_CA12() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		assertEquals(listaPremium.size(), 1);
		assertEquals(listaPremium.get(0), "Liguori Pietro");
	}
	
	@Test
	void test_CA13() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> lista = (ArrayList<String>) coordinatore.getRisorseNonPossedute("Liguori Pietro");
		assertNotNull(lista);
		assertEquals(lista.size(), 0);
	}
	
	@Test
	void test_CA14() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		assertEquals(coordinatore.getRisorseNonPossedute(null).isEmpty(), true);
	}
	
	@Test
	void test_CA15() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		assertEquals(coordinatore.getRisorseNonPossedute("Utente").isEmpty(), true);
	}
	
	@Test
	void test_CA16() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		ArrayList<String> lista = (ArrayList<String>) coordinatore.getRisorseNonPossedute("Liguori Pietro");
		assertNotNull(lista);
		assertEquals(lista.size(), 8);
		
		assertEquals(lista.get(0), "The Ecstasy of Gold; Morricone Ennio");
		assertEquals(lista.get(1), "The Shining; Kubrick Stanley");
		assertEquals(lista.get(2), "The Aviator; Scorzese Martin");
		assertEquals(lista.get(3), "Cowboy solitari; Warhol Andy");
		assertEquals(lista.get(4), "Ragazza afgana; McCurry Steve");
		assertEquals(lista.get(5), "Requiem; Izu Kenro");
		assertEquals(lista.get(6), "Marilyn Monroe; Warhol Andy");
		assertEquals(lista.get(7), "The Hateful Eight Soundtrack; Morricone Ennio");
	}
	
	@Test
	void test_CA17() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		String[] risorse = new String[3];
		risorse[0] = "Requiem; Izu Kenro";
		risorse[1] = "Marilyn Monroe; Warhol Andy";
		risorse[2] = "The Hateful Eight Soundtrack; Morricone Ennio";
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", risorse), false);
	}
	
	@Test
	void test_CA18() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		
		String[] risorse = new String[3];
		risorse[0] = "Requiem; Izu Kenro";
		risorse[1] = "Marilyn Monroe; Warhol Andy";
		risorse[2] = "The Hateful Eight Soundtrack; Morricone Ennio";
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", risorse), false);
	}
	
	@Test
	void test_CA19() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		ArrayList<String> lista = (ArrayList<String>) coordinatore.getRisorseNonPossedute("Liguori Pietro");
		assertNotNull(lista);
		
		String[] risorse = new String[3];
		risorse[0] = "Requiem; Izu Kenro";
		risorse[1] = "Marilyn Monroe; Warhol Andy";
		risorse[2] = "Risorsa che non esiste";
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", risorse), false);
	}
	
	@Test
	void test_CA20() {		
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		ArrayList<String> lista = (ArrayList<String>) coordinatore.getRisorseNonPossedute("Liguori Pietro");
		assertNotNull(lista);
		
		String[] risorse = new String[3];
		risorse[0] = "Requiem; Izu Kenro";
		risorse[1] = "Marilyn Monroe; Warhol Andy";
		risorse[2] = null;
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente(null, risorse), false);
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", null), false);
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", risorse), false);
	}
	
	@Test
	void test_CA21() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		ArrayList<String> listaPremium = (ArrayList<String>) coordinatore.getListaUtentiPremium();
		assertNotNull(listaPremium);
		ArrayList<String> lista = (ArrayList<String>) coordinatore.getRisorseNonPossedute("Liguori Pietro");
		assertNotNull(lista);
		
		String[] risorse = new String[3];
		risorse[0] = "Requiem; Izu Kenro";
		risorse[1] = "Marilyn Monroe; Warhol Andy";
		risorse[2] = "The Hateful Eight Soundtrack; Morricone Ennio";
		assertEquals(coordinatore.aggiungiRisorseACatalogoUtente("Liguori Pietro", risorse), true);
	}
	
	@Test
	void test_CA22() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		assertEquals(coordinatore.inserisciAutore(null, "Rossi"), false);
		assertEquals(coordinatore.inserisciAutore("Mario", null), false);
	}
	
	@Test
	void test_CA23() {
		CoordinatoreAmministratore coordinatore = new CoordinatoreAmministratore();
		assertEquals(coordinatore.inserisciAutore("Mario", "Rossi"), true);
	}
	
} 
 
