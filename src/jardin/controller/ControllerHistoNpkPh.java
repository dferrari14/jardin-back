package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.histo.HistoApi;
import jardin.api.histo.HistoFactory;
import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmHistoNpkPhDao;

@Path("histoNpkPh")
public class ControllerHistoNpkPh {
	private HistoApi m =HistoFactory.getInstance();
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insererHistoNpkPh(
			BmHistoNpkPhDao b) {
		return m.insererHistoNpkPh(b);
	}
	
	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getUnLegume(
			 @PathParam("id") int id,@QueryParam("dateReleve")int dateReleve) {
		return m.getHistoNpkPh(id,dateReleve);
	}

}
