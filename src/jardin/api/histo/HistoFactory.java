package jardin.api.histo;

public class HistoFactory {
	
	public static HistoApi getInstance() {
		return new HistoApiImpl();
	}
}
