package jardin.dao.voisinage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.model.BmVoisinageParcelle;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;
 

public class VoisinageParcelleDao {
	//ECRITURE
	public static void ajouterVoisinageParcelle(BmVoisinageParcelle b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_PARCELLE;
			req = req + " values (";
			req = req + "'" + b.getIdParcelle() + "',";
			req = req + "'" + b.getIdParcelleVoisinage() + "',";
			req = req + ")";

			 UtilsDao.executeUpdateQuery(req);
		 
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterVoisinageParcelle");
			j.setDetail(s.getMessage());
			throw j;
		} 
	}
	
	//LECTURE	
	public static List<BmVoisinageParcelle> getListeVoisinageParcelle(int idParcelle) throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_VOISINAGE_PARCELLE;
		List<BmVoisinageParcelle> l = new ArrayList<BmVoisinageParcelle>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmVoisinageParcelle(r));
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeVoisinageParcelle");
			j.setDetail(s.getMessage());
			throw j;
		} 
		return l;
	}

	 
	
	private static BmVoisinageParcelle getBmVoisinageParcelle(ResultSet r) throws SQLException {
		BmVoisinageParcelle c = new BmVoisinageParcelle();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setIdParcelleVoisinage(r.getInt(CsteDao.COLUMN_ID_PARCELLE_VOISINAGE));
		
		return c;
	}

}
