package jardin.dao.semis;

import java.sql.ResultSet;
import java.sql.SQLException;

import jardin.constante.CsteDao;
import jardin.model.dao.BmSemisDao;
import jardin.model.metier.BmSemisMetier;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class SemisDao {
	//ECRITURE
	public static void insererSemis(BmSemisDao b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_SEMIS;
			req = req + " values (";
			req = req + "'" + b.getIdLegume() + "',";
			req = req + "'" + b.getEspacementLigneCm() + "',";
			req = req + "'" + b.getEspacementPlantCm() + "',";
			req = req + "'" + b.getProfondeur() + "',";
			req = req + "'" + b.getMoisMinSemis() + "',";
			req = req + "'" + b.getMoisMaxSemis() + "',";
			req = req + "'" + b.getMoisMinRecolte() + "',";
			req = req + "'" + b.getMoisMaxRecolte() + "',";
			req = req + "'" + b.getTemperatureMinGerminaison() + "',";
			req = req + "'" + b.getDureeGerminaison() + "',";
			req = req + "'" + b.getRemarque() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterJemis");
			j.setDetail(s.getMessage());
			throw j;
		} 
	}
	
	public static void updateSemis(BmSemisDao b) throws JardinException {
		try {
			String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_SEMIS;
			req = req + " set ";
			req = req + " " + CsteDao.COLUMN_ESPACEMENT_LIGNE + " = " + b.getEspacementLigneCm() + " , ";
			req = req + " " + CsteDao.COLUMN_ESPACEMENT_PLANT+ " = " +b.getEspacementPlantCm() + " , ";
			req = req + " " + CsteDao.COLUMN_PROFONDEUR + " = " +b.getProfondeur() + " , ";
			req = req + " " + CsteDao.COLUMN_DATE_MIN_SEMIS + " = " + b.getMoisMinSemis() + " , ";
			req = req + " " + CsteDao.COLUMN_DATE_MAX_SEMIS + " = " +b.getMoisMaxSemis() + " , ";
			req = req + " " + CsteDao.COLUMN_DATE_MIN_RECOLTE + " = " +b.getMoisMinRecolte() + " , ";
			req = req + " " + CsteDao.COLUMN_DATE_MAX_RECOLTE + " = " +b.getMoisMaxRecolte() + " , ";
			req = req + " " + CsteDao.COLUMN_TEMPERATURE_MIN_GERMINAISON + " = " +b.getTemperatureMinGerminaison() + " , ";
			req = req + " " + CsteDao.COLUMN_DUREE_GERMINAISON + " = " +b.getDureeGerminaison() + " , ";
			req = req + " " + CsteDao.COLUMN_REMARQUE + " = " +b.getRemarque() ;
			req = req + " where " + CsteDao.COLUMN_ID_LEGUME + " = " + b.getIdLegume();

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur update semis");
			j.setDetail(s.getMessage());
			throw j;
		}
	}
	
	//LECTURE

	public static BmSemisDao getSemis(int idLegume) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_SEMIS  + " A, ";
			req = req + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME + " B ";
			req = req + " where A." + CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
			req = req + " and A." + CsteDao.COLUMN_ID_LEGUME + " = B." + CsteDao.COLUMN_ID_LEGUME;
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
	
	private static BmSemisMetier getBmSemis(ResultSet r) throws SQLException {
		BmSemisMetier c = new BmSemisMetier();
		c.setIdLegume(r.getInt(CsteDao.COLUMN_ID_LEGUME));
		c.setNom(r.getString(CsteDao.COLUMN_NOM));
		c.setEspacementLigneCm(r.getInt(CsteDao.COLUMN_ESPACEMENT_LIGNE));
		c.setEspacementPlantCm(r.getInt(CsteDao.COLUMN_ESPACEMENT_PLANT));
		c.setProfondeur(r.getInt(CsteDao.COLUMN_PROFONDEUR));
		c.setMoisMinSemis(r.getString(CsteDao.COLUMN_DATE_MIN_SEMIS));
		c.setMoisMaxSemis(r.getString(CsteDao.COLUMN_DATE_MAX_SEMIS));
		c.setMoisMinRecolte(r.getString(CsteDao.COLUMN_DATE_MIN_RECOLTE));
		c.setMoisMaxRecolte(r.getString(CsteDao.COLUMN_DATE_MAX_RECOLTE));
		c.setTemperatureMinGerminaison(r.getInt(CsteDao.COLUMN_TEMPERATURE_MIN_GERMINAISON));
		c.setDureeGerminaison(r.getInt(CsteDao.COLUMN_DUREE_GERMINAISON));
		c.setRemarque(r.getString(CsteDao.COLUMN_REMARQUE));
		return c;
	}

}
