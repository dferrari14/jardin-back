package jardin.metier;

import jardin.technique.JardinException;
import jardin.technique.Utils;
import jardin.technique.UtilsDate;

public class ControleDate {
	//legume
	private static final String OBL = " obligatoire";
	private static final String KO = " invalide ";
	 
	public static void controleDateAAAAMMJJ_OBL(String d,String libelleDate) throws JardinException {
		if(Utils.isNullOrEmpty(d)) {
			throw new JardinException(libelleDate  + OBL);
		}
		
		if(!isDateAAAAMMJJ_OK(d)) {
			throw new JardinException(libelleDate  + KO  +d);
		}
	}
	
	public static void controleDateAAAAMMJJ_FAC(String d,String libelleDate) throws JardinException {
		if(!Utils.isNullOrEmpty(d)) {
			controleDateAAAAMMJJ_OBL(d, libelleDate);
		}
	}
	
	private static boolean isDateAAAAMMJJ_OK(String d) { 
		if(d.length() != 8) {
			return false;
		}
		
		int aaaa = Integer.valueOf(d.substring(0,4));
		int mm = Integer.valueOf(d.substring(5, 6));
		int jj = Integer.valueOf(d.substring(7,8));
		
		if(mm ==0 || mm > 12) {
			return false;
		}
		
		if(mm == 1 || mm == 3 || mm==5 || mm == 7 || mm == 8 || mm == 10 || mm == 12) {
			if(jj > 31) {
				return false;
			}
		}
		if(mm == 4 || mm == 6 || mm==9 || mm == 11) {
			if(jj > 30) {
				return false;
			}
		}
		if(mm == 2) {
			if(UtilsDate.isAnneeBissextile(aaaa)){
				if(jj > 29) {
					return false;
				}			
			}else{
				if(jj > 28) {
					return false;
				}			
			}
		}
		
		
		return true;
	}
	 
	
}
