package jardin.api.voisinage;

import javax.ws.rs.core.Response;

import jardin.model.dao.BmVoisinageLegumeDao;


public interface VoisinageApi {
	public Response insererOuModifierVoisinageLegume(BmVoisinageLegumeDao b,boolean modif);
	public Response getVoisinageLegume(int idLegume);
	public Response getListeLegumeVoisinageFiltre(int idLegume);
}
