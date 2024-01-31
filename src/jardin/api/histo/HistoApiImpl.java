package jardin.api.histo;

import javax.ws.rs.core.Response;

import jardin.dao.historique.HistoCultureDao;
import jardin.dao.historique.HistoNpkPhDao;
import jardin.metier.Controle;
import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmHistoNpkPhDao;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;


public class HistoApiImpl  extends UtilsResponse implements  HistoApi{

//	@Override
//	public Response getUnLegume(int id) {
//			try {
//				return buildResponse(LegumeDao.getLegume(id));
//			} catch (JardinException e) {
//				return buildResponse(e);
//			}	 
//	}
//
//	@Override
//	public Response getLegumes() {
//		try {
//			return buildResponse(LegumeDao.getListeLegumes());
//		} catch (JardinException e) {
//			return buildResponse(e);
//		}	
//	}

	@Override
	public Response insererHistoCulture(BmHistoCultureDao b) {
		try {
			Controle.controleHistoCulture(b);
			HistoCultureDao.ajouterHistoCulture(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
 
	}

	@Override
	public Response insererHistoNpkPh(BmHistoNpkPhDao b) {
		try {
			Controle.controleHistoNpkPh(b);
			HistoNpkPhDao.ajouterHistoNpkPh(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
	}

	@Override
	public Response getHistoNpkPh(int i,int dateReleve) {
		try {
			return buildResponse(HistoNpkPhDao.getHistoNpkPhParcelle(i,dateReleve));
		} catch (JardinException e) {
			return buildResponse(e);
		}	 
	}


}
