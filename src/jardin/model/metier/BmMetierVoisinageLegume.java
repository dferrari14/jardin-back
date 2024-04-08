package jardin.model.metier;

import java.util.ArrayList;
import java.util.List;

public class BmMetierVoisinageLegume {
	private int idLegume;
	private List<BmMetierDetailVoisinageLegume> listeLegumeVoisinage = new ArrayList<BmMetierDetailVoisinageLegume>();
	public BmMetierVoisinageLegume() {
		super();
	}
	public int getIdLegume() {
		return idLegume;
	}
	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}
	public List<BmMetierDetailVoisinageLegume> getListeLegumeVoisinage() {
		return listeLegumeVoisinage;
	}
	public void setListeLegumeVoisinage(List<BmMetierDetailVoisinageLegume> listeLegumeVoisinage) {
		this.listeLegumeVoisinage = listeLegumeVoisinage;
	}
	
}
