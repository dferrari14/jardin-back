package jardin.model;

public class BmHistoCulture {
	private int idHisto;
	private int idParcelle;
	private int idLegume;
	private String encombrement; //complet ou demi culture
	private String dateDebut;
	private String dateFin; 
	private String observation;   
	private String rendement;   
	
	public BmHistoCulture() {
		super();
	}

	public int getIdHisto() {
		return idHisto;
	}

	public void setIdHisto(int idHisto) {
		this.idHisto = idHisto;
	}

	public int getIdParcelle() {
		return idParcelle;
	}

	public void setIdParcelle(int idParcelle) {
		this.idParcelle = idParcelle;
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getDateFin() {
		return dateFin;
	}

	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public String getRendement() {
		return rendement;
	}

	public void setRendement(String rendement) {
		this.rendement = rendement;
	}

	public String getEncombrement() {
		return encombrement;
	}

	public void setEncombrement(String encombrement) {
		this.encombrement = encombrement;
	}

}
