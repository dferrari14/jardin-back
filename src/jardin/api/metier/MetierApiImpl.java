package jardin.api.metier;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import jardin.dao.historique.HistoCultureDao;
import jardin.dao.legume.LegumeDao;
import jardin.dao.parcelle.ParcelleDao;
import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmParcelleDao;
import jardin.model.metier.BmHistoCultureMetier;
import jardin.model.metier.BmParcelleMetier;
import jardin.technique.JardinException;
import jardin.technique.UtilsResponse;


public class MetierApiImpl  extends UtilsResponse implements  MetierApi{	 

	@Override
	public Response getMetierParcelle(int idParcelle) {
		try {
			BmParcelleDao b =ParcelleDao.getParcelle(idParcelle);
			List<BmHistoCultureDao> lHisto = HistoCultureDao.getHistoCultureParcelle(idParcelle);
			List<BmHistoCultureMetier> lHistoMetier = new ArrayList<BmHistoCultureMetier>();
			for(BmHistoCultureDao unHisto:lHisto) {
				LegumeDao.getLegume(unHisto.getIdLegume()).getNom();
				BmHistoCultureMetier unHCM = new BmHistoCultureMetier();
				unHCM.setIdHisto(unHisto.getIdHisto());
				unHCM.setIdParcelle(unHisto.getIdHisto());
				unHCM.setIdLegume(unHisto.getIdLegume());
				unHCM.setNomLegume(LegumeDao.getLegume(unHisto.getIdLegume()).getNom());
				unHCM.setDateDebut(unHisto.getDateDebut());
				unHCM.setDateFin(unHisto.getDateFin());
				unHCM.setEncombrement(unHisto.getEncombrement());
				unHCM.setObservation(unHisto.getObservation());
				unHCM.setRendement(unHisto.getRendement());
				lHistoMetier.add(unHCM);
			}
			
			BmParcelleMetier bm = new BmParcelleMetier();
			bm.setIdParcelle(b.getIdParcelle());
			bm.setLargeur(b.getLargeur());
			bm.setLongueur(b.getLongueur());
			bm.setNom(b.getNom());
			
			bm.setHistoCulture(lHistoMetier);
				
			return buildResponse(bm);
		} catch (JardinException e) {
			return buildResponse(e);
		}	
	}
	




}
