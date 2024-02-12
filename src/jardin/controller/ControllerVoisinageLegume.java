package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.voisinage.VoisinageApi;
import jardin.api.voisinage.VoisinageFactory;
import jardin.model.dao.BmVoisinageLegumeDao;

@Path("voisinageLegume")
public class ControllerVoisinageLegume {
	private VoisinageApi m =VoisinageFactory.getInstance();
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insererVoisinageLegume(
			BmVoisinageLegumeDao b,@QueryParam("ajout")boolean ajout) {
		return m.insererOuModifierVoisinageLegume(b,ajout);
	}
	
	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getVoisinageLegume(
			 @PathParam("id") int id) {
		return m.getVoisinageLegume(id);
	}
	
	@Path("/listeLegumeFiltre/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getListeLegumeFiltre(
			 @PathParam("id") int id) {
		return m.getListeLegumeVoisinageFiltre(id);
	}
}
