package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.parcelle.ParcelleApi;
import jardin.api.parcelle.ParcelleFactory;
import jardin.model.dao.BmParcelleDao;


 //http://localhost:8080/jardin/api/jardin/legumes
@Path("parcelle")
public class ControllerParcelle {
	private ParcelleApi m = ParcelleFactory.getInstance();
	
	//@Path(".")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getListeParcelles() {
		return m.getParcelles();
	}
	

	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getUneParcelle(
			 @PathParam("id") int id) {
		return m.getUneParcelle(id);
	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insertParcelle(
			BmParcelleDao b) {
		return m.insererParcelle(b);
	}
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
	public  Response modifierParcelle(
			BmParcelleDao b) {
		return m.modifierParcelle(b);
	}

}
