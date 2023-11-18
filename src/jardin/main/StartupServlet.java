package jardin.main;

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import jardin.constante.CsteProperties;
import jardin.technique.Contexte;
import jardin.technique.Utils;
 
 

public class StartupServlet extends HttpServlet {
	private final static String FILE_PATH_PROPERTIES = "..\\..\\..\\DAVID\\workspace\\jardin\\ressources\\application.properties";

	private static jardin.technique.Contexte contexte;

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("INIT STARTUP SERVLET JARDIN2 !!!!!!!!!!!");
		Properties p = null;
		try {
			p = Utils.load(FILE_PATH_PROPERTIES);
		} catch (IOException e) {
			e.printStackTrace();
		}

		contexte = initContexte(p);
	
	}

	public static Contexte getContexte() {
		return contexte;
	}
	
	private Contexte initContexte(Properties p) {
		Contexte c =  new Contexte();
		c.setDbDriver(p.getProperty(CsteProperties.DB_DRIVER));
		c.setDbName(p.getProperty(CsteProperties.DB_NAME));
		c.setDbUrl(p.getProperty(CsteProperties.DB_URL));
		c.setDbUserName(p.getProperty(CsteProperties.DB_USER_NAME));
		c.setDbUserPwd(p.getProperty(CsteProperties.DB_USER_PWD));
		c.setHttpPort(p.getProperty(CsteProperties.HTTP_PORT));
		c.setPathFolderPhoto(p.getProperty(CsteProperties.PATH_FOLDER_PHOTO));
		return c;
	}
}
