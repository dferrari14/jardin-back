package jardin.model;

public class BmSemis {
	private int idLegume;
	private int espacementLigneCm;
	private int espacementPlantCm;
	private String periode; 
	private String remarque; 
	
	public BmSemis() {
		super();
	}

	public int getIdLegume() {
		return idLegume;
	}

	public void setIdLegume(int idLegume) {
		this.idLegume = idLegume;
	}

	public int getEspacementLigneCm() {
		return espacementLigneCm;
	}

	public void setEspacementLigneCm(int espacementLigneCm) {
		this.espacementLigneCm = espacementLigneCm;
	}

	public int getEspacementPlantCm() {
		return espacementPlantCm;
	}

	public void setEspacementPlantCm(int espacementPlantCm) {
		this.espacementPlantCm = espacementPlantCm;
	}

	public String getPeriode() {
		return periode;
	}

	public void setPeriode(String periode) {
		this.periode = periode;
	}

	public String getRemarque() {
		return remarque;
	}

	public void setRemarque(String remarque) {
		this.remarque = remarque;
	}

}
