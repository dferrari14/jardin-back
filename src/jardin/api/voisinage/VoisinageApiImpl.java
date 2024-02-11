package jardin.api.voisinage;

import java.util.List;

import javax.ws.rs.core.Response;

import jardin.dao.voisinage.VoisinageLegumeDao;
import jardin.metier.Controle;
import jardin.model.dao.BmVoisinageLegumeDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;

public class VoisinageApiImpl extends UtilsResponse implements VoisinageApi {

	@Override
	public Response insererOuModifierVoisinageLegume(BmVoisinageLegumeDao b, boolean ajout) {
		try {
			Controle.controleVoisinageLegume(b);
			if (ajout) {
				VoisinageLegumeDao.ajouterVoisinageLegume(b);
				// on inscrit la reciproque si pas deja inserer avant la mise en
				// place de la reciproque
			} else {
				VoisinageLegumeDao.updateVoisinageCulture(b);
			}

			List<BmVoisinageLegumeDao> l = VoisinageLegumeDao.getListeVoisinageLegume(b.getIdLegumeVoisinage(),
					b.getType());
			boolean trouve = false;
			for (BmVoisinageLegumeDao bvl : l) {
				if (bvl.getIdLegumeVoisinage() == b.getIdLegume()) {
					trouve = true;
				}
			}

			if (!trouve) {

				BmVoisinageLegumeDao bvl = new BmVoisinageLegumeDao();
				bvl.setIdLegume(b.getIdLegumeVoisinage());
				bvl.setIdLegumeVoisinage(b.getIdLegume());
				bvl.setType(b.getType());
				bvl.setDescription(b.getDescription());
				if (ajout) {
					VoisinageLegumeDao.ajouterVoisinageLegume(bvl);
				} else {
					VoisinageLegumeDao.updateVoisinageCulture(bvl);
				}
			}

			return buildResponseUpdate();
		} catch (JardinException s) {
			return buildResponse(s);
		}
	}

	@Override
	public Response getVoisinageLegume(int idLegume) {
		try {
			return buildResponse(VoisinageLegumeDao.getListeVoisinageLegume(idLegume));
		} catch (JardinException e) {
			return buildResponse(e);
		}
	}

//	@Override
//	public Response getHistoCultureByIdHisto(int idHisto) {
//		try {
//			return buildResponse(HistoCultureDao.getHistoCultureByIdHisto(idHisto));
//		} catch (JardinException e) {
//			return buildResponse(e);
//		}	 
//	}

}
