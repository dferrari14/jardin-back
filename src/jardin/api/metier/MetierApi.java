package jardin.api.metier;

import javax.ws.rs.core.Response;


public interface MetierApi {
	public Response getMetierParcelle(int idParcelle);
}