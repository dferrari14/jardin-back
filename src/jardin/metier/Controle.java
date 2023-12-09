package jardin.metier;

import jardin.model.dao.BmHistoCultureDao;
import jardin.model.dao.BmLegumeDao;
import jardin.model.dao.BmParcelleDao;
import jardin.technique.JardinException;
import jardin.technique.Utils;
import jardin.technique.UtilsDate;

public class Controle {
	//legume
	private static final String BEAN_LEGUME_NULL = "bean legume null";
	private static final String NOM_LEGUME_OBL = "nom legume obligatoire";
	private static final String TYPE_OBL = "type legume obligatoire";
	private static final String FAMILLE_OBL = "famille legume obligatoire";
	private static final String EXPOSITION_LEGUME_OBL = "exposition legume obligatoire";
	//parcelle
	private static final String BEAN_PARCELLE_NULL = "bean parcelle null";
	private static final String NOM_PARCELLE_OBL = "nom parcelle obligatoire";
	private static final String LONGUEUR_OBL = "longueur parcell obligatoire";
	private static final String LARGEUR_OBL = "largeur parcelle obligatoire";
	private static final String EXPOSITION_PARCELLE_OBL = "exposition parcelle obligatoire";
	//histo culture
	private static final String BEAN_HISTO_NULL = "bean histo null";
	private static final String ID_PARCELLE_OBL = "id parcelle obl";
	private static final String ID_LEGUME_OBL = "id legume obl";
	private static final String DATE_DEBUT = "date debut histo";
	private static final String DATE_FIN = "date fin histo";
	private static final String ENCOMBREMENT_OBL = "ecombrement parcelle obligatoire";
	private static final String DATE_FIN_HISTO_INF_DATE_DEBUT= "date fin histo inferieur à la date de debut";
	
	public static void controleLegume(BmLegumeDao b) throws JardinException {
		if( b == null) {
			throw new JardinException(BEAN_LEGUME_NULL);
		}
		
		if(Utils.isNullOrEmpty(b.getNom())) {
			throw new JardinException(NOM_LEGUME_OBL);
		}
		
		if(Utils.isNullOrEmpty(b.getType())) {
			throw new JardinException(TYPE_OBL);
		}
		
		if(Utils.isNullOrEmpty(b.getFamille())) {
			throw new JardinException(FAMILLE_OBL);
		}
		
		if(Utils.isNullOrEmpty(b.getExposition())) {
			throw new JardinException(EXPOSITION_LEGUME_OBL);
		}
		
	}
	
	public static void controleParcelle(BmParcelleDao b) throws JardinException {
		if( b == null) {
			throw new JardinException(BEAN_PARCELLE_NULL);
		}
		
		if(Utils.isNullOrEmpty(b.getNom())) {
			throw new JardinException(NOM_PARCELLE_OBL);
		}
		
		if(Utils.isNullOrEmpty(b.getExposition())) {
			throw new JardinException(EXPOSITION_PARCELLE_OBL);
		}
		
		if(b.getLargeur() == 0) {
			throw new JardinException(LARGEUR_OBL);
		}
		
		if(b.getLongueur() == 0){
			throw new JardinException(LONGUEUR_OBL);
		}
	}
	
	public static void controleHistoCulture(BmHistoCultureDao b) throws JardinException {
		if( b == null) {
			throw new JardinException(BEAN_HISTO_NULL);
		}
	
		if(b.getIdLegume() == 0) {
			throw new JardinException(ID_LEGUME_OBL);
		}
		
		if(b.getIdParcelle() == 0) {
			throw new JardinException(ID_PARCELLE_OBL);
		}
		
		ControleDate.controleDateAAAAMMJJ_OBL(b.getDateDebut(), DATE_DEBUT);
		ControleDate.controleDateAAAAMMJJ_FAC(b.getDateFin(), DATE_FIN);
		
		if(Utils.isNullOrEmpty(b.getDateFin())) {
			if(!UtilsDate.afterAAAAMMD2D1(b.getDateFin(), b.getDateDebut())) {
				throw new JardinException(DATE_FIN_HISTO_INF_DATE_DEBUT);
			}
		}
		
		if(Utils.isNullOrEmpty(b.getEncombrement())) {
			throw new JardinException(ENCOMBREMENT_OBL);
		}
			
	}
}
