package jardin.dao.semis;

import java.sql.ResultSet;
import java.sql.SQLException;

import jardin.constante.CsteDao;
import jardin.model.BmSemis;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class SemisDao {
	//ECRITURE
	public static void c(BmSemis b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_SEMIS;
			req = req + " values (";
			req = req + "'" + b.getIdLegume() + "',";
			req = req + "'" + b.getEspacementLigneCm() + "',";
			req = req + "'" + b.getEspacementPlantCm() + "',";
			req = req + "'" + b.getPeriode() + "',";
			req = req + "'" + b.getRemarque() + "'";
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

	public static BmSemis getSemis(int idLegume) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_SEMIS + " where "
					+ CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				return getBmSemis(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getSemis");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return null;
	}
	
	private static BmSemis getBmSemis(ResultSet r) throws SQLException {
		BmSemis c = new BmSemis();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setEspacementLigneCm(r.getInt(CsteDao.COLUMN_ESPACEMENT_LIGNE));
		c.setEspacementPlantCm(r.getInt(CsteDao.COLUMN_ESPACEMENT_PLANT));
		c.setPeriode(r.getString(CsteDao.COLUMN_PERIODE));
		c.setRemarque(r.getString(CsteDao.COLUMN_REMARQUE));
		return c;
	}

}
