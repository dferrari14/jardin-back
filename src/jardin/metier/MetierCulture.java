package jardin.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jardin.constante.CsteJardin;
import jardin.dao.historique.HistoCultureDao;
import jardin.dao.legume.LegumeDao;
import jardin.dao.voisinage.VoisinageLegumeDao;
import jardin.dao.voisinage.VoisinageParcelleDao;
import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmLegumeDao;
import jardin.model.dao.BmVoisinageLegumeDao;
import jardin.model.dao.BmVoisinageParcelleDao;
import jardin.technique.JardinException;
import jardin.technique.Utils;
import jardin.technique.UtilsDate;

public class MetierCulture {
	public static List<BmLegumeDao> getListeAuthorizedLegume(int idParcelle, Date dateCulture) throws JardinException {
		List<BmLegumeDao> l = new ArrayList<BmLegumeDao>();

		// 1°) recup histo culture de la parcelle
		List<BmHistoCultureDao> lHistoCulture = HistoCultureDao.getHistoCultureParcelle(idParcelle);

		// 2°)determination prochain type de legume authorisée
		String nextTypeCulture = CsteJardin.TYPE_CULTURE_LIBRE;
		if (lHistoCulture != null && lHistoCulture.isEmpty()) {
			int previousIdCulture = lHistoCulture.get(0).getIdLegume();
			BmLegumeDao previousLegume = LegumeDao.getLegume(previousIdCulture);
			String previousTypeCulture = previousLegume.getType();
			nextTypeCulture = generateNextTypeLegumeRotation(previousTypeCulture);
			if (nextTypeCulture == null) {
				throw new JardinException("impossible de determiner le prochain type de culture avec previous type ; "
						+ previousTypeCulture);
			}
		}

		// 3°)recuperation liste de legume du type authorisée
		List<BmLegumeDao> lTempAuthorizedLegume = LegumeDao.getLegumes(nextTypeCulture);
		List<BmLegumeDao> lAuthorizedLegume = new ArrayList<BmLegumeDao>();
		// 4°)gestion exception (exemple pas de poireaux au meme endroit avant 3 ans (à
		// verifier)
		for (BmLegumeDao unLegume : lTempAuthorizedLegume) {
			if (unLegume.getNbAnneeRotation() > 0) {
				Date minDateCulturePrecedente = UtilsDate.soustractAnnees(dateCulture, unLegume.getNbAnneeRotation());
				for (BmHistoCultureDao h : lHistoCulture) {
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
		List<BmVoisinageParcelleDao> lParcelleVoisine = VoisinageParcelleDao.getListeVoisinageParcelle(idParcelle);
		List<BmLegumeDao> lListeLegumeOK = new ArrayList<BmLegumeDao>();
		//liste d'id de legume ok en voisinage pour les parcelle deja detérminé
		List<Integer> lVoisinageOK =  new ArrayList<Integer>();
		
		for (BmLegumeDao unLegume : lAuthorizedLegume) {
			// on peut avoir plusieurs culture sur une parcelle (colonne encombrement =demi)
			boolean keep = true;
			for (BmVoisinageParcelleDao uneParcelleVoisinage : lParcelleVoisine) {				
				// 6°)recuperation culture en cours ou a venir des parcelles voisines
				List<BmHistoCultureDao> lHisto = HistoCultureDao.getHistoCultureParcelleByDate(uneParcelleVoisinage.getIdParcelleVoisinage(), dateCulture);
				// 7°)gestion du voisinage  interdit (suppression des legumes interdit)
				
				for (BmHistoCultureDao unHisto : lHisto) {					
					List<BmVoisinageLegumeDao> lVoisinageKO = VoisinageLegumeDao.getListeVoisinageLegumeKO(unHisto.getIdLegume());					
					for (BmVoisinageLegumeDao unVoisinageKO : lVoisinageKO) {
						// si le legume non recommandé est sur la parcelle voisine on ne garde pas le legume prevu en culture
						if (unVoisinageKO.getIdLegumeVoisinage() == unLegume.getIdLegume()) {
							keep = false;
							break;
						}
					}
					//on alimente la liste de voisinage ok au passage (possible doublon mais pas grave)
					List<BmVoisinageLegumeDao> lTempVoisinageOK =VoisinageLegumeDao.getListeVoisinageLegumeOK(unHisto.getIdLegume());
					for (BmVoisinageLegumeDao unVoisinageOK : lTempVoisinageOK) {
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
		BmLegumeDao[] tempTabLegume = new BmLegumeDao[lListeLegumeOK.size()];
		for(BmLegumeDao unLegume:lListeLegumeOK) {
			if(Utils.isListeContainLegume(lVoisinageOK, unLegume.getIdLegume())) {
				ajouterLegumeDebutTableau(tempTabLegume, unLegume);
			}else {
				ajouterLegumeFinTableau(tempTabLegume, unLegume);
			}
			
		}
	
		return l;
	}

	private static void ajouterLegumeDebutTableau(BmLegumeDao[] tempTabLegume,BmLegumeDao b) {
		
	}
	
	private static void ajouterLegumeFinTableau(BmLegumeDao[] tempTabLegume,BmLegumeDao b) {
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
