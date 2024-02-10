package jardin.api.voisinage;

public class VoisinageFactory {
	
	public static VoisinageApi getInstance() {
		return new VoisinageApiImpl();
	}
}
