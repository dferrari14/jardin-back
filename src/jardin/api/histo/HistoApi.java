package jardin.api.histo;

import javax.ws.rs.core.Response;

import jardin.model.BmHistoCulture;


public interface HistoApi {
//	public Response getUnLegume(int id);
//	public Response getLegumes();
	public Response insererHistoCulture(BmHistoCulture b);
}
