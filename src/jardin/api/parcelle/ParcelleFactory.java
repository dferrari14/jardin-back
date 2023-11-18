package jardin.api.parcelle;

public class ParcelleFactory {
	
	public static ParcelleApi getInstance() {
		return new ParcelleApiImpl();
	}
}
