package jardin.technique;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Set;

import jardin.main.StartupServlet;

public class UtilsDao {
	public static Connection currentConnection = null;

	public static Connection getConnection() throws SQLException, JardinException {
		if (currentConnection == null || currentConnection.isClosed()) {
			Contexte p = StartupServlet.getContexte();
			try {
				Class.forName(p.getDbDriver());
			} catch (ClassNotFoundException e) {
				throw new JardinException("getConnection ClassNotFoundException ", e.getMessage());
			}
			currentConnection = DriverManager.getConnection(p.getDbUrl(), p.getDbUserName(), p.getDbUserPwd());
		}

		return currentConnection;
	}

	public static Statement getStatement() throws JardinException, SQLException {
		return getConnection().createStatement();
	}

	public static ResultSet executeQuery(String q) throws JardinException, SQLException {
		return getStatement().executeQuery(q);
	}

	public static int executeUpdateQuery(String q) throws JardinException, SQLException {
		return getStatement().executeUpdate(q);
	}

	public static String getOrderby(LinkedHashMap<String, String> lKeyOrder) {
		String o = " order by ";

		Set<String> lKey = lKeyOrder.keySet();
		int nb = lKey.size();
		int index = 0;
		for (String k : lKey) {
			String typeOrder = lKeyOrder.get(k);
			if (index != 0) {
				o = o + " , ";
			}
			o = o + k + " " + typeOrder + " ";
			index++;
		}

		return o;
	}
}
