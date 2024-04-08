package jardin.model.metier;

import jardin.model.dao.BmHistoCultureDao;

public class BmMetierHistoCulture extends BmHistoCultureDao{
	private String nomLegume;    
	
	public BmMetierHistoCulture() {
		super();
	}

	public String getNomLegume() {
		return nomLegume;
	}

	public void setNomLegume(String nomLegume) {
		this.nomLegume = nomLegume;
	}
	
}
