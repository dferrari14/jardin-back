package jardin.model.dao;

import jardin.model.metier.BmMetierDetailVoisinageLegume;

public class BmVoisinageLegumeDao extends BmMetierDetailVoisinageLegume {
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
