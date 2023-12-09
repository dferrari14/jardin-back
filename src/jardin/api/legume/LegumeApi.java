package jardin.api.legume;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmLegumeDao;


public interface LegumeApi {
	public Response getUnLegume(int id);
	public Response getLegumes();
	public Response insererLegume(BmLegumeDao b);
	public Response modifierLegume(BmLegumeDao b);
}
