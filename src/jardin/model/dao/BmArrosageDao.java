package jardin.model.dao;

public class BmArrosageDao {
	private int idLegume;
	private int frequence;
	private String uniteFrequence; //Jours ou Semaine
	private String quantitee;  
	private String description;
	
	public BmArrosageDao() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}

	public int getFrequence() {
		return frequence;
	}

	public void setFrequence(int frequence) {
		this.frequence = frequence;
	}

	public String getUniteFrequence() {
		return uniteFrequence;
	}

	public void setUniteFrequence(String uniteFrequence) {
		this.uniteFrequence = uniteFrequence;
	}

	public String getQuantitee() {
		return quantitee;
	}

	public void setQuantitee(String quantitee) {
		this.quantitee = quantitee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
