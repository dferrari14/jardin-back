package jardin.api.legume;

import javax.ws.rs.core.Response;

import jardin.model.BmLegume;


public interface LegumeApi {
	public Response getUnLegume(int id);
	public Response getLegumes();
	public Response insererLegume(BmLegume b);
}
