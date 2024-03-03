package jardin.model.dao;

public class BmSemisDao {
	private int idLegume;
	private int espacementLigneCm;
	private int espacementPlantCm;
	private int profondeur;
	private String moisMinSemis; 
	private String moisMaxSemis; 
	private String moisMinRecolte; 
	private String moisMaxRecolte; 
	private int temperatureMinGerminaison;
	private int dureeGerminaison;
	private String remarque; 
	
	public BmSemisDao() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}

	public int getEspacementLigneCm() {
		return espacementLigneCm;
	}

	public void setEspacementLigneCm(int espacementLigneCm) {
		this.espacementLigneCm = espacementLigneCm;
	}

	public int getEspacementPlantCm() {
		return espacementPlantCm;
	}

	public void setEspacementPlantCm(int espacementPlantCm) {
		this.espacementPlantCm = espacementPlantCm;
	}
	

	public String getMoisMinSemis() {
		return moisMinSemis;
	}

	public void setMoisMinSemis(String moisMinSemis) {
		this.moisMinSemis = moisMinSemis;
	}

	public String getMoisMaxSemis() {
		return moisMaxSemis;
	}

	public void setMoisMaxSemis(String moisMaxSemis) {
		this.moisMaxSemis = moisMaxSemis;
	}

	public String getMoisMinRecolte() {
		return moisMinRecolte;
	}

	public void setMoisMinRecolte(String moisMinRecolte) {
		this.moisMinRecolte = moisMinRecolte;
	}

	public String getMoisMaxRecolte() {
		return moisMaxRecolte;
	}

	public void setMoisMaxRecolte(String moisMaxRecolte) {
		this.moisMaxRecolte = moisMaxRecolte;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

	public int getTemperatureMinGerminaison() {
		return temperatureMinGerminaison;
	}

	public void setTemperatureMinGerminaison(int temperatureMinGerminaison) {
		this.temperatureMinGerminaison = temperatureMinGerminaison;
	}

	public int getDureeGerminaison() {
		return dureeGerminaison;
	}

	public void setDureeGerminaison(int dureeGerminaison) {
		this.dureeGerminaison = dureeGerminaison;
	}

	public int getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(int profondeur) {
		this.profondeur = profondeur;
	}
	
	

}
