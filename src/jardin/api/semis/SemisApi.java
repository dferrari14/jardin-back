package jardin.api.semis;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmSemisDao;


public interface SemisApi {
	public Response insererOuModifierSemis(BmSemisDao b,boolean ajout);
	public Response getSemis(int idLegume);
}
