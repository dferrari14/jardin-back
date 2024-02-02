package jardin.api.histo;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmHistoNpkPhDao;


public interface HistoApi {
//	public Response getUnLegume(int id);
//	public Response getLegumes();
	public Response insererHistoCulture(BmHistoCultureDao b);
	public Response insererHistoNpkPh(BmHistoNpkPhDao b,boolean modif);
	public Response getHistoNpkPh(int i,int dateReleve);
}
