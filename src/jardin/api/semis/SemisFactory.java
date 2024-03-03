package jardin.api.semis;

public class SemisFactory {
	
	public static SemisApi getInstance() {
		return new SemisApiImpl();
	}
}
