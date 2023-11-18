package jardin.dao.planning;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmPlanning;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class PlanningDao {
	//ECRITURE
	public static void ajouterTachePlanning(BmPlanning b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PLANNING;
			req = req + " values (";
			req = req + "'" + b.getIdParcelle() + "',";
			req = req + "'" + b.getDateDebut() + "',";
			req = req + "'" + b.getDateMax() + "',";
			req = req + "'" + b.getTache() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterTachePlanning");
			j.setDetail(s.getMessage());
			throw j;
		}

	}
	
	//LECTURE
	public static List<BmPlanning> getListePlanning() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PLANNING;
		List<BmPlanning> l = new ArrayList<BmPlanning>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmPlanning(r));

			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeParcelle");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	public static List<BmPlanning> getPlanningParcelle(int idParcelle) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PARCELLE + " where "
				+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle;
		List<BmPlanning> l = new ArrayList<BmPlanning>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmPlanning(r));

			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeParcelle");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	private static BmPlanning getBmPlanning(ResultSet r) throws SQLException {
		BmPlanning c = new BmPlanning();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setDateDebut(r.getString(CsteDao.COLUMN_DATE_DEBUT));
		c.setDateMax(r.getString(CsteDao.COLUMN_DATE_MAX));
		c.setTache(r.getString(CsteDao.COLUMN_TACHE));

		return c;
	}

}
