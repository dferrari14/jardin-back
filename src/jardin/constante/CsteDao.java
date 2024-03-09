package jardin.constante;

public class CsteDao {
	public final static String DATABASE_NAME = "jardin";

	// COLONNE COMMUNE
	public final static String COLUMN_ID_LEGUME = "idLegume";
	public final static String COLUMN_ID_PARCELLE = "idParcelle";
	public final static String COLUMN_ID_HISTO = "idHisto";
	public final static String COLUMN_DATE_DEBUT = "dateDebut";
	public final static String COLUMN_DATE_FIN = "dateFin";
	public final static String COLUMN_DESCRIPTION = "description";
	public final static String COLUMN_TYPE = "type";
	public final static String COLUMN_NOM = "nom";
	public final static String COLUMN_EXPOSITION = "exposition";

	// TABLE LEGUME
	public final static String TABLE_LEGUME = "legume";
	public final static String COLUMN_FAMILLE = "famille";
	public final static String COLUMN_NB_ANNEE_ROTATION = "nbAnneeRotation";
	
	// TABLE ARROSAGE
	public final static String TABLE_ARROSAGE = "arrosage";
	public final static String COLUMN_FREQUENCE = "nom";
	public final static String COLUMN_UNITE = "uniteFrequence";
	public final static String COLUMN_QUANTITEE = "quantitee";
	
	//TABLE ENRICHISSEMENT
	public final static String TABLE_ENRICHISSEMENT = "enrichissement";
	public final static String COLUMN_ENGRAIS = "engrais";
	public final static String COLUMN_AMENDEMENT = "amendement";
	public final static String COLUMN_DATE_ENRICHISSEMENT = "dateEnrichissement";
	
	//TABLE HISTO CULTURE
	public final static String TABLE_HISTO_CULTURE = "histo_culture";
	public final static String COLUMN_ENCOMBREMENT= "encombrement";
	public final static String COLUMN_OBSERVATION= "observation";
	public final static String COLUMN_RENDEMENT= "rendement";
	
	//TABLE HISTO NPK PH
	public final static String TABLE_HISTO_NPK_PH = "histo_npk_ph";
	public final static String COLUMN_DATE_RELEVE = "dateReleve";
	public final static String COLUMN_AZOTE = "azote";
	public final static String COLUMN_PHOSPHORE = "phosphore";
	public final static String COLUMN_POTASSIUM = "potassium";
	public final static String COLUMN_PH = "ph";

	//TABLE VOISINAGE CULTURE
	public final static String TABLE_VOISINAGE_CULTURE = "voisinage_legume";
	public final static String COLUMN_ID_LEGUME_VOISINAGE = "idLegumeVoisinage";
	public final static String COLUMN_TYPE_VOISINAGE_VALUE_OK = "OK";
	public final static String COLUMN_TYPE_VOISINAGE_VALUE_KO = "KO";
	
	//TABLE VOISINAGE PARCELLE
	public final static String TABLE_VOISINAGE_PARCELLE = "voisinage_parcelle";
	public final static String COLUMN_ID_PARCELLE_VOISINAGE = "idParcelleVoisinage";
	
	//TABLE SEMIS
	public final static String TABLE_SEMIS = "semis";
	public final static String COLUMN_ESPACEMENT_LIGNE = "espacementLigneCm";
	public final static String COLUMN_ESPACEMENT_PLANT = "espacementPlantCm";
	public final static String COLUMN_PROFONDEUR = "profondeur";
	public final static String COLUMN_MOIS_MIN_SEMIS = "moisMinSemis";
	public final static String COLUMN_MOIS_MAX_SEMIS = "moisMaxSemis";
	public final static String COLUMN_MOIS_MIN_RECOLTE = "moisMinRecolte";
	public final static String COLUMN_MOIS_MAX_RECOLTE = "moisMaxRecolte";
	public final static String COLUMN_TEMPERATURE_MIN_GERMINAISON = "temperatureMinGerminaison";
	public final static String COLUMN_DUREE_GERMINAISON = "dureeGerminaison";
	public final static String COLUMN_REMARQUE = "remarque";
	
	//TABLE PARCELLE
	public final static String TABLE_PARCELLE = "parcelle";
	public final static String COLUMN_LARGEUR = "largeur";
	public final static String COLUMN_LONGUEUR = "longueur";
	
	//TABLE PLANNING
	public final static String TABLE_PLANNING = "planning";
	public final static String COLUMN_DATE_MAX = "dateMax";
	public final static String COLUMN_TACHE = "tache";

	//SQL
	public final static String ORDER_BY_DESC = "desc";
	public final static String ORDER_BY_ASC = "asc";
 
}
