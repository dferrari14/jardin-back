package jardin.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.histo.HistoApi;
import jardin.api.histo.HistoFactory;
import jardin.model.dao.BmHistoCultureDao;


 //http://localhost:8080/jardin/api/jardin/legumes
@Path("histoCulture")
public class ControllerHistoCulture {
	private HistoApi m =HistoFactory.getInstance();
	
//	//@Path(".")
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//	public  Response getAllLegumes() {
//		return m.getLegumes();
//	}
//	
//
//	@Path("/{id}")
//	@GET
//    @Produces(MediaType.APPLICATION_JSON)
//	public  Response getUnLegume(
//			 @PathParam("id") int id) {
//		return m.getUnLegume(id);
//	}
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insertHistoCulture(
			BmHistoCultureDao b) {
		return m.insererHistoCulture(b);
	}

}
