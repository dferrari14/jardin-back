package jardin.model.metier;

import jardin.model.dao.BmSemisDao;

public class BmMetierSemis extends BmSemisDao{
	private String nom;

	public BmMetierSemis() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
