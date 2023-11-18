package jardin.dao.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import jardin.constante.CsteDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;

public class CommonDao {
	public static int getNextID(String table,String column) throws JardinException {
		String req = "select max(" + column + ") as nb from " + CsteDao.DATABASE_NAME + "."
				+ table;
		try {
			ResultSet r = UtilsDao.executeQuery(req);
			r.next();
			int res = r.getInt("nb");
			return res + 1;
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getNextId , table : " + table + " ,  colonne : " + column);
			j.setDetail(s.getMessage());
			throw j;
		} 
	}
}
