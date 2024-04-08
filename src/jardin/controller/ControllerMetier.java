package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.metier.MetierApi;
import jardin.api.metier.MetierFactory;


 //http://localhost:8080/jardin/api/jardin/legumes
@Path("metier")
public class ControllerMetier {
	private MetierApi m = MetierFactory.getInstance();
	
	@Path("planning")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getPlanning() {
		return m.getPlanning();
	}
	 

	@Path("parcelle/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getUneParcelle(
			 @PathParam("id") int id) {
		return m.getMetierParcelle(id);
	}
	
	@Path("parcelles")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getMetierParcelles() {
		return m.getMetierParcelles();
	}
	
 
}
