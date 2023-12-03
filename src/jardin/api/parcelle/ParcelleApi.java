package jardin.api.parcelle;

import javax.ws.rs.core.Response;

import jardin.model.BmParcelle;


public interface ParcelleApi {
	public Response getUneParcelle(int id);
	public Response getParcelles();
	public Response insererParcelle(BmParcelle b);
	public Response modifierParcelle(BmParcelle b);
}
