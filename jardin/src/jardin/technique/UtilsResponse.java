package jardin.technique;

import javax.ws.rs.core.Response;

public  class UtilsResponse {
	public Response buildResponse(Object o) {
		if(o == null) {
			return Response.status(204).build();
		}else {
			return Response.status(200).entity(o).build();
		}
	}
	
	public Response buildResponse(JardinException s) {
		return Response.status(500).entity(s).build();
	}
	
	public Response buildResponseUpdate() {
		return Response.status(204).build();
	}
	 
}
