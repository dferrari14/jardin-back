package jardin.model;

public class BmHistoNpkPh {
	private int idParcelle;
	private String dateReleve;
	private double azote; 
	private double potassium; 
	private double phosphore; 
	private double ph; 
	
	public BmHistoNpkPh() {
		super();
	}

	public int getIdParcelle() {
		return idParcelle;
	}

	public void setIdParcelle(int idParcelle) {
		this.idParcelle = idParcelle;
	}

	public String getDateReleve() {
		return dateReleve;
	}

	public void setDateReleve(String dateReleve) {
		this.dateReleve = dateReleve;
	}

	public double getAzote() {
		return azote;
	}

	public void setAzote(double azote) {
		this.azote = azote;
	}

	public double getPotassium() {
		return potassium;
	}

	public void setPotassium(double potassium) {
		this.potassium = potassium;
	}

	public double getPhosphore() {
		return phosphore;
	}

	public void setPhosphore(double phosphore) {
		this.phosphore = phosphore;
	}

	public double getPh() {
		return ph;
	}

	public void setPh(double ph) {
		this.ph = ph;
	}

}
