package jardin.model.metier;

import jardin.model.dao.BmPlanningDao;

public class BmMetierPlanning extends BmPlanningDao{
		 
		private int idLegume;
		private String nomLegume;
		private String nomParcelle;	 
		private String etat; //FAIT/A FAIRE/URGENT (date jour > date max)
		
		public BmMetierPlanning() {
			super();
		}

		public int getIdLegume() {
			return idLegume;
		}

		public void setIdLegume(int idLegume) {
			this.idLegume = idLegume;
		}
 
		public String getEtat() {
			return etat;
		}

		public void setEtat(String etat) {
			this.etat = etat;
		}

		public String getNomLegume() {
			return nomLegume;
		}

		public void setNomLegume(String nomLegume) {
			this.nomLegume = nomLegume;
		}

		public String getNomParcelle() {
			return nomParcelle;
		}

		public void setNomParcelle(String nomParcelle) {
			this.nomParcelle = nomParcelle;
		}
		
		
		
}
