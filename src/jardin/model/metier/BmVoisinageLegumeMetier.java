package jardin.model.metier;

import java.util.ArrayList;
import java.util.List;

public class BmVoisinageLegumeMetier {
	private int idLegume;
	private List<BmDetailVoisinageLegumeMetier> listeLegumeVoisinage = new ArrayList<BmDetailVoisinageLegumeMetier>();
	public BmVoisinageLegumeMetier() {
		super();
	}
	public int getIdLegume() {
		return idLegume;
	}
	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}
	public List<BmDetailVoisinageLegumeMetier> getListeLegumeVoisinage() {
		return listeLegumeVoisinage;
	}
	public void setListeLegumeVoisinage(List<BmDetailVoisinageLegumeMetier> listeLegumeVoisinage) {
		this.listeLegumeVoisinage = listeLegumeVoisinage;
	}
	
}
