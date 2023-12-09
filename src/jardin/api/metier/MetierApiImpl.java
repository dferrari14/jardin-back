package jardin.api.metier;

import javax.ws.rs.core.Response;

import jardin.dao.parcelle.ParcelleDao;
import jardin.model.dao.BmParcelleDao;
import jardin.model.metier.BmParcelleMetier;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;


public class MetierApiImpl  extends UtilsResponse implements  MetierApi{	 

	@Override
	public Response getMetierParcelle(int idParcelle) {
		try {
			BmParcelleDao b =ParcelleDao.getParcelle(idParcelle);
			BmParcelleMetier bm = new BmParcelleMetier();
			bm.setIdParcelle(b.getIdParcelle());
			bm.setLargeur(b.getLargeur());
			bm.setLongueur(b.getLongueur());
			bm.setNom(b.getNom());
				
			return buildResponse(b);
		} catch (JardinException e) {
			return buildResponse(e);
		}	
	}
	




}
