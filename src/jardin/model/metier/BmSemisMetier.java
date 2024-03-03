package jardin.model.metier;

import jardin.model.dao.BmSemisDao;

public class BmSemisMetier extends BmSemisDao{
	private String nom;

	public BmSemisMetier() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
