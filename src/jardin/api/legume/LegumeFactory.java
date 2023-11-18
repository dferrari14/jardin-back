package jardin.api.legume;

public class LegumeFactory {
	
	public static LegumeApi getInstance() {
		return new LegumeApiImpl();
	}
}
