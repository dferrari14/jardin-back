package jardin.dao.arrosage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmArrosage;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class ArrosageDao {
	//ECRITURE
	public static void ajouterArrosage(BmArrosage b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ARROSAGE;
			req = req + " values (";
			req = req + "'" + b.getIdLegume() + "',";
			req = req + "'" + b.getFrequence() + "',";
			req = req + "'" + b.getUniteFrequence() + "',";
			req = req + "'" + b.getQuantitee() + "',";
			req = req + "'" + b.getDescription() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterArrosage");
			j.setDetail(s.getMessage());
			throw j;
		}

	}
	
	//LECTURE
	
	public static List<BmArrosage> getListeArrosages() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ARROSAGE;
		List<BmArrosage> l = new ArrayList<BmArrosage>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmArrosage(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeArrosages");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	public static BmArrosage getArrosage(int idLegume) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ARROSAGE + " where "
					+ CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				return getBmArrosage(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getArrosage");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return null;
	}
	
	private static BmArrosage getBmArrosage(ResultSet r) throws SQLException {
		BmArrosage c = new BmArrosage();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setFrequence(r.getInt(CsteDao.COLUMN_FREQUENCE));
		c.setUniteFrequence(r.getString(CsteDao.COLUMN_UNITE));
		c.setQuantitee(r.getString(CsteDao.COLUMN_QUANTITEE));
		c.setDescription(r.getString(CsteDao.COLUMN_DESCRIPTION));
		return c;
	}

}
