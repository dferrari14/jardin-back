package jardin.technique;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ServiceDAO {
	
	public Object executeRequete(String req) throws JardinException {
		try {
			Connection con = UtilsDao.getConnection();
			
			Statement statement= con.createStatement();
			ResultSet res = statement.executeQuery(req);
			return getResultat(res);
		} catch (SQLException e) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getArrosage");
			j.setDetail(e.getMessage());
			throw j;
		} 
		
		
	}
	
	public abstract Object getResultat(ResultSet res);
	

}
