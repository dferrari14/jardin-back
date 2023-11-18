package jardin.technique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import jardin.model.BmLegume;

public class Utils {
	public static Properties load(String filename) throws IOException, FileNotFoundException {
		Properties properties = new Properties();

		FileInputStream input = new FileInputStream(filename);
		try {
			properties.load(input);
			return properties;
		} finally {
			input.close();
		}
	}
	
	public static boolean isNullOrEmpty(String s) {
		if(s == null || s.equals("")) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isListeContainLegume(List<Integer> l,int idLegume) {
		if(l != null) {
			for(int b:l) {
				if(b == idLegume) {
					return true;
				}
			}		
		}		
		return false;
	}
	
	public static BmLegume getLegumeFromListe(List<BmLegume> l,int idLegume) throws JardinException{
		if(l != null) {
			for(BmLegume b:l) {
				if(b.getIdLegume() == idLegume) {
					return b;
				}
			}		
		}		
		throw new JardinException("pas de legume trouvé , getLegumeFromListe", "idLegume : " + idLegume + " , Liste : " + l);
	}
}
