package jardin.dao.voisinage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.dao.BmVoisinageLegumeDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;

public class VoisinageLegumeDao {
	// ECRITURE
	public static void ajouterVoisinageLegume(BmVoisinageLegumeDao b) throws JardinException {

		String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_CULTURE;
		req = req + " values (";
		req = req + "'" + b.getIdLegume() + "',";
		req = req + "'" + b.getIdLegumeVoisinage() + "',";
		req = req + "'" + b.getDescription() + "',";
		req = req + "'" + b.getType() + "'";
		req = req + ")";
		try {
			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterVoisinage,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
	}

	// LECTURE

	public static List<BmVoisinageLegumeDao> getListeVoisinageLegumeOK(int idLegume) throws JardinException {
		return getListeVoisinageLegume(idLegume,CsteDao.COLUMN_TYPE_VOISINAGE_VALUE_OK);
	}
	
	public static List<BmVoisinageLegumeDao> getListeVoisinageLegumeKO(int idLegume) throws JardinException {
		return getListeVoisinageLegume(idLegume,CsteDao.COLUMN_TYPE_VOISINAGE_VALUE_KO);
	}
	
	public static List<BmVoisinageLegumeDao> getListeVoisinageLegume(int idLegume,String type) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_CULTURE;
		req = req + " where " + CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
		req = req + " and " + CsteDao.COLUMN_TYPE + " = '" + type + "'";
		List<BmVoisinageLegumeDao> l = new ArrayList<BmVoisinageLegumeDao>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmVoisinage(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeVoisinageLegume ,req : " + req);
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}

	private static BmVoisinageLegumeDao getBmVoisinage(ResultSet r) throws SQLException {
		BmVoisinageLegumeDao c = new BmVoisinageLegumeDao();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setIdLegumeVoisinage(r.getInt(CsteDao.COLUMN_ID_LEGUME_VOISINAGE));
		c.setDescription(r.getString(CsteDao.COLUMN_DESCRIPTION));
		c.setType(r.getString(CsteDao.COLUMN_TYPE));

		return c;
	}

}
