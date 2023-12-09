package jardin.model.metier;

import jardin.model.dao.BmHistoCultureDao;

public class BmHistoCultureMetier extends BmHistoCultureDao{
	private String nomLegume;    
	
	public BmHistoCultureMetier() {
		super();
	}

	public String getNomLegume() {
		return nomLegume;
	}

	public void setNomLegume(String nomLegume) {
		this.nomLegume = nomLegume;
	}
	
}
