package jardin.controller;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jardin.api.histo.HistoApi;
import jardin.api.histo.HistoFactory;
import jardin.model.dao.BmHistoCultureDao;

@Path("histoCulture")
public class ControllerHistoCulture {
	private HistoApi m =HistoFactory.getInstance();
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
	public  Response insertHistoCulture(
			BmHistoCultureDao b) {
		return m.insererHistoCulture(b);
	}
	
	@Path("/{id}")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public  Response getHistoCultureByIdHisto(
			@PathParam("id") int id) {
		return m.getHistoCultureByIdHisto(id);
	}

}
