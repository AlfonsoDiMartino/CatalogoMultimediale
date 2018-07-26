package Server.DAO;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import Server.DAO.util.HibernateUtil;


@Entity
@Table(name="Risorsa")
public class RisorsaDAO {

	@Id
	@GeneratedValue
	@Column(name="idRisorsa")
	private Integer idRisorsa;
	private String titolo;
	private Float costoNoleggio;
	private Float costoAcquisto;
	private String fileName;
	
	@Temporal(TemporalType.DATE)
	private Date dataPubblicazione;
	
	@ManyToOne
	@JoinColumn(name="idTipologia_fk")
	private TipologiaDAO tipologia;
	
	@ManyToOne
	@JoinColumn(name="idAutore_fk")
	private AutoreDAO autore;

	public Integer getIdRisorsa() {
		return this.idRisorsa;
	}

	public void setIdRisorsa(Integer idRisorsa) {
		this.idRisorsa = idRisorsa;
	}

	public String getTitolo() {
		return this.titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Float getCostoNoleggio() {
		return this.costoNoleggio;
	}

	public void setCostoNoleggio(Float costoNoleggio) {
		this.costoNoleggio = costoNoleggio;
	}

	public Float getCostoAcquisto() {
		return this.costoAcquisto;
	}

	public void setCostoAcquisto(Float costoAcquisto) {
		this.costoAcquisto = costoAcquisto;
	}

	public Date getDataPubblicazione() {
		return this.dataPubblicazione;
	}

	public void setDataPubblicazione(Date dataPubblicazione2) {
		this.dataPubblicazione = dataPubblicazione2;
	}

	public String getFile() {
		return fileName;
	}

	public void setFile(String file) {
		this.fileName = file;
	}
	
	public TipologiaDAO getTipologia() {
		return this.tipologia;
	}
	
	public void setTipologia(TipologiaDAO tipologia) {
		this.tipologia = tipologia;
	}
	
	public AutoreDAO getAutore() {
		return this.autore;
	}
	
	public void setAutore(AutoreDAO autore) {
		this.autore = autore;
	}

	public boolean save() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.save(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			System.out.println(he);
			return false;
		}finally{
			session.close();
		}
		return true;
	}

	public static RisorsaDAO getRisorsaById(Integer idRisorsa) {
		if (idRisorsa == null)
			return null;
		
		RisorsaDAO risorsa;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			risorsa = (RisorsaDAO) session.get(RisorsaDAO.class, idRisorsa);
			session.getTransaction().commit();
			session.close();
			return risorsa;
		}catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	// restituisce true se vi è un duplicato
	public static boolean verificaDuplicato(String titolo, AutoreDAO autore) {
		if (titolo == null || autore == null)
			return true;
		
		String hql = 	"SELECT R.idRisorsa, R.titolo FROM RisorsaDAO R WHERE R.titolo = '" +
						titolo + "'and idAutore_fk = " + autore.getIdAutore();
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			return !query.list().isEmpty();
		} catch(HibernateException he){
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			session.close();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<RisorsaDAO> getListaRisorse() {
		String hql = 	"FROM RisorsaDAO";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			return (List<RisorsaDAO>)query.list();
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}


}
