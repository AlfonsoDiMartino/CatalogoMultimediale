package Server.DAO;
import javax.persistence.*;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.*;
import Server.DAO.util.HibernateUtil;

@Entity
@Table(name="CatalogoUtente")
public class CatalogoUtenteDAO {

	@Id
	@GeneratedValue
	@Column(name="idCatalogo")
	private Integer idCatalogoUtente;
	
	@OneToOne
	@JoinColumn(name="idUtente_fk")
	private UtenteDAO utente;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<RisorsaDAO> risorsePossedute = new ArrayList<RisorsaDAO>();

	public Integer getIdCatalogoUtente() {
		return this.idCatalogoUtente;
	}

	public void setIdCatalogoUtente(Integer idCatalogoUtente) {
		this.idCatalogoUtente = idCatalogoUtente;
	}

	public UtenteDAO getUtente() {
		return this.utente;
	}

	public void setUtente(UtenteDAO utente) {
		this.utente = utente;
	}

	public List<RisorsaDAO> getRisorsePossedute() {
		return this.risorsePossedute;
	}

	public void setRisorsePossedute(List<RisorsaDAO> risorsePossedute) {
		this.risorsePossedute = risorsePossedute;
	}
	
	public void addRisorsa(RisorsaDAO risorsa) {
		this.risorsePossedute.add(risorsa);
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
	
	public boolean update() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.update(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
		return true;
	}

	public static CatalogoUtenteDAO getCatalogoUtenteById(Integer idCatalogo) {
		if (idCatalogo == null)
			return null;
		CatalogoUtenteDAO catalogo;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			catalogo = (CatalogoUtenteDAO) session.get(CatalogoUtenteDAO.class, idCatalogo);
			if (catalogo != null)
				Hibernate.initialize(catalogo.risorsePossedute);
			session.getTransaction().commit();
			session.close();
			return catalogo;
		}catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getListaIdCataloghi() {
		String hql = 	"SELECT C.idCatalogoUtente FROM CatalogoUtenteDAO C";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			return (List<Integer>)query.list();
		} catch(HibernateException he){
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

}
