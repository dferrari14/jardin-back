package jardin.api.metier;

public class MetierFactory {
	
	public static MetierApi getInstance() {
		return new MetierApiImpl();
	}
}
