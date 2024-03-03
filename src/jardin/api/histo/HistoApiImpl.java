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
	public Response modifierHistoCulture(BmHistoCultureDao b) {
		try {
			Controle.controleHistoCulture(b);
			HistoCultureDao.updateHistoCulture(b);
			return buildResponseUpdate();
		}
		catch (JardinException s) {
			return buildResponse(s);
		}
	}

	@Override
	public Response insererHistoNpkPh(BmHistoNpkPhDao b,boolean modif) {
		try {
			Controle.controleHistoNpkPh(b);
			if(modif) {
				HistoNpkPhDao.updateHistoNpkPh(b);
			}else {
				HistoNpkPhDao.ajouterHistoNpkPh(b);
			}
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

	@Override
	public Response getHistoCultureByIdHisto(int idHisto) {
		try {
			return buildResponse(HistoCultureDao.getHistoCultureByIdHisto(idHisto));
		} catch (JardinException e) {
			return buildResponse(e);
		}	 
	}

	


}
