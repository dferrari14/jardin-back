package jardin.api.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.Response;

import jardin.constante.CsteJardin;
import jardin.dao.historique.HistoCultureDao;
import jardin.dao.historique.HistoNpkPhDao;
import jardin.dao.legume.LegumeDao;
import jardin.dao.parcelle.ParcelleDao;
import jardin.dao.planning.PlanningDao;
import jardin.dao.semis.SemisDao;
import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmHistoNpkPhDao;
import jardin.model.dao.BmLegumeDao;
import jardin.model.dao.BmParcelleDao;
import jardin.model.dao.BmPlanningDao;
import jardin.model.dao.BmSemisDao;
import jardin.model.metier.BmMetierHistoCulture;
import jardin.model.metier.BmMetierParcelle;
import jardin.model.metier.BmMetierPlanning;
import jardin.technique.JardinException;
import jardin.technique.Utils;
import jardin.technique.UtilsDate;
import jardin.technique.UtilsResponse;

public class MetierApiImpl extends UtilsResponse implements MetierApi {

	@Override
	public Response getMetierParcelle(int idParcelle) {
		try {
			return buildResponse(getBmMetierParcelle(idParcelle, false));
		} catch (JardinException e) {
			return buildResponse(e);
		}
	}

	private BmMetierParcelle getBmMetierParcelle(int idParcelle, boolean enCours) throws JardinException {

		BmParcelleDao b = ParcelleDao.getParcelle(idParcelle);
		List<BmHistoCultureDao> lHisto = HistoCultureDao.getHistoCultureParcelle(idParcelle);
		List<BmMetierHistoCulture> lHistoMetier = new ArrayList<BmMetierHistoCulture>();
		for (BmHistoCultureDao unHisto : lHisto) {
			LegumeDao.getLegume(unHisto.getIdLegume()).getNom();
			if (!enCours || (enCours && Utils.isNullOrEmpty(unHisto.getDateFin()))) {
				BmMetierHistoCulture unHCM = new BmMetierHistoCulture();
				unHCM.setIdHisto(unHisto.getIdHisto());
				unHCM.setIdParcelle(unHisto.getIdHisto());
				unHCM.setIdLegume(unHisto.getIdLegume());
				unHCM.setType(unHisto.getType());
				unHCM.setNomLegume(LegumeDao.getLegume(unHisto.getIdLegume()).getNom());
				unHCM.setDateDebut(unHisto.getDateDebut());
				unHCM.setDateFin(unHisto.getDateFin());
				unHCM.setEncombrement(unHisto.getEncombrement());
				unHCM.setObservation(unHisto.getObservation());
				unHCM.setRendement(unHisto.getRendement());
				lHistoMetier.add(unHCM);
			}
		}

		List<BmHistoNpkPhDao> lHistoNpk = HistoNpkPhDao.getHistoNpkPhParcelle(idParcelle);
		List<BmPlanningDao> lPlanning = PlanningDao.getPlanningParcelle(idParcelle);

		BmMetierParcelle bm = new BmMetierParcelle();
		bm.setIdParcelle(b.getIdParcelle());
		bm.setLargeur(b.getLargeur());
		bm.setLongueur(b.getLongueur());
		bm.setNom(b.getNom());
		bm.setExposition(b.getExposition());
		bm.setHistoNpkPh(lHistoNpk);
		bm.setHistoCulture(lHistoMetier);
		bm.setPlanning(lPlanning);

		return bm;

	}

	// pour affichage generale dans jardin
	// pon veut que les cultues en cours ou a venir
	@Override
	public Response getMetierParcelles() {
		List<BmParcelleDao> l = new ArrayList<BmParcelleDao>();
		try {
			l = ParcelleDao.getListeParcelle();
		} catch (JardinException e) {
			return buildResponse(e);
		}
		List<BmMetierParcelle> lReturn = new ArrayList<BmMetierParcelle>();
		for (BmParcelleDao uneP : l) {
			try {
				lReturn.add(getBmMetierParcelle(uneP.getIdParcelle(), true));
			} catch (JardinException e) {
				return buildResponse(e);
			}
		}

		return buildResponse(lReturn);
	}

	@Override
	public Response getPlanning() {
		List<BmMetierPlanning> lMetierPlanning = new ArrayList<BmMetierPlanning>();

		try {
			List<BmHistoCultureDao> lCurrentCulture = HistoCultureDao.getHistoCultureEncours();
			for (BmHistoCultureDao currentCulture : lCurrentCulture) {
				// lecture pour nom legume et parcelle
				BmLegumeDao unLegume = LegumeDao.getLegume(currentCulture.getIdLegume());
				BmParcelleDao uneParcelle = ParcelleDao.getParcelle(currentCulture.getIdParcelle());
				// culturer prevue et pas commencé , on va chercher les dates de semis
				if (Utils.isNullOrEmpty(currentCulture.getDateDebut())) {
					BmMetierPlanning unMetierPlanning = new BmMetierPlanning();

					BmSemisDao unSemis = SemisDao.getSemis(currentCulture.getIdLegume());
					if (unSemis != null) {
						// tache semis
						unMetierPlanning.setIdLegume(currentCulture.getIdLegume());
						unMetierPlanning.setNomLegume(unLegume.getNom());
						unMetierPlanning.setIdParcelle(currentCulture.getIdParcelle());
						unMetierPlanning.setNomParcelle(uneParcelle.getNom());
						unMetierPlanning.setTache("SEMIS " + unLegume.getNom());
						

						unMetierPlanning.setDateDebut(
								UtilsDate.getPremierJourMoisAAAAMMJJFromLibelleMois(unSemis.getMoisMinSemis()));
						unMetierPlanning.setDateMax(
								UtilsDate.getDernierJourMoisAAAAMMJJFromLibelleMois(unSemis.getMoisMaxSemis()));
						
						if (UtilsDate.dateAfterOrEqualsD1D2(UtilsDate.getAAAAMMJJ(new Date()),unMetierPlanning.getDateMax())) {   						 
							unMetierPlanning.setEtat(CsteJardin.ETAT_PLANNING_URGENT);
						} else {
							if (UtilsDate.dateAfterOrEqualsD1D2(UtilsDate.getAAAAMMJJ(new Date()),unMetierPlanning.getDateDebut())) {  
								unMetierPlanning.setEtat(CsteJardin.ETAT_PLANNING_A_FAIRE);
							} else {
								unMetierPlanning.setEtat(CsteJardin.ETAT_PLANNING_EN_PREVISION); // TROP TOT
							}
						}
						
						

						lMetierPlanning.add(unMetierPlanning);
					}

				}

				// TODO tache amendement

				List<BmPlanningDao> lPlanning = PlanningDao.getPlanningParcelle(currentCulture.getIdParcelle());
				for (BmPlanningDao unPlanning : lPlanning) {
					// lecture pour nom legume et parcelle
					BmMetierPlanning unMetierPlanning = new BmMetierPlanning();
					unMetierPlanning.setIdLegume(currentCulture.getIdLegume());
					unMetierPlanning.setNomLegume(unLegume.getNom());
					unMetierPlanning.setIdParcelle(currentCulture.getIdParcelle());
					unMetierPlanning.setNomParcelle(uneParcelle.getNom());
					unMetierPlanning.setTache(unPlanning.getTache());
					unMetierPlanning.setDateDebut(unPlanning.getDateDebut());
					unMetierPlanning.setDateMax(unPlanning.getDateMax());

					lMetierPlanning.add(unMetierPlanning);
				}
			}

		} catch (JardinException e) {
			return buildResponse(e);
		}

		return buildResponse(lMetierPlanning);
	}

}
