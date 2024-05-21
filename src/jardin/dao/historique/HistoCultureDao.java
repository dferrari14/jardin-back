package jardin.dao.historique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.dao.utils.CommonDao;
import jardin.model.dao.BmHistoCultureDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
import jardin.technique.UtilsDate;

public class HistoCultureDao {
	// ECRITURE
	public static void ajouterHistoCulture(BmHistoCultureDao b) throws JardinException {

		String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE;
		req = req + " values (";
		req = req + CommonDao.getNextID(CsteDao.TABLE_HISTO_CULTURE, CsteDao.COLUMN_ID_HISTO) + ",";
		req = req + "'" + b.getIdParcelle() + "',";
		req = req + "'" + b.getIdLegume() + "',";
		req = req + "'" + b.getType() + "',";
		req = req + "'" + b.getEncombrement() + "',";
		req = req + "'" + b.getDateDebut() + "',";
		req = req +  "'" + b.getDateFin() + "',";
		req = req + "'" + b.getObservation() + "',";
		req = req + "'" + b.getRendement() + "'";
		req = req + ")";
		try {
			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterHistoCulture,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
	}
	
	public static void deleteHistoCulture(int id) throws JardinException {
		String req = "delete from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE;
		req = req + " where " + CsteDao.COLUMN_ID_HISTO + " = " + id;
		try {
			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur delete HistoCulture,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
	}

	public static void updateHistoCulture(BmHistoCultureDao b) throws JardinException {

		String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE;
		req = req + " set " + CsteDao.COLUMN_ID_LEGUME + " ='" + b.getIdLegume() + "' ,";
		req = req + CsteDao.COLUMN_TYPE + " ='" + b.getType() + "' ,";
		req = req + CsteDao.COLUMN_ENCOMBREMENT + " ='" + b.getEncombrement() + "' ,";
		req = req + CsteDao.COLUMN_DATE_DEBUT + " ='" + b.getDateDebut() + "' ,";
		req = req + CsteDao.COLUMN_DATE_FIN + " ='" + b.getDateFin() + "' ,";
		req = req + CsteDao.COLUMN_OBSERVATION + " ='" + b.getObservation() + "' ,";
		req = req + CsteDao.COLUMN_RENDEMENT + " ='" + b.getRendement() + "' ";
		req = req + " where " + CsteDao.COLUMN_ID_HISTO + " = " + b.getIdHisto();
		try {
			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur cloturerHistoCulture,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
	}

	// LECTURE

	public static List<BmHistoCultureDao> getListeHistoCulture() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE;
		List<BmHistoCultureDao> l = new ArrayList<BmHistoCultureDao>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeHistoCulture,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}

	public static List<BmHistoCultureDao> getHistoCultureParcelle(int idParcelle) throws JardinException {
		List<BmHistoCultureDao> l = new ArrayList<BmHistoCultureDao>();
		//culture future   (pas encore de date de debut ni de fin)
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE + " where "
				+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle 
				+ " and " + CsteDao.COLUMN_DATE_DEBUT + " = ''"
				+ " and " + CsteDao.COLUMN_DATE_FIN + " = ''";
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelle,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		
		//culture  en cours (date de debut present mais pas encore de date de fin)
		req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE + " where "
				+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle 
				+ " and " + CsteDao.COLUMN_DATE_DEBUT + " != ''"
				+ " and " + CsteDao.COLUMN_DATE_FIN + " = ''";
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelle,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		
 

		 req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE + " where "
				+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle 
				+ " and " + CsteDao.COLUMN_DATE_FIN + " != ''"
				+ UtilsDao.getOrderby(new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put(CsteDao.COLUMN_DATE_FIN, CsteDao.ORDER_BY_DESC);
					}
				});
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelle,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}
	
	public static BmHistoCultureDao getHistoCultureByIdHisto(int idHisto) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE + " where "
				+ CsteDao.COLUMN_ID_HISTO + " = " + idHisto ;
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				return getBmHistoCulture(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelle,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		 
		throw new JardinException("HistoCulture avec id " + idHisto + " introuvable");
	}
	
	public static List<BmHistoCultureDao> getHistoCultureEncours() throws JardinException {
		List<BmHistoCultureDao> lRet = new ArrayList<BmHistoCultureDao>();
		Date date = UtilsDate.createDate();
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE ;
		req = req + " where "  + "(" + CsteDao.COLUMN_DATE_DEBUT + " <= " +UtilsDate.getAAAAMMJJ(date);
		req = req + " and " + CsteDao.COLUMN_DATE_FIN + " >= "  +UtilsDate.getAAAAMMJJ(date) + ")";
		req = req + " or " + "(" +  CsteDao.COLUMN_DATE_DEBUT + " = ''"+ ")";
		req = req +UtilsDao.getOrderby(new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(CsteDao.COLUMN_DATE_FIN, CsteDao.ORDER_BY_DESC);
			}
		});
		
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				lRet.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureEncours,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return lRet;
	}
	
	public static List<BmHistoCultureDao> getHistoCultureParcelleByDate(int idParcelle,Date date) throws JardinException {
		List<BmHistoCultureDao> l = new ArrayList<BmHistoCultureDao>();

		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE ;
				req = req + " where " + CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle ;
				req = req + " and " + CsteDao.COLUMN_DATE_DEBUT + " <= " +UtilsDate.getAAAAMMJJ(date);
				req = req + " and " + CsteDao.COLUMN_DATE_FIN + " >= "  +UtilsDate.getAAAAMMJJ(date);
				req = req +UtilsDao.getOrderby(new LinkedHashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put(CsteDao.COLUMN_DATE_FIN, CsteDao.ORDER_BY_DESC);
					}
				});
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelleByDate,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}

	public static List<BmHistoCultureDao> getHistoCultureLegume(int idLegume) throws JardinException {
		List<BmHistoCultureDao> l = new ArrayList<BmHistoCultureDao>();

		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_CULTURE + " where "
				+ CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmHistoCulture(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureLegume,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}

	private static BmHistoCultureDao getBmHistoCulture(ResultSet r) throws SQLException {
		BmHistoCultureDao c = new BmHistoCultureDao();
		c.setIdHisto(r.getInt(CsteDao.COLUMN_ID_HISTO));
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setType(r.getString(CsteDao.COLUMN_TYPE));
		c.setEncombrement(r.getString(CsteDao.COLUMN_ENCOMBREMENT));
		c.setDateDebut(r.getString(CsteDao.COLUMN_DATE_DEBUT));
		c.setDateFin(r.getString(CsteDao.COLUMN_DATE_FIN));
		c.setObservation(r.getString(CsteDao.COLUMN_OBSERVATION));
		c.setRendement(r.getString(CsteDao.COLUMN_RENDEMENT));
		return c;
	}

}
