package Server;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Session;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Server.BusinessLogic.GestoreCatalogo;
import Server.DAO.util.HibernateUtil;
import Server.Entity.Autore;
import Server.Entity.CatalogoMultimediale;
import Server.Entity.CatalogoUtente;
import Server.Entity.ListaAutori;
import Server.Entity.ListaCataloghiUtenti;
import Server.Entity.ListaTipologie;
import Server.Entity.Risorsa;
import Server.Entity.Tipologia;

class GestoreCatalogoTest {

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
	void test_GC01() {
		ArrayList<Autore> lista = (ArrayList<Autore>) GestoreCatalogo.getListaAutori();
		assertNotNull(lista);
		assertEquals(lista.size(), 10);
		assertEquals(lista.get(0).getId().toString(), Integer.toString(52));
		assertEquals(lista.get(1).getId().toString(), Integer.toString(12));
		assertEquals(lista.get(2).getId().toString(), Integer.toString(8));
		assertEquals(lista.get(3).getId().toString(), Integer.toString(10));
		assertEquals(lista.get(4).getId().toString(), Integer.toString(11));
		assertEquals(lista.get(5).getId().toString(), Integer.toString(5));
		assertEquals(lista.get(6).getId().toString(), Integer.toString(6));
		assertEquals(lista.get(7).getId().toString(), Integer.toString(9));
		assertEquals(lista.get(8).getId().toString(), Integer.toString(7));
		assertEquals(lista.get(9).getId().toString(), Integer.toString(13));
	}
	
	@Test
	void test_GC02() {
		ArrayList<Tipologia> lista = (ArrayList<Tipologia>) GestoreCatalogo.getListaTipologia();
		assertNotNull(lista);
		
		assertEquals(lista.size(), 4);
		assertEquals(lista.get(0).getId().toString(), Integer.toString(1));
		assertEquals(lista.get(1).getId().toString(), Integer.toString(2));
		assertEquals(lista.get(2).getId().toString(), Integer.toString(3));
		assertEquals(lista.get(3).getId().toString(), Integer.toString(4));
	}
	
	@Test
	void test_GC03() {
		assertEquals(GestoreCatalogo.getListaRisorseNonPossedute(null).isEmpty(), true);
	}
		
	@Test
	void test_GC04() {
		CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(27);
		assertNotNull(catalogo);
		ArrayList<Risorsa> lista = (ArrayList<Risorsa>) GestoreCatalogo.getListaRisorseNonPossedute(catalogo);
		assertNotNull(lista);
		assertEquals(lista.size(), 9);
		assertEquals(lista.get(0).getId().toString(), Integer.toString(14));
		assertEquals(lista.get(1).getId().toString(), Integer.toString(15));
		assertEquals(lista.get(2).getId().toString(), Integer.toString(16));
		assertEquals(lista.get(3).getId().toString(), Integer.toString(17));
		assertEquals(lista.get(4).getId().toString(), Integer.toString(19));
		assertEquals(lista.get(5).getId().toString(), Integer.toString(20));
		assertEquals(lista.get(6).getId().toString(), Integer.toString(21));
		assertEquals(lista.get(7).getId().toString(), Integer.toString(22));
		assertEquals(lista.get(8).getId().toString(), Integer.toString(53));
	}
	
	@Test
	void test_GC05() {
		CatalogoUtente catalogo = new CatalogoUtente(null, null, null);
		Risorsa[] risorse = new Risorsa[3];
		risorse[0] = new Risorsa(null, null, null, null, null, null, null, null);
		risorse[1] = new Risorsa(null, null, null, null, null, null, null, null);
		risorse[2] = new Risorsa(null, null, null, null, null, null, null, null);
		
		assertEquals(GestoreCatalogo.addOfferta(null, risorse), false);
		
		risorse[0] = null;
		assertEquals(GestoreCatalogo.addOfferta(null, risorse), false);
		
		assertEquals(GestoreCatalogo.addOfferta(catalogo, null), false);
	}
	
	@Test
	void test_GC06() {
		CatalogoUtente catalogo = ListaCataloghiUtenti.getCatalogoUtenteById(27);
		assertNotNull(catalogo);
		
		Risorsa[] risorse = new Risorsa[3];
		risorse[0] = CatalogoMultimediale.getRisorsaById(14);
		assertNotNull(risorse[0]);
		risorse[1] = CatalogoMultimediale.getRisorsaById(15);
		assertNotNull(risorse[1]);
		risorse[2] = CatalogoMultimediale.getRisorsaById(16);
		assertNotNull(risorse[2]);
		
		GestoreCatalogo.addOfferta(catalogo, risorse);
		
		CatalogoUtente catalogofetched = ListaCataloghiUtenti.getCatalogoUtenteById(27);
		assertNotNull(catalogofetched);
		ArrayList<Risorsa> lista = (ArrayList<Risorsa>) catalogofetched.getListaRisorsePossedute();
		assertEquals(lista.size(), 5);
		assertEquals(lista.get(0).getId().toString(), Integer.toString(14));
		assertEquals(lista.get(1).getId().toString(), Integer.toString(15));
		assertEquals(lista.get(2).getId().toString(), Integer.toString(16));
		assertEquals(lista.get(3).getId().toString(), Integer.toString(18));
		assertEquals(lista.get(4).getId().toString(), Integer.toString(23));
	}
	
	@Test
	void test_GC07() {
		Tipologia tipologia = new Tipologia(null, null, null);
		Autore autore = new Autore(null, null, null);
		String titolo = "Titolo";
		Float costoNoleggio = Float.valueOf(0), costoAcquisto = Float.valueOf(0);
		
		String file = "file";
		assertEquals(GestoreCatalogo.inserisciRisorsa(null, autore, titolo, costoNoleggio, costoAcquisto, file), false);
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, null, titolo, costoNoleggio, costoAcquisto, file), false);
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, null, costoNoleggio, costoAcquisto, file), false);
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, titolo, null, costoAcquisto, file), false);
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, titolo, costoNoleggio, null, null), false);
	}
	
	@Test
	void test_GC08() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date data = new Date();
		Tipologia tipologia = ListaTipologie.getTipologiaById(2);
		assertNotNull(tipologia);
		Autore autore = ListaAutori.getAutoreById(9);
		assertNotNull(autore);
		
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, "Godfellas", Float.valueOf("2"), Float.valueOf("9"), "theGodfellas.mp4"), true);
				
		Risorsa risorsafetched = CatalogoMultimediale.getRisorsaById(1);
		assertNotNull(risorsafetched);
		assertEquals(risorsafetched.getAutore().getId().toString(), autore.getId().toString());
		assertEquals(risorsafetched.getTipologia().getId().toString(), tipologia.getId().toString());
		assertEquals(risorsafetched.getTitolo(), "Godfellas");
		assertEquals(risorsafetched.getFile(), "theGodfellas.mp4");
		assertEquals(risorsafetched.getCostoAcquisto(), Float.valueOf("9"));
		assertEquals(risorsafetched.getCostoNoleggio(), Float.valueOf("2"));
		assertEquals(format.format(risorsafetched.getDataPubblicazione()), format.format(data));
	}
	
	@Test
	void test_GC09() {
		Autore autore = ListaAutori.getAutoreById(52);
		assertNotNull(autore);
		Tipologia tipologia = ListaTipologie.getTipologiaById(1);
		assertNotNull(tipologia);
		assertEquals(tipologia.getListaAutori().size(), 3);		
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, "DeLaurentis ammette di non voler cacciare soldi", Float.valueOf("100"), Float.valueOf("100"), "delaurentis.mp4"), true);
		assertEquals(ListaTipologie.getTipologiaById(1).getListaAutori().size(), 4);
	}
	
	@Test
	void test_GC10() {
		Autore autore = ListaAutori.getAutoreById(52);
		assertNotNull(autore);
		Tipologia tipologia = ListaTipologie.getTipologiaById(1);
		assertNotNull(tipologia);
		assertEquals(tipologia.getListaAutori().size(), 4);		
		assertEquals(GestoreCatalogo.inserisciRisorsa(tipologia, autore, "Insigne che azzecca congiuntivi", Float.valueOf("100"), Float.valueOf("100"), "insigne.mp4"), true);
		assertEquals(ListaTipologie.getTipologiaById(1).getListaAutori().size(), 4);
		
	}
	
	@Test
	void test_GC11() {
		assertEquals(GestoreCatalogo.aggiungiAutore("Freddie", "Mercury"), true);
				
		Autore autorefetched = ListaAutori.getAutoreById(4);
		assertNotNull(autorefetched);
		assertEquals(autorefetched.getNome(), "Freddie");
		assertEquals(autorefetched.getCognome(), "Mercury");
	}
	
	@Test
	void test_GC12() {
		String nome = "nome", cognome = "cognome";
		assertEquals(GestoreCatalogo.aggiungiAutore(null, cognome), false);
		assertEquals(GestoreCatalogo.aggiungiAutore(nome, null), false);
		
	}
	
	

}
