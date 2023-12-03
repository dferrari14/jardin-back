package jardin.api.legume;

import javax.ws.rs.core.Response;

import jardin.dao.legume.LegumeDao;
import jardin.metier.Controle;
import jardin.model.BmLegume;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;


public class LegumeApiImpl  extends UtilsResponse implements  LegumeApi{

	@Override
	public Response getUnLegume(int id) {
			try {
				return buildResponse(LegumeDao.getLegume(id));
			} catch (JardinException e) {
				return buildResponse(e);
			}	 
	}

	@Override
	public Response getLegumes() {
		try {
			return buildResponse(LegumeDao.getListeLegumes());
		} catch (JardinException e) {
			return buildResponse(e);
		}	
	}

	@Override
	public Response insererLegume(BmLegume b) {
		try {
			Controle.controleLegume(b);
			LegumeDao.ajouterLegume(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
 
	}

	@Override
	public Response modifierLegume(BmLegume b) {
		try {
			Controle.controleLegume(b);
			LegumeDao.modifierLegume(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
	}
	




}
