package jardin.main;

import javax.ws.rs.ext.Provider;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;



@Provider
public class CORSFilterRequest implements ContainerRequestFilter {

	@Override
	public ContainerRequest filter(ContainerRequest arg0) {
		return arg0;
	}
   
}
