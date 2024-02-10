package jardin.dao.legume;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.dao.utils.CommonDao;
import jardin.model.dao.BmLegumeDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class LegumeDao {
	//ECRITURE
	public static void modifierLegume(BmLegumeDao b) throws JardinException {
		 
		try {
			String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME;
			req = req + " set ";
			req = req + " " + CsteDao.COLUMN_NOM + " = '" + b.getNom() + "' , ";
			req = req + " " + CsteDao.COLUMN_FAMILLE + " = '" +b.getFamille() + "' , ";
			req = req + " " + CsteDao.COLUMN_TYPE + " = '" +b.getType() + "' , ";
			req = req + " " + CsteDao.COLUMN_NB_ANNEE_ROTATION + " = " +b.getNbAnneeRotation() + " , ";
			req = req + " " + CsteDao.COLUMN_EXPOSITION + " = '" +b.getExposition() + "' ";
			req = req + " where " + CsteDao.COLUMN_ID_LEGUME + " = " + b.getIdLegume();

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur modifierLegume");
			j.setDetail(s.getMessage());
			throw j;
		}

	}
	
	public static void ajouterLegume(BmLegumeDao b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME;
			req = req + " values (";
			req = req + CommonDao.getNextID(CsteDao.TABLE_LEGUME,CsteDao.COLUMN_ID_LEGUME) + ",";
			req = req + "'" + b.getNom() + "',";
			req = req + "'" + b.getFamille() + "',";
			req = req + "'" + b.getType() + "',";
			req = req + "'" + b.getExposition()  + "',";
			req = req + "'" + b.getNbAnneeRotation() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterLegume");
			j.setDetail(s.getMessage());
			throw j;
		} 
	}
	
	//LECTURE
	
	public static List<BmLegumeDao> getListeLegumes() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME;
		req = req  + UtilsDao.getOrderby(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put(CsteDao.COLUMN_TYPE, CsteDao.ORDER_BY_ASC);
				put(CsteDao.COLUMN_NOM, CsteDao.ORDER_BY_ASC);
			}
		});
		List<BmLegumeDao> l = new ArrayList<BmLegumeDao>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmLegume(r));

			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeLegumes");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	public static BmLegumeDao getLegume(int idLegume) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME + " where "
					+ CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				return getBmLegume(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeLegumes");
			j.setDetail(s.getMessage());
			throw j;
		} 
		
		throw new JardinException("legume avec id " + idLegume + " introuvable");
	}
	
	public static List<BmLegumeDao> getLegumes(String type) throws JardinException {
		List<BmLegumeDao> l = new ArrayList<BmLegumeDao>();
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME + " where "
					+ CsteDao.COLUMN_TYPE + " = " + type;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmLegume(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeLegumes");
			j.setDetail(s.getMessage());
			throw j;
		} 
		
		return l;
	}
	
	private static BmLegumeDao getBmLegume(ResultSet r) throws SQLException {
		BmLegumeDao c = new BmLegumeDao();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setNom(r.getString(CsteDao.COLUMN_NOM));
		c.setFamille(r.getString(CsteDao.COLUMN_FAMILLE));
		c.setType(r.getString(CsteDao.COLUMN_TYPE));
		c.setExposition(r.getString(CsteDao.COLUMN_EXPOSITION));
		c.setNbAnneeRotation(r.getInt(CsteDao.COLUMN_NB_ANNEE_ROTATION));
		return c;
	}

}
