package jardin.technique;

public class Contexte {
	private String dbUrl;
	private String dbName;
	private String dbDriver;
	private String dbUserName;
	private String dbUserPwd;
	private String httpPort;
	private String pathFolderPhoto;
	
	public Contexte() {
		super();
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUserName() {
		return dbUserName;
	}

	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}

	public String getDbUserPwd() {
		return dbUserPwd;
	}

	public void setDbUserPwd(String dbUserPwd) {
		this.dbUserPwd = dbUserPwd;
	}

	public String getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(String httpPort) {
		this.httpPort = httpPort;
	}

	public String getPathFolderPhoto() {
		return pathFolderPhoto;
	}

	public void setPathFolderPhoto(String pathFolderPhoto) {
		this.pathFolderPhoto = pathFolderPhoto;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}
	
	
	
}
