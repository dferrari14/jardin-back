package jardin.model.dao;

public class BmVoisinageLegumeDao {
	private int idLegume;
	private int idLegumeVoisinage;
	private String description;
	private String type; //ok ou ko 
	
	public BmVoisinageLegumeDao() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	} 

	public int getIdLegumeVoisinage() {
		return idLegumeVoisinage;
	}

	public void setIdLegumeVoisinage(int idLegumeVoisinage) {
		this.idLegumeVoisinage = idLegumeVoisinage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
 
}
