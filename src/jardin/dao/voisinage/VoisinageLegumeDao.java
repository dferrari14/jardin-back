package jardin.dao.voisinage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.dao.legume.LegumeDao;
import jardin.model.dao.BmLegumeDao;
import jardin.model.dao.BmVoisinageLegumeDao;
import jardin.model.metier.BmDetailVoisinageLegumeMetier;
import jardin.model.metier.BmVoisinageLegumeMetier;
import jardin.technique.JardinException;
import jardin.technique.Utils;
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
	
	public static void updateVoisinageCulture(BmVoisinageLegumeDao b) throws JardinException {
		try {
			String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_CULTURE;
			req = req + " set ";
			req = req + " " + CsteDao.COLUMN_TYPE + " = '" + b.getType()+"' , ";
			req = req + " " + CsteDao.COLUMN_DESCRIPTION + " = '" + b.getDescription()+"' ";
			req = req + " where " + CsteDao.COLUMN_ID_LEGUME + " = " + b.getIdLegume();
			req = req + " and  " + CsteDao.COLUMN_ID_LEGUME_VOISINAGE + " = " + b.getIdLegumeVoisinage();

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur updateVoisinageCulture");
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
	
	public static BmVoisinageLegumeMetier getListeVoisinageLegume(int idLegume) throws JardinException {
		List<BmVoisinageLegumeDao> l =  getListeVoisinageLegume(idLegume,null);
		BmVoisinageLegumeMetier b = new BmVoisinageLegumeMetier();
		b.setIdLegume(idLegume);
		for(BmVoisinageLegumeDao bv:l) {
			BmDetailVoisinageLegumeMetier bdv = new BmDetailVoisinageLegumeMetier();
			bdv.setIdLegumeVoisinage(bv.getIdLegumeVoisinage());
			bdv.setType(bv.getType());
			bdv.setDescription(bv.getDescription());
			BmLegumeDao bl = LegumeDao.getLegume(bv.getIdLegumeVoisinage());
			bdv.setNom(bl.getNom());
			b.getListeLegumeVoisinage().add(bdv);
		}
			
		return b;
	}
	
	
	public static List<BmVoisinageLegumeDao> getListeVoisinageLegume(int idLegume,String type) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_CULTURE + " A, ";
		req = req + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_LEGUME + " B ";
		req = req + " where A." + CsteDao.COLUMN_ID_LEGUME + " = " + idLegume;
		req = req + " and A." + CsteDao.COLUMN_ID_LEGUME_VOISINAGE + " = B." + CsteDao.COLUMN_ID_LEGUME;
		if(!Utils.isNullOrEmpty(type)) {
			req = req + " and A." + CsteDao.COLUMN_TYPE + " = '" + type + "' " ;
		}
		req = req+UtilsDao.getOrderby(new LinkedHashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{	
				put("A."+CsteDao.COLUMN_TYPE, CsteDao.ORDER_BY_DESC);
				put("B."+CsteDao.COLUMN_NOM, CsteDao.ORDER_BY_ASC);				
			}
		});
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
