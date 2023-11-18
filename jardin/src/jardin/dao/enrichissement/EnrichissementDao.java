package jardin.dao.enrichissement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmEnrichissement;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class EnrichissementDao {
	//ECRITURE
	public static void ajouterEnrichissement(BmEnrichissement b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ENRICHISSEMENT;
			req = req + " values (";
			req = req + "'" + b.getIdParcelle() + "',";
			req = req + "'" + b.getEngrais() + "',";
			req = req + "'" + b.getAmendement() + "',";
			req = req + "'" + b.getDateEnrichissement() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterEnrichissement");
			j.setDetail(s.getMessage());
			throw j;
		}

	}
	
	//LECTURE
	public static List<BmEnrichissement> getListeEnrichissement() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ENRICHISSEMENT;
		List<BmEnrichissement> l = new ArrayList<BmEnrichissement>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmEnrichissement(r));

			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeEnrichissement");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	public static BmEnrichissement getEnrichissement(int idParcelle) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_ENRICHISSEMENT + " where "
					+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				return getBmEnrichissement(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getEnrichissement");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return null;
	}

	private static BmEnrichissement getBmEnrichissement(ResultSet r) throws SQLException {
		BmEnrichissement c = new BmEnrichissement();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setEngrais(r.getString(CsteDao.COLUMN_ENGRAIS));
		c.setAmendement(r.getString(CsteDao.COLUMN_AMENDEMENT));
		c.setDateEnrichissement(r.getString(CsteDao.COLUMN_DATE_ENRICHISSEMENT));
		return c;
	}

}
