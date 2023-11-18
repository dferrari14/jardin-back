package jardin.dao.historique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmHistoNpkPh;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class HistoNpkPhDao {
	//ECRITURE
	public static void ajouterHistoNpkPh(BmHistoNpkPh b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_NPK_PH;
			req = req + " values (";
			req = req + "'" + b.getIdParcelle() + "',";
			req = req + "'" + b.getDateReleve() + "',";
			req = req + "'" + b.getAzote() + "',";
			req = req + "'" + b.getPotassium() + "',";
			req = req + "'" + b.getPhosphore() + "',";
			req = req + "'" + b.getPh() + "'";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterHistoNpkPh");
			j.setDetail(s.getMessage());
			throw j;
		} 
	}
	
	//LECTURE
	public static List<BmHistoNpkPh> getHistoNpkPhParcelle(int idParcelle) throws JardinException {
		List<BmHistoNpkPh> l = new ArrayList<BmHistoNpkPh>();
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_NPK_PH + " where "
					+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle;
			ResultSet r = UtilsDao.executeQuery(req);			
			while (r.next()) {
				l.add(getBmHistoNpkPh(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoCultureParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}  
		return l;
	}
	
	 
	private static BmHistoNpkPh getBmHistoNpkPh(ResultSet r) throws SQLException {
		BmHistoNpkPh c = new BmHistoNpkPh();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setDateReleve(r.getString(CsteDao.COLUMN_DATE_RELEVE));
		c.setAzote(r.getDouble(CsteDao.COLUMN_AZOTE));
		c.setPhosphore(r.getDouble(CsteDao.COLUMN_PHOSPHORE));
		c.setPotassium(r.getDouble(CsteDao.COLUMN_POTASSIUM));
		c.setPh(r.getDouble(CsteDao.COLUMN_PH));
		return c;
	}

}
