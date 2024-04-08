package jardin.model.metier;

import java.util.ArrayList;
import java.util.List;

import jardin.model.dao.BmHistoNpkPhDao;
import jardin.model.dao.BmParcelleDao;
import jardin.model.dao.BmPlanningDao;

public class BmMetierParcelle extends BmParcelleDao{
	private List<BmMetierHistoCulture> histoCulture = new ArrayList<BmMetierHistoCulture>();
	private List<BmHistoNpkPhDao> histoNpkPh = new ArrayList<BmHistoNpkPhDao>();
	private List<BmPlanningDao> planning = new ArrayList<BmPlanningDao>();
	
	public BmMetierParcelle() {
		super();
	}

	public List<BmMetierHistoCulture> getHistoCulture() {
		return histoCulture;
	}

	public void setHistoCulture(List<BmMetierHistoCulture> histoCulture) {
		this.histoCulture = histoCulture;
	}

	public List<BmHistoNpkPhDao> getHistoNpkPh() {
		return histoNpkPh;
	}

	public void setHistoNpkPh(List<BmHistoNpkPhDao> histoNpkPh) {
		this.histoNpkPh = histoNpkPh;
	}

	public List<BmPlanningDao> getPlanning() {
		return planning;
	}

	public void setPlanning(List<BmPlanningDao> planning) {
		this.planning = planning;
	}

}
