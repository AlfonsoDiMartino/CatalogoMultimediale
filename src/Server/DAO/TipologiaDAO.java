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
@Table(name="Tipologia")
public class TipologiaDAO {

	@Id
	@GeneratedValue
	@Column(name="idTipologia")
	private Integer idTipologia;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<AutoreDAO> listaAutori = new ArrayList<AutoreDAO>();
	private String descrizione;
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getIdTipologia() {
		return this.idTipologia;
	}

	public void setIdTipologia(Integer idTipologia) {
		this.idTipologia = idTipologia;
	}

	public List<AutoreDAO> getListaAutori() {
		return this.listaAutori;
	}

	public void setListaAutori(List<AutoreDAO> listaAutori) {
		this.listaAutori = listaAutori;
	}
	
	public void addAutore(AutoreDAO autore) {
		this.listaAutori.add(autore);
	}
	
	public boolean save() {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			session.save(this);
			session.getTransaction().commit();
		}catch(HibernateException he){
			System.out.println(he.getMessage());
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
			System.out.println(he.getMessage());
			session.getTransaction().rollback();
			return false;
		}finally{
			session.close();
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Integer> getListaIdTipologie() {
		String hql = 	"SELECT T.idTipologia FROM TipologiaDAO T";
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			return (List<Integer>)query.list();
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	public static TipologiaDAO getTipologiaById(Integer idTipologia) {
		if (idTipologia == null)
			return null;
		
		TipologiaDAO tipologia;
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try{
			tipologia = (TipologiaDAO) session.get(TipologiaDAO.class, idTipologia);
			if (tipologia != null)
				Hibernate.initialize(tipologia.listaAutori);
			session.getTransaction().commit();
			session.close();
			return tipologia;
		}catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}


}
