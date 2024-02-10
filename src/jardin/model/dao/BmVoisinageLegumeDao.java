package jardin.model.dao;

import jardin.model.metier.BmDetailVoisinageLegumeMetier;

public class BmVoisinageLegumeDao extends BmDetailVoisinageLegumeMetier {
	private int idLegume;
	 
	
	public BmVoisinageLegumeDao() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	} 
 
}
