package jardin.api.semis;

import javax.ws.rs.core.Response;

import jardin.dao.semis.SemisDao;
import jardin.metier.Controle;
import jardin.model.dao.BmSemisDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;

public class SemisApiImpl extends UtilsResponse implements SemisApi {
	 

	@Override
	public Response insererOuModifierSemis(BmSemisDao b, boolean ajout) {
		try {
			Controle.controleSemis(b);
			if (ajout) {
				SemisDao.insererSemis(b);
			
			} else {
				SemisDao.updateSemis(b);
			}

			return buildResponseUpdate();
		} catch (JardinException s) {
			return buildResponse(s);
		}
	}

	@Override
	public Response getSemis(int idLegume) {
		try {
			return buildResponse(SemisDao.getSemis(idLegume));
		} catch (JardinException e) {
			return buildResponse(e);
		}
	}
	
}
