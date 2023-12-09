package jardin.model.dao;

public class BmEnrichissementDao {
	private int idParcelle;
	private String engrais;
	private String amendement;  
	private String dateEnrichissement;  
	
	public BmEnrichissementDao() {
		super();
	}

	public int getIdParcelle() {
		return idParcelle;
	}

	public void setIdParcelle(int idParcelle) {
		this.idParcelle = idParcelle;
	}

	public String getEngrais() {
		return engrais;
	}

	public void setEngrais(String engrais) {
		this.engrais = engrais;
	}

	public String getAmendement() {
		return amendement;
	}

	public void setAmendement(String amendement) {
		this.amendement = amendement;
	}

	public String getDateEnrichissement() {
		return dateEnrichissement;
	}

	public void setDateEnrichissement(String dateEnrichissement) {
		this.dateEnrichissement = dateEnrichissement;
	}


}
