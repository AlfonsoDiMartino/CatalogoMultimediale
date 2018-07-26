package Server.DAO;
import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.Year;
import java.util.*;
import Server.DAO.util.HibernateUtil;

@Entity
@Table(name="DataNoleggio")
public class DataNoleggioDAO {
	
	@Id
	@GeneratedValue
	@Column(name="idNoleggio")
	private Integer idNoleggio;

	private Date dataNoleggio;
	@ManyToOne
	@JoinColumn(name="idCatalogo_fk")
	private CatalogoUtenteDAO catalogo;
	@ManyToOne
	@JoinColumn(name="idRisorsa_fk")
	private RisorsaDAO risorsa;

	public Integer getIdNoleggio() {
		return idNoleggio;
	}
	
	public void setIdNoleggio(Integer idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public Date getDataNoleggio() {
		return this.dataNoleggio;
	}

	public void setDataNoleggio(Date dataNoleggio) {
		this.dataNoleggio = dataNoleggio;
	}

	public CatalogoUtenteDAO getCatalogo() {
		return this.catalogo;
	}
	
	public void setCatalogo(CatalogoUtenteDAO catalogo) {
		this.catalogo = catalogo;
	}

	public RisorsaDAO getRisorsa() {
		return this.risorsa;
	}
	
	public void setRisorsa(RisorsaDAO risorsa) {
		this.risorsa = risorsa;
	}

	public static List<DataNoleggioDAO> getDataNoleggioPerMese(Integer mese) {	
		if (mese == null)
			return new ArrayList<DataNoleggioDAO>();
		String hql = 	"FROM DataNoleggioDAO D WHERE MONTH(dataNoleggio) = " + mese.toString() +
						"AND YEAR(dataNoleggio) = " + Integer.toString(Year.now().getValue());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<DataNoleggioDAO> result = (List<DataNoleggioDAO>)query.list();
			return result;
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
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

}
