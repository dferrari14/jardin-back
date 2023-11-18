package jardin.metier;

import jardin.model.BmLegume;
import jardin.model.BmParcelle;
import jardin.technique.JardinException;
import jardin.technique.Utils;

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
	
	public static void controleLegume(BmLegume b) throws JardinException {
		if( b == null) {
			throw new JardinException(NOM_LEGUME_OBL);
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
	
	public static void controleParcelle(BmParcelle b) throws JardinException {
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
}
