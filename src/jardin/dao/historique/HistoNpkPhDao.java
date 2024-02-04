package jardin.dao.historique;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.dao.BmHistoNpkPhDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class HistoNpkPhDao {
	//ECRITURE
	public static void ajouterHistoNpkPh(BmHistoNpkPhDao b) throws JardinException {
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
	
	public static void updateHistoNpkPh(BmHistoNpkPhDao b) throws JardinException {
		try {
			String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_NPK_PH;
			req = req + " set ";
			req = req + " " + CsteDao.COLUMN_AZOTE + " = " + b.getAzote() + " , ";
			req = req + " " + CsteDao.COLUMN_POTASSIUM + " = " +b.getPotassium() + " , ";
			req = req + " " + CsteDao.COLUMN_PHOSPHORE + " = " +b.getPhosphore() + " , ";
			req = req + " " + CsteDao.COLUMN_PH + " = " +b.getPh() ;
			req = req + " where " + CsteDao.COLUMN_ID_PARCELLE + " = " + b.getIdParcelle();
			req = req + " and  " + CsteDao.COLUMN_DATE_RELEVE + " = '" + b.getDateReleve()+"'";

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterHistoNpkPh");
			j.setDetail(s.getMessage());
			throw j;
		}
	}
	
	//LECTURE
	public static List<BmHistoNpkPhDao> getHistoNpkPhParcelle(int idParcelle) throws JardinException {
		return getHistoNpkPhParcelle(idParcelle,0);
	}
		
	public static List<BmHistoNpkPhDao> getHistoNpkPhParcelle(int idParcelle,int dateReleve) throws JardinException {
		List<BmHistoNpkPhDao> l = new ArrayList<BmHistoNpkPhDao>();
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_HISTO_NPK_PH + " where "
					+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle ;
			if(dateReleve > 0) {
				req = req + " and " + CsteDao.COLUMN_DATE_RELEVE + " = " + dateReleve;
			}
			req = req +UtilsDao.getOrderby(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(CsteDao.COLUMN_DATE_RELEVE, CsteDao.ORDER_BY_DESC);
				}
			});
			ResultSet r = UtilsDao.executeQuery(req);			
			while (r.next()) {
				l.add(getBmHistoNpkPh(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getHistoNpkPhParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}  
		return l;
	}
	
	 
	private static BmHistoNpkPhDao getBmHistoNpkPh(ResultSet r) throws SQLException {
		BmHistoNpkPhDao c = new BmHistoNpkPhDao();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setDateReleve(r.getString(CsteDao.COLUMN_DATE_RELEVE));
		c.setAzote(r.getDouble(CsteDao.COLUMN_AZOTE));
		c.setPhosphore(r.getDouble(CsteDao.COLUMN_PHOSPHORE));
		c.setPotassium(r.getDouble(CsteDao.COLUMN_POTASSIUM));
		c.setPh(r.getDouble(CsteDao.COLUMN_PH));
		return c;
	}

}
