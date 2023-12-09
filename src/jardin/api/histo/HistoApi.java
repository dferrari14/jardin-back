package jardin.api.histo;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmHistoCultureDao;


public interface HistoApi {
//	public Response getUnLegume(int id);
//	public Response getLegumes();
	public Response insererHistoCulture(BmHistoCultureDao b);
}
