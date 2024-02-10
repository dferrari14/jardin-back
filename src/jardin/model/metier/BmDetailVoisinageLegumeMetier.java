package jardin.model.metier;

public class BmDetailVoisinageLegumeMetier {
	private int idLegumeVoisinage;
	private String nom;
	private String description;
	private String type;
	public BmDetailVoisinageLegumeMetier() {
		super();
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
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	} 
	
	
}
