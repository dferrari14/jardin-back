package jardin.api.parcelle;

import javax.ws.rs.core.Response;

import jardin.dao.parcelle.ParcelleDao;
import jardin.metier.Controle;
import jardin.model.dao.BmParcelleDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;


public class ParcelleApiImpl  extends UtilsResponse implements  ParcelleApi{

	@Override
	public Response getUneParcelle(int id) {
			try {
				return buildResponse(ParcelleDao.getParcelle(id));
			} catch (JardinException e) {
				return buildResponse(e);
			}	 
	}

	@Override
	public Response getParcelles() {
		try {
			return buildResponse(ParcelleDao.getListeParcelle());
		} catch (JardinException e) {
			return buildResponse(e);
		}	
	}

	@Override
	public Response insererParcelle(BmParcelleDao b) {
		try {
			Controle.controleParcelle(b);
			ParcelleDao.ajouterParcelle(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
 
	}

	@Override
	public Response modifierParcelle(BmParcelleDao b) {
		try {
			Controle.controleParcelle(b);
			ParcelleDao.modifierParcelle(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
	}
	




}
