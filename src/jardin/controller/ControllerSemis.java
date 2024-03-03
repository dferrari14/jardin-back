package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.semis.SemisApi;
import jardin.api.semis.SemisFactory;
import jardin.model.dao.BmSemisDao;

@Path("semis")
public class ControllerSemis {
	private SemisApi m =SemisFactory.getInstance();
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insererOuModifierSemis(
			BmSemisDao b,@QueryParam("ajout")boolean ajout) {
		return m.insererOuModifierSemis(b, ajout);
	}
	
	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getSemis(
			 @PathParam("id") int id) {
		return m.getSemis(id);
	}
	
	 
}
