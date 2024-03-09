package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.semis.SemisApi;
import jardin.api.semis.SemisFactory;
import jardin.model.dao.BmSemisDao;
import jardin.model.metier.BmSemisMetier;

@Path("semis")
public class ControllerSemis {
	private SemisApi m =SemisFactory.getInstance();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getListeSemis() {
		return m.getListeSemis();
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insererSemis(BmSemisMetier b) {
		return m.insererOuModifierSemis(b, true);
	}
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	public  Response ModifierSemis(BmSemisMetier b) {
		return m.insererOuModifierSemis(b, false);
	}
	
	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getSemis(
			 @PathParam("id") int id) {
		return m.getSemis(id);
	}
	
	//liste legume sans fiche semis
	@Path("/legume")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getLegumeFiltre() {
		return m.getListeLegumeFiltre();
	} 
}
