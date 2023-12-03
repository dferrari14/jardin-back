package jardin.dao.voisinage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmVoisinageLegume;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;

public class VoisinageLegumeDao {
	// ECRITURE
	public static void ajouterVoisinageLegume(BmVoisinageLegume b) throws JardinException {

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

	public static List<BmVoisinageLegume> getListeVoisinageLegumeOK(int idLegume) throws JardinException {
		return getListeVoisinageLegume(idLegume,CsteDao.COLUMN_TYPE_VOISINAGE_VALUE_OK);
	}
	
	public static List<BmVoisinageLegume> getListeVoisinageLegumeKO(int idLegume) throws JardinException {
		return getListeVoisinageLegume(idLegume,CsteDao.COLUMN_TYPE_VOISINAGE_VALUE_KO);
	}
	
	public static List<BmVoisinageLegume> getListeVoisinageLegume(int idLegume,String type) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_CULTURE;
		req = req + " where " + CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
		req = req + " and " + CsteDao.COLUMN_TYPE + " = '" + type + "'";
		List<BmVoisinageLegume> l = new ArrayList<BmVoisinageLegume>();
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

	private static BmVoisinageLegume getBmVoisinage(ResultSet r) throws SQLException {
		BmVoisinageLegume c = new BmVoisinageLegume();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setIdLegumeVoisinage(r.getInt(CsteDao.COLUMN_ID_LEGUME_VOISINAGE));
		c.setDescription(r.getString(CsteDao.COLUMN_DESCRIPTION));
		c.setType(r.getString(CsteDao.COLUMN_TYPE));

		return c;
	}

}
