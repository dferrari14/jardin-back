package jardin.model.dao;

public class BmLegumeDao {
	private int idLegume;
	private String nom;
	private String famille; //solanacée,crucifere
	private String type; //feuille/racine/legumineuse
	private String exposition;
	private int nbAnneeRotation; //ex 3ans entre chaque culture de poireau sur la meme parcelle
	
	public BmLegumeDao() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getExposition() {
		return exposition;
	}

	public void setExposition(String exposition) {
		this.exposition = exposition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNbAnneeRotation() {
		return nbAnneeRotation;
	}

	public void setNbAnneeRotation(int nbAnneeRotation) {
		this.nbAnneeRotation = nbAnneeRotation;
	}
	
	
	
}
