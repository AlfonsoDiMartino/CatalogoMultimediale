package Server.DAO;

import javax.persistence.*;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.*;
import Server.DAO.util.HibernateUtil;


@Entity
@Table(name="DataAcquisto")
public class DataAcquistoDAO {
	
	@Id
	@GeneratedValue
	@Column(name="idAcquisto")
	private Integer idAcquisto;

	private Date dataAcquisto;
	
	@ManyToOne
	@JoinColumn(name="idRisorsa_fk")
	private RisorsaDAO risorsa;
	
	@ManyToOne
	@JoinColumn(name="idCatalogo_fk")
	private CatalogoUtenteDAO catalogo;

	public Integer getIdAcquisto() {
		return idAcquisto;
	}
	
	public void setIdAcquisto(Integer idAcquisto) {
		this.idAcquisto = idAcquisto;
	}

	public Date getDataAcquisto() {
		return this.dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public RisorsaDAO getRisorsa() {
		return this.risorsa;
	}

	public void setRisorsa(RisorsaDAO risorsa) {
		this.risorsa = risorsa;
	}
	
	public CatalogoUtenteDAO getCatalogo() {
		return this.catalogo;
	}

	public void setCatalogo(CatalogoUtenteDAO catalogo) {
		this.catalogo = catalogo;
	}

	public static List<DataAcquistoDAO> getDateAcquistoPerMese(Integer mese) {
		if (mese == null)
			return new ArrayList<DataAcquistoDAO>();
		String hql = 	"FROM DataAcquistoDAO D WHERE MONTH(dataAcquisto) = " + mese.toString() +
						"AND YEAR(dataAcquisto) = " + Integer.toString(Year.now().getValue());
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<DataAcquistoDAO> result = (List<DataAcquistoDAO>)query.list();
			return result;
		} catch(HibernateException he){
			session.getTransaction().rollback();
			session.close();
			return null;
		}
	}

	public static List<DataAcquistoDAO> getDataAcquistoAnnoSolare(CatalogoUtenteDAO catalogo) {
		if (catalogo == null)
			return new ArrayList<DataAcquistoDAO>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -365);
		Date data = c.getTime();
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String datestr = format1.format(data); 
		String hql = 	"FROM DataAcquistoDAO WHERE dataAcquisto > '" + datestr + "' AND idCatalogo_fk =" + catalogo.getIdCatalogoUtente().toString();
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
			Query query = session.createQuery(hql);
			@SuppressWarnings("unchecked")
			List<DataAcquistoDAO> result = (List<DataAcquistoDAO>)query.list();
			return result;
		} catch(HibernateException he){
			System.out.println(he.getMessage());
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
