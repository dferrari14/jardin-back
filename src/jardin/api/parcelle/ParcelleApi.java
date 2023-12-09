package jardin.api.parcelle;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmParcelleDao;


public interface ParcelleApi {
	public Response getUneParcelle(int id);
	public Response getParcelles();
	public Response insererParcelle(BmParcelleDao b);
	public Response modifierParcelle(BmParcelleDao b);
}
