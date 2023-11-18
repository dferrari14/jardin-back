package jardin;

import jardin.technique.JardinException;
import jardin.technique.UtilsDate;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String d = "20240501";
		try {
			System.out.println(UtilsDate.getAAAAMMJJ(UtilsDate.soustractMois(UtilsDate.createDate(d), 4)));
		} catch (JardinException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
