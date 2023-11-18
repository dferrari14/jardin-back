package jardin.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jardin.constante.CsteJardin;
import jardin.dao.historique.HistoCultureDao;
import jardin.dao.legume.LegumeDao;
import jardin.dao.voisinage.VoisinageLegumeDao;
import jardin.dao.voisinage.VoisinageParcelleDao;
import jardin.model.BmHistoCulture;
import jardin.model.BmLegume;
import jardin.model.BmVoisinageLegume;
import jardin.model.BmVoisinageParcelle;
import jardin.technique.JardinException;
import jardin.technique.Utils;
import jardin.technique.UtilsDate;

public class MetierCulture {
	public static List<BmLegume> getListeAuthorizedLegume(int idParcelle, Date dateCulture) throws JardinException {
		List<BmLegume> l = new ArrayList<BmLegume>();

		// 1°) recup histo culture de la parcelle
		List<BmHistoCulture> lHistoCulture = HistoCultureDao.getHistoCultureParcelle(idParcelle);

		// 2°)determination prochain type de legume authorisée
		String nextTypeCulture = CsteJardin.TYPE_CULTURE_LIBRE;
		if (lHistoCulture != null && lHistoCulture.isEmpty()) {
			int previousIdCulture = lHistoCulture.get(0).getIdLegume();
			BmLegume previousLegume = LegumeDao.getLegume(previousIdCulture);
			String previousTypeCulture = previousLegume.getType();
			nextTypeCulture = generateNextTypeLegumeRotation(previousTypeCulture);
			if (nextTypeCulture == null) {
				throw new JardinException("impossible de determiner le prochain type de culture avec previous type ; "
						+ previousTypeCulture);
			}
		}

		// 3°)recuperation liste de legume du type authorisée
		List<BmLegume> lTempAuthorizedLegume = LegumeDao.getLegumes(nextTypeCulture);
		List<BmLegume> lAuthorizedLegume = new ArrayList<BmLegume>();
		// 4°)gestion exception (exemple pas de poireaux au meme endroit avant 3 ans (à
		// verifier)
		for (BmLegume unLegume : lTempAuthorizedLegume) {
			if (unLegume.getNbAnneeRotation() > 0) {
				Date minDateCulturePrecedente = UtilsDate.soustractAnnees(dateCulture, unLegume.getNbAnneeRotation());
				for (BmHistoCulture h : lHistoCulture) {
					if (UtilsDate.dateAfterD1D2(h.getDateDebut(), minDateCulturePrecedente)) {
						if (h.getIdLegume() == unLegume.getIdLegume()) {
							// on ne garde pas
							break;
						}
					} else {
						lAuthorizedLegume.add(unLegume);
					}
				}
			}
		}

		// 5°)recuperation liste des parcelles voisines
		List<BmVoisinageParcelle> lParcelleVoisine = VoisinageParcelleDao.getListeVoisinageParcelle(idParcelle);
		List<BmLegume> lListeLegumeOK = new ArrayList<BmLegume>();
		//liste d'id de legume ok en voisinage pour les parcelle deja detérminé
		List<Integer> lVoisinageOK =  new ArrayList<Integer>();
		
		for (BmLegume unLegume : lAuthorizedLegume) {
			// on peut avoir plusieurs culture sur une parcelle (colonne encombrement =demi)
			boolean keep = true;
			for (BmVoisinageParcelle uneParcelleVoisinage : lParcelleVoisine) {				
				// 6°)recuperation culture en cours ou a venir des parcelles voisines
				List<BmHistoCulture> lHisto = HistoCultureDao.getHistoCultureParcelleByDate(uneParcelleVoisinage.getIdParcelleVoisinage(), dateCulture);
				// 7°)gestion du voisinage  interdit (suppression des legumes interdit)
				
				for (BmHistoCulture unHisto : lHisto) {					
					List<BmVoisinageLegume> lVoisinageKO = VoisinageLegumeDao.getListeVoisinageLegumeKO(unHisto.getIdLegume());					
					for (BmVoisinageLegume unVoisinageKO : lVoisinageKO) {
						// si le legume non recommandé est sur la parcelle voisine on ne garde pas le legume prevu en culture
						if (unVoisinageKO.getIdLegumeVoisinage() == unLegume.getIdLegume()) {
							keep = false;
							break;
						}
					}
					//on alimente la liste de voisinage ok au passage (possible doublon mais pas grave)
					List<BmVoisinageLegume> lTempVoisinageOK =VoisinageLegumeDao.getListeVoisinageLegumeOK(unHisto.getIdLegume());
					for (BmVoisinageLegume unVoisinageOK : lTempVoisinageOK) {
						lVoisinageOK.add(unVoisinageOK.getIdLegumeVoisinage());
					}					
					
					if (!keep) {
						break;
					}
				}
			}
			
			if (keep) {
				lListeLegumeOK.add(unLegume);
			}
		}
		
		//Gestion des legumes OK 
		//classement de la liste de legume authorisée par ordre d'interet
		BmLegume[] tempTabLegume = new BmLegume[lListeLegumeOK.size()];
		for(BmLegume unLegume:lListeLegumeOK) {
			if(Utils.isListeContainLegume(lVoisinageOK, unLegume.getIdLegume())) {
				ajouterLegumeDebutTableau(tempTabLegume, unLegume);
			}else {
				ajouterLegumeFinTableau(tempTabLegume, unLegume);
			}
			
		}
	
		return l;
	}

	private static void ajouterLegumeDebutTableau(BmLegume[] tempTabLegume,BmLegume b) {
		
	}
	
	private static void ajouterLegumeFinTableau(BmLegume[] tempTabLegume,BmLegume b) {
		tempTabLegume[tempTabLegume.length]=b;
	}
	
	private static String generateNextTypeLegumeRotation(String type) {
		if (type.equalsIgnoreCase(CsteJardin.TYPE_CULTURE_BULBE)
				|| type.equalsIgnoreCase(CsteJardin.TYPE_CULTURE_BULBE)) {
			return CsteJardin.TYPE_CULTURE_FRUIT;
		} else if (type.equalsIgnoreCase(CsteJardin.TYPE_CULTURE_FRUIT)) {
			return CsteJardin.TYPE_CULTURE_LEGUMINEUSE;
		} else if (type.equalsIgnoreCase(CsteJardin.TYPE_CULTURE_LEGUMINEUSE)) {
			return CsteJardin.TYPE_CULTURE_FEUILLE;
		} else if (type.equalsIgnoreCase(CsteJardin.TYPE_CULTURE_FEUILLE)) {
			return CsteJardin.TYPE_CULTURE_BULBE_RACINE;
		}

		return null;
	}

}
