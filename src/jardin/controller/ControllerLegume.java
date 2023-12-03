package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.legume.LegumeApi;
import jardin.api.legume.LegumeFactory;
import jardin.model.BmLegume;
import jardin.model.BmParcelle;


 //http://localhost:8080/jardin/api/jardin/legumes
@Path("legume")
public class ControllerLegume {
	private LegumeApi m = LegumeFactory.getInstance();
	
	//@Path(".")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getAllLegumes() {
		return m.getLegumes();
	}
	

	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getUnLegume(
			 @PathParam("id") int id) {
		return m.getUnLegume(id);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insertLegume(
			BmLegume b) {
		return m.insererLegume(b);
	}

	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	public  Response modifierParcelle(
			BmLegume b) {
		return m.modifierLegume(b);
	}
}
