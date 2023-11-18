package jardin.technique;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
* Class Util pour Date
*/
public class UtilsDate {
	public static final String FORMAT_AAAAMMJJ = "yyyyMMdd";

	private static final char DELIM_SLASH = '/';
	private static final char DELIM_MINUS = '-';
	private static final char DELIM_DOT = '.';
	private static final int POSITIF = 1;
	private static final int NEGATIF = -1;
	public static final String FORMAT_ANNEEDDANSDECENIEQUANTIEME = "yDDD";

	private static final int[] tabDayMaxOfMonth = { 31, 28, 31, 30, 31, 30, 31,
			31, 30, 31, 30, 31 };

	private static final String[] tabj = { "00", "01", "02", "03", "04", "05",
			"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
			"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
			"28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38",
			"39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49",
			"50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60" };

	private static final String[] tabLibelleMois = { "Janvier", "Février",
			"Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre",
			"Octobre", "Novembre", "Décembre" };

	private static final String[] tabLibelleJours = { "lundi", "mardi",
			"mercredi", "jeudi", "vendredi", "samedi", "dimanche" };
	public static final String DATE_MIN_AAAAMMJJ = "00010101";
	public static final String DATE_MAX_AAAAMMJJ = "99991231";

	private UtilsDate() {
		// RAS
	}
	
	/**
	 * Renvoi true si date2 est après ou egal date1 au format AAAAMM
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean afterAAAAMMD2D1(String date1, String date2) {
		boolean result = false;
		if (!Utils.isNullOrEmpty(date1) && !Utils.isNullOrEmpty(date2) && date1.length() == 6 && date2.length() == 6) {
			int mm1 = Integer.parseInt(date1.substring(4));
			int mm2 = Integer.parseInt(date2.substring(4));

			int aaaa1 = Integer.parseInt(date1.substring(0, 4));
			int aaaa2 = Integer.parseInt(date2.substring(0, 4));

			if (aaaa1 < aaaa2) {
				result = true;
			}

			if (aaaa1 == aaaa2 && mm1 < mm2) {
				result = true;
			}
		}
		return result;
	}
	
	public static String getAAAAtiretMMtiretJJ(String dateAAAAMMJJ) {
		return dateAAAAMMJJ.substring(0, 4)+"-"+dateAAAAMMJJ.substring(4, 6)+"-"+dateAAAAMMJJ.substring(6, 8);
	}
	
	// retourne le 31/12 de l'année saisie
	public static String getDate31Decembre(String dateAAAAMMJJ){
		return getAAAAXXYY(dateAAAAMMJJ,"1231");
	}
	
	public static String getDate01Janvier(String dateAAAAMMJJ){
		return getAAAAXXYY(dateAAAAMMJJ,"0101");
	}
	
	public static String getAAAAXXYY(String dateAAAAMMJJ,String filler){
		return getAnnee(dateAAAAMMJJ)+filler;
	}
	
	public static String getPremierJourDuMois(){
		return getAAAAMM(createDate())+"01";
	}
	
	public static String getPremierJourDuMois(String date){
		return getAAAAMM(date)+"01";
	}
	
	public static Date getPremierJourDuMois(Date date){
		return createDate(getAAAAMM(date)+"01");
	}

	/**
	 * Renvoi true si date2 est après ou egal date1 au format AAAAMM
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean afterOrEqualAAAAMMD2D1(String date1, String date2) {
		boolean result = false;
		if (!Utils.isNullOrEmpty(date1) && !Utils.isNullOrEmpty(date2) && date1.length() == 6 && date2.length() == 6) {
			int mm1 = Integer.parseInt(date1.substring(4));
			int mm2 = Integer.parseInt(date2.substring(4));

			int aaaa1 = Integer.parseInt(date1.substring(0, 4));
			int aaaa2 = Integer.parseInt(date2.substring(0, 4));

			if (aaaa1 < aaaa2) {
				result = true;
			}

			if (aaaa1 == aaaa2 && mm1 <= mm2) {
				result = true;
			}
		}
		return result;
	}
	
	public static String getHHMMSS(Date d) {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
		return d != null ? formatter.format(d) : "";
	}

	/**
	 * Renvoi la date passée en parametre sous la forme d'une string AAAAMMJJ
	 */
	public static String getAAAAMMJJ(Date d) {
		// cette methode est à prendre plutot que celle du fwk , en effet si la date est null
		// je renvoie "" plutot que null
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return d != null ? formatter.format(d) : "";
	}

	/**
	 * Renvoi la chaine AAAAMMJJ passée en parametre sous la forme d'un string JJMMAAAA
	 */
	public static String transformAAAAMMJJtoJJMMAAAA(String s) {
		String chaineJJMMAAA = "";
		if (!Utils.isNullOrEmpty(s) && s.length() == 8) {
			String jj = s.substring(6, 8);
			String mm = s.substring(4, 6);
			String aaaa = s.substring(0, 4);
			chaineJJMMAAA = jj + mm + aaaa;
		}
		return chaineJJMMAAA;
	}

	/**
	 * Renvoi la date passée en parametre sous la forme d'une string AAAAMM
	 */
	public static String getAAAAMM(Date d) {
		// cette methode est à prendre plutot que celle du fwk , en effet si la date est null
		// je renvoie "" plutot que null
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return d != null ? formatter.format(d) : "";
	}
	
	public static String getAAAASlashMM(String d) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/yyyy");
		return d != null ? formatter.format(createDate(d)) : "";
	}
	
	
	public static String getAAAAMM(String d) {
		return getAAAAMM(createDate(d));
	}

	/**
	 * Renvoi la date passée en parametre sous la forme d'une string AAAA
	 */
	public static String getAAAA(Date d) {
		// cette methode est à prendre plutot que celle du fwk , en effet si la date est null
		// je renvoie "" plutot que nullutilsdat
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return d != null ? formatter.format(d) : "";
	}

	/**
	 * Gets the date jours moins 24 mois.
	 * 
	 * @return the date jours moins 24 mois
	 * @throws JardinException
	 *             the infra exception
	 */
	public static String getDateMoinsMois(Date dateJour, int nbMois) throws JardinException {
		return getAAAAMMJJ(soustractMois(dateJour, nbMois));
	}

	public static String getDatePlusMois(Date dateJour, int nbMois) throws JardinException {
		return getAAAAMMJJ(ajoutMois(dateJour, nbMois));
	}
	
	public static String getDatePlusMois(String dateJour, int nbMois) throws JardinException {
		try {
			return getAAAAMMJJ(ajoutMois(createDate(dateJour), nbMois));
		} catch (JardinException e) {
			throw new JardinException(e.getMessage());
		}
	}

	/**
	 * Get Date et Heure de Traitement
	 */
	public static Date getDateHeureTraitement(String dateTraitement, String heureTraitement) {
		return createDate(Integer.parseInt(dateTraitement.substring(0, 4)), Integer.parseInt(dateTraitement.substring(4, 6)), Integer.parseInt(dateTraitement.substring(6, 8)),
				Integer.parseInt(heureTraitement.substring(0, 2)), Integer.parseInt(heureTraitement.substring(2, 4)), Integer.parseInt(heureTraitement.substring(4, 6)));
	}

	public static Date getDateMin() {
		return createDate(DATE_MIN_AAAAMMJJ);
	}

	public static Date getDateMax() {
		return createDate(DATE_MAX_AAAAMMJJ);
	}


	public static boolean dateEqual(String date1, String date2) {
		return (compareToAAAAMMJJ(createDate(date1), createDate(date2)) == 0);
	}

	public static boolean dateBeforeD1D2(String date1, String date2) {
		return (compareToAAAAMMJJ(createDate(date1), createDate(date2)) < 0);
	}

	public static boolean dateBeforeOrEqualsD1D2(String date1, String date2) {
		return (compareToAAAAMMJJ(createDate(date1), createDate(date2)) <= 0);
	}

	public static boolean dateAfterD1D2(String date1, String date2) {
		return (compareToAAAAMMJJ(createDate(date1), createDate(date2)) > 0);
	}

	public static boolean dateAfterAAAAMMD2D1(Date date1, String date2) {
		boolean result = false;
		SimpleDateFormat moisFormat = new SimpleDateFormat("MM");
		SimpleDateFormat anneeFormat = new SimpleDateFormat("yyyy");
		int moisDate1 = Integer.parseInt(moisFormat.format(date1));
		int anneeDate1 = Integer.parseInt(anneeFormat.format(date1));
		int moisDate2 = Integer.parseInt(date2.substring(4, 6));
		int anneeDate2 = Integer.parseInt(date2.substring(0, 4));

		if (anneeDate1 < anneeDate2) {
			result = true;
		}

		if (anneeDate1 == anneeDate2 && moisDate1 <= moisDate2) {
			result = true;
		}

		return result;
	}

	// retourne true si date2 est < ou = la date 1
	public static boolean dateBeforeOrEqualsAAAAMMD2D1(Date date1, String date2) {
		boolean result = false;

		String date3 = UtilsDate.getAAAAMM(date1);
		int moisDate1 = Integer.parseInt(date3.substring(4, 6));
		int anneeDate1 = Integer.parseInt(date3.substring(0, 4));

		int moisDate2 = Integer.parseInt(date2.substring(4, 6));
		int anneeDate2 = Integer.parseInt(date2.substring(0, 4));

		if (anneeDate1 > anneeDate2) {
			result = true;
		}

		if (anneeDate1 == anneeDate2 && moisDate1 >= moisDate2) {
			result = true;
		}

		return result;
	}

	// retourne true si date2 est < ou = la date 1
	public static boolean dateBeforeOrEqualsAAAAMMD2D1(String date1, String date2) {
		boolean result = false;

		int moisDate1 = Integer.parseInt(date1.substring(4, 6));
		int anneeDate1 = Integer.parseInt(date1.substring(0, 4));

		int moisDate2 = Integer.parseInt(date2.substring(4, 6));
		int anneeDate2 = Integer.parseInt(date2.substring(0, 4));

		if (anneeDate1 > anneeDate2) {
			result = true;
		}

		if (anneeDate1 == anneeDate2 && moisDate1 >= moisDate2) {
			result = true;
		}

		return result;
	}
	
	public static boolean dateAfterOrEqualsAAAAMMD2D1(String date1, String date2) {
		boolean result = false;

		int moisDate1 = Integer.parseInt(date1.substring(4, 6));
		int anneeDate1 = Integer.parseInt(date1.substring(0, 4));

		int moisDate2 = Integer.parseInt(date2.substring(4, 6));
		int anneeDate2 = Integer.parseInt(date2.substring(0, 4));

		if (anneeDate2 > anneeDate1) {
			result = true;
		}

		if (anneeDate2 == anneeDate1 && moisDate2 >= moisDate1) {
			result = true;
		}

		return result;
	}
	

	public static boolean dateAfterD1D2(String date1, Date date2) {
		return (compareToAAAAMMJJ(createDate(date1), date2) > 0);
	}

	//date1 >= date2
	//-1 date1 < date2
	//0 date1=date2
	//1 date1 > date2
	public static boolean dateAfterOrEqualsD1D2(String date1, String date2) {
		return (compareToAAAAMMJJ(createDate(date1), createDate(date2)) >= 0);
	}

	//date 1 < date 2
	public static boolean dateBeforeD1D2(Date date1, Date date2) {
		return (compareToAAAAMMJJ(date1, date2) < 0);
	}

	public static boolean dateBeforeOrEqualsD1D2(Date date1, Date date2) {
		return (compareToAAAAMMJJ(date1, date2) <= 0);
	}

	public static boolean dateAfterD1D2(Date date1, Date date2) {
		return (compareToAAAAMMJJ(date1, date2) > 0);
	}

	public static boolean dateAfterOrEqualsD1D2(Date date1, Date date2) {
		return (compareToAAAAMMJJ(date1, date2) >= 0);
	}

	public static int plusUnMois(String date) {
		int plusUnMois;
		int moisCourant = Integer.parseInt(date.substring(4, 6));
		if (moisCourant == 12) {
			plusUnMois = 01;
		} else {
			plusUnMois = moisCourant + 1;
		}
		return plusUnMois;
	}
	
	public static int getMois(String date) {
		return Integer.parseInt(date.substring(4, 6));
	}
	
	public static int getAnnee(String date) {
		if(Utils.isNullOrEmpty(date) || date.length() < 4) {
			return 0;
		}
		return Integer.parseInt(date.substring(0, 4));
	}
	
	public static Date getMMAAAAPlusMois(String dateDebut , int nbMois){
		//date en format aaaammjj
		int moisDebutRef = UtilsDate.getMois(dateDebut);
		
		int moisDebut = UtilsDate.getMois(dateDebut) + nbMois;
		moisDebut = (moisDebut > 12 ? moisDebut-12:moisDebut);
		
		int aaaa = UtilsDate.getAnnee(dateDebut);
		//on a change d'année
		if(moisDebut < moisDebutRef){
			aaaa++;
		}
		
		return createDate(aaaa, moisDebut, 1);
	}

	// LES 2 Methodes ci-dessous sont recupérés du framework cnaf ,je les ai reprise pour virer le throws exceptions qui ne sert à rien
	// et qui oblige à le traiter

	private static int compareToAAAAMMJJ(Date date1, Date date2) {

		if (date1 == null && date2 == null) {
			return 0;
		}

		if (date1 == null) {
			return -1;
		}

		if (date2 == null) {
			return 1;
		}

		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		return compareToAAAAMMJJ(cal1, cal2);
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la 2<sup>ème</sup> date, pour les critères année/mois/jour. <br>
	 * Retourne un entier : -1 : La 1</up>ère</sup> date est strictement inférieure à la 2<sup>ème</sup>. 0 : La 1</up>ère</sup> date est égale à la 2<sup>ème</sup>. 1 : La 1</up>ère</sup> date est
	 * strictement supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (cal,null) retourne 1<br>
	 * (null,null) retourne 0<br>
	 * (null,cal) retourne -1<br>
	 * 
	 * @param cal1
	 *            - java.util.Calendar
	 * @param cal2
	 *            - java.util.Calendar
	 * @return entier
	 * @throws JardinException
	 *             - CnafDate_01
	 */
	private static int compareToAAAAMMJJ(Calendar cal1, Calendar cal2) {

		if (getIntAnnee(cal1) != getIntAnnee(cal2)) {
			return (getIntAnnee(cal1) < getIntAnnee(cal2) ? -1 : 1);
		} else if (getIntMois(cal1) != getIntMois(cal2)) {
			return (getIntMois(cal1) < getIntMois(cal2) ? -1 : 1);
		} else if (getIntJour(cal1) != getIntJour(cal2)) {
			return (getIntJour(cal1) < getIntJour(cal2) ? -1 : 1);
		} else {
			return 0;
		}
	}

	/**
	 * Retourne l'année sous forme d'un int Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	private static int getIntAnnee(Calendar cal) {
		return cal.get(Calendar.YEAR);
	}

	/**
	 * Retourne le mois sous forme d'un int. Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
//	private static int getIntMois(Calendar cal) {
//		return cal.get(Calendar.MONTH) + 1;
//	}

	/**
	 * Retourne le jour sous forme d'un int Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
//	private static int getIntJour(Calendar cal) {
//		return cal.get(Calendar.DATE);
//	}
	
	/**
	 * Constructeur Date, retourne la date/heure courante
	 * 
	 * @return java.util.Date
	 */
	public static Date createDate() {
		return GregorianCalendar.getInstance().getTime();
	}

	/**
	 * Constructeur Calendar, retourne la date/heure courante
	 * 
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar() {
		return GregorianCalendar.getInstance();
	}

	/**
	 * Constructeur Date, retourne la date à partir d'un calendrier Retourne
	 * null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return java.util.Date
	 */
	public static Date createDate(Calendar cal) {
		if (cal == null)
			return null;
		return cal.getTime();
	}

	/**
	 * Constructeur Calendar, retourne un calendar à partir d'un java.util.Date
	 * Retourne null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(Date date) {
		if (date == null)
			return null;
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);

		return cal;
	}

	/**
	 * Constructeur Date pour un format année/mois/jour. Retourne null si les
	 * paramètres sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @return java.util.Date
	 */
	public static Date createDate(int year, int month, int date) {
		if (year == 0 && month == 0 && date == 0)
			return null;

		Calendar cal = createGregCalendar(year, month, date);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar pour un format année/mois/jour. Retourne null si
	 * les paramètres sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(int year, int month, int date) {
		if (year == 0 && month == 0 && date == 0)
			return null;

		return new GregorianCalendar(year, month - 1, date);
	}

	/**
	 * Constructeur Date pour un format année/mois/jour/heure/minute. Retourne
	 * null si les paramètres sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @param hour
	 *            - entier
	 * @param minute
	 *            - entier
	 * @return java.util.Date
	 */
	public static Date createDate(int year, int month, int date, int hour,
			int minute) {

		if (year == 0 && month == 0 && date == 0 && hour == 0 && minute == 0)
			return null;

		Calendar cal = createGregCalendar(year, month, date, hour, minute);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar pour un format année/mois/jour/heure/minute.
	 * Retourne null si les paramètres sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @param hour
	 *            - entier
	 * @param minute
	 *            - entier
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(int year, int month, int date,
			int hour, int minute) {
		if (year == 0 && month == 0 && date == 0 && hour == 0 && minute == 0)
			return null;

		return new GregorianCalendar(year, month - 1, date, hour, minute);
	}

	/**
	 * Constructeur Date pour un format année/mois/jour/heure/minute/seconde.
	 * Retourne null si les paramètres sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @param hour
	 *            - entier
	 * @param minute
	 *            - entier
	 * @param second
	 *            - second
	 * @return java.util.Date
	 */
	public static Date createDate(int year, int month, int date, int hour,
			int minute, int second) {

		if (year == 0 && month == 0 && date == 0 && hour == 0 && minute == 0
				&& second == 0)
			return null;

		Calendar cal = createGregCalendar(year, month, date, hour, minute,
				second);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar pour un format
	 * année/mois/jour/heure/minute/seconde. Retourne null si les paramètres
	 * sont invalides
	 * 
	 * @param year
	 *            - entier
	 * @param month
	 *            - entier
	 * @param date
	 *            - entier
	 * @param hour
	 *            - entier
	 * @param minute
	 *            - entier
	 * @param second
	 *            - entier
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(int year, int month, int date,
			int hour, int minute, int second) {
		if (year == 0 && month == 0 && date == 0 && hour == 0 && minute == 0
				&& second == 0)
			return null;

		return new GregorianCalendar(year, month - 1, date, hour, minute,
				second);
	}

	/**
	 * Constructeur Date pour un format chaine de caractères AAAAMMJJ.
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @return java.util.Date
	 */
	public static Date createDate(String dateAAAAMMJJ) {
		return createDate(Integer.parseInt(dateAAAAMMJJ.substring(0, 4)),
				Integer.parseInt(dateAAAAMMJJ.substring(4, 6)), Integer
						.parseInt(dateAAAAMMJJ.substring(6, 8)));
	}

	/**
	 * Constructeur Calendar pour un format chaine de caractères AAAAMMJJ.
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(String dateAAAAMMJJ) {
		return createGregCalendar(Integer
				.parseInt(dateAAAAMMJJ.substring(0, 4)), Integer
				.parseInt(dateAAAAMMJJ.substring(4, 6)), Integer
				.parseInt(dateAAAAMMJJ.substring(6, 8)));
	}

	/**
	 * Constructeur Date pour un format long AAAAMMJJ.
	 * 
	 * @param dateLongAAAAMMJJ
	 *            - long
	 * @return java.util.Date
	 */
	public static Date createDate(long dateLongAAAAMMJJ) {
		if (dateLongAAAAMMJJ == 0)
			return null;

		return createDate(Integer.parseInt(Long.toString(dateLongAAAAMMJJ)
				.substring(0, 4)), Integer.parseInt(Long.toString(
				dateLongAAAAMMJJ).substring(4, 6)), Integer.parseInt(Long
				.toString(dateLongAAAAMMJJ).substring(6, 8)));
	}

	/**
	 * Constructeur Calendar pour un format long AAAAMMJJ.
	 * 
	 * @param dateLongAAAAMMJJ
	 *            - long
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(long dateLongAAAAMMJJ) {
		if (dateLongAAAAMMJJ == 0)
			return null;

		return createGregCalendar(Integer.parseInt(Long.toString(
				dateLongAAAAMMJJ).substring(0, 4)), Integer.parseInt(Long
				.toString(dateLongAAAAMMJJ).substring(4, 6)), Integer
				.parseInt(Long.toString(dateLongAAAAMMJJ).substring(6, 8)));
	}

	/**
	 * Constructeur Date avec Locale.
	 * 
	 * @param aLocale
	 *            - Locale
	 * @return java.util.Date
	 */
	public static Date createDate(java.util.Locale aLocale) {
		Calendar cal = createGregCalendar(aLocale);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar avec Locale.
	 * 
	 * @param aLocale
	 *            - Locale
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(java.util.Locale aLocale) {
		return new GregorianCalendar(aLocale);
	}

	/**
	 * Constructeur Date avec TimeZone.
	 * 
	 * @param zone
	 *            - TimeZone
	 * @return java.util.Date
	 */
	public static Date createDate(java.util.TimeZone zone) {
		Calendar cal = createGregCalendar(zone);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar avec TimeZone.
	 * 
	 * @param zone
	 *            - TimeZone
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(java.util.TimeZone zone) {
		return new GregorianCalendar(zone);
	}

	/**
	 * Constructeur Date avec TimeZone et Locale. Retourne null si les
	 * paramètres sont invalides
	 * 
	 * @param zone
	 *            - TimeZone
	 * @param aLocale
	 *            - Locale
	 * @return java.util.Date
	 */
	public static Date createDate(java.util.TimeZone zone,
			java.util.Locale aLocale) {
		Calendar cal = createGregCalendar(zone, aLocale);
		if (cal != null)
			return cal.getTime();
		return null;
	}

	/**
	 * Constructeur Calendar avec TimeZone et Locale.
	 * 
	 * @param zone
	 *            - TimeZone
	 * @param aLocale
	 *            - Locale
	 * @return java.util.Calendar
	 */
	public static Calendar createGregCalendar(java.util.TimeZone zone,
			java.util.Locale aLocale) {
		return new GregorianCalendar(zone, aLocale);
	}

	/**
	 * Crée et retourne une date minimale au sens CNAF, à savoir année 1, mois
	 * 1, jour 1.
	 * 
	 * @return java.tuil.Date
	 */
	public static Date createDateMin() {
		return createDate("00010101");
	}

	/**
	 * Crée et retourne une date minimale au sens CNAF, à savoir année 1, mois
	 * 1, jour 1.
	 * 
	 * @return java.tuil.Calendar
	 */
	public static Calendar createGregCalendarMin() {
		return createGregCalendar("00010101");
	}

	/**
	 * Crée et retourne une date maximale au sens CNAF, à savoir année 9999,
	 * mois 12, jour 31.
	 * 
	 * @return java.tuil.Date
	 */
	public static Date createDateMax() {
		return createDate("99991231");
	}

	/**
	 * Crée et retourne une date maximale au sens CNAF, à savoir année 9999,
	 * mois 12, jour 31.
	 * 
	 * @return java.tuil.Calendar
	 */
	public static Calendar createGregCalendarMax() {
		return createGregCalendar("99991231");
	}

	/**
	 * Ajoute un nombre d'années, un nombre de mois et un nombre de jours à une
	 * date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param annee
	 *            - entier
	 * @param mois
	 *            - entier
	 * @param jour
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date ajoutDate(Date date, int annee, int mois, int jour)
			throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, annee, mois, jour, POSITIF).getTime();
	}

	/**
	 * Ajoute un nombre d'années, un nombre de mois et un nombre de jours à une
	 * date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param annee
	 *            - entier
	 * @param mois
	 *            - entier
	 * @param jour
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar ajoutDate(Calendar cal, int annee, int mois, int jour)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, annee, mois, jour, POSITIF);
	}

	/**
	 * Ajoute un nombre de jours à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param jour
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date ajoutJours(Date date, int jour) throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, 0, 0, jour, POSITIF).getTime();
	}

	/**
	 * Ajoute un nombre de jours à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param jour
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar ajoutJours(Calendar cal, int jour)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, 0, 0, jour, POSITIF);
	}

	/**
	 * Ajoute un nombre de mois à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param mois
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date ajoutMois(Date date, int mois) throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, 0, mois, 0, POSITIF).getTime();
	}

	/**
	 * Ajoute un nombre de mois à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param mois
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar ajoutMois(Calendar cal, int mois)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, 0, mois, 0, POSITIF);
	}

	/**
	 * Ajoute un nombre d'années à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param année
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date ajoutAnnees(Date date, int annee) throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, annee, 0, 0, POSITIF).getTime();
	}

	/**
	 * Ajoute un nombre d'années à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param annee
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar ajoutAnnees(Calendar cal, int annee)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, annee, 0, 0, POSITIF);
	}

	/**
	 * Soustrait un nombre d'années, un nombre de mois et un nombre de jours à
	 * une date. Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param annee
	 *            - entier
	 * @param mois
	 *            - entier
	 * @param jour
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             - CnafDate_06 : nombre d'année à extraire supérieur à l'année
	 *             en cours
	 */
	public static Date soustractDate(Date date, int annee, int mois, int jour)
			throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, annee, mois, jour, NEGATIF).getTime();
	}

	/**
	 * Soustrait un nombre d'années, un nombre de mois et un nombre de jours à
	 * une date. Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param annee
	 *            - entier
	 * @param mois
	 *            - entier
	 * @param jour
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_06 : nombre d'année à extraire supérieur à l'année
	 *             en cours
	 */
	public static Calendar soustractDate(Calendar cal, int annee, int mois,
			int jour) throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, annee, mois, jour, NEGATIF);
	}

	/**
	 * Soustrait un nombre de jours à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param jour
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date soustractJours(Date date, int jour)
			throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, 0, 0, jour, NEGATIF).getTime();
	}

	/**
	 * Soustrait un nombre de jours à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param jour
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar soustractJours(Calendar cal, int jour)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, 0, 0, jour, NEGATIF);
	}

	/**
	 * Soustrait un nombre de mois à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param mois
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date soustractMois(Date date, int mois) throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, 0, mois, 0, NEGATIF).getTime();
	}

	/**
	 * Soustrait un nombre de mois à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param mois
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar soustractMois(Calendar cal, int mois)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, 0, mois, 0, NEGATIF);
	}

	/**
	 * Soustrait un nombre d'années à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param année
	 *            - entier
	 * @return java.util.Date
	 * @throws JardinException
	 *             -
	 */
	public static Date soustractAnnees(Date date, int annee)
			throws JardinException {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return calcDate(cal, annee, 0, 0, NEGATIF).getTime();
	}

	/**
	 * Soustrait un nombre d'années à une date. <BR>
	 * Si la date est null, retourne null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param annee
	 *            - entier
	 * @return java.util.Calendar
	 * @throws JardinException
	 *             -
	 */
	public static Calendar soustractAnnees(Calendar cal, int annee)
			throws JardinException {
		if (cal == null)
			return null;

		return calcDate(cal, annee, 0, 0, NEGATIF);
	}

	/**
	 * Méthode privée qui ajoute ou soustrait un nombre d'années, un nombre de
	 * mois et un nombre de jours à une date.
	 * 
	 * @param cal
	 * @param annee
	 * @param mois
	 * @param jour
	 * @param sign
	 * @return
	 * @throws JardinException
	 *             - CnafDate_06
	 */
	private static Calendar calcDate(Calendar cal, int annee, int mois,
			int jour, int sign) throws JardinException {
		Calendar calRetour = (Calendar) cal.clone();

		if (sign > 0) {
			calRetour.add(Calendar.YEAR, Math.abs(annee) * sign);
			calRetour.add(Calendar.MONTH, Math.abs(mois) * sign);
			calRetour.add(Calendar.DATE, Math.abs(jour) * sign);
		} else {
			int year = calRetour.get(Calendar.YEAR);
			if (annee > year) {
				throw genererJardinException("CnafDate_06",
						"Nombre d'années à soustraire supérieures à la date passée en paramètre.");
			}

			calRetour.add(Calendar.YEAR, Math.abs(annee) * sign);
			calRetour.add(Calendar.MONTH, Math.abs(mois) * sign);
			calRetour.add(Calendar.DATE, Math.abs(jour) * sign);
		}

		return calRetour;
	}

	/**
	 * Calcule la différence entre date1 et date2 et retourne le nombre de jours
	 * 
	 * (date,null) retourne une exception<br>
	 * (null,null) retourne une structure avec pour chaque élément la valeur 0<br>
	 * (null,date) retourne une exception<br>
	 * 
	 * @param date1
	 *            - java.util.Date
	 * @param date2
	 *            - java.util.Date
	 * @return DifferenceDate
	 * @throws JardinException
	 *             - CnafDate_03 Une date est au moins null
	 */
//	public static DateDifference comparesTwoDates(Date date1, Date date2)
//			throws JardinException {
//
//		if (date1 == null && date2 == null)
//			return new DateDifference(0,0,0);
//			
//		if (date1 == null || date2 == null)
//			throw genererJardinException("CnafDate_03",
//					"Une date au moins est null<" + date1 + "><" + date2 + ">");
//
//		Calendar cal1 = new GregorianCalendar();
//		cal1.setTime(date1);
//		Calendar cal2 = new GregorianCalendar();
//		cal2.setTime(date2);
//
//		return comparesTwoGregCalendar(cal1, cal2);
//	}

	/**
	 * Calcule la différence entre date1 et date2 et retourne le nombre de jours
	 * 
	 * (cal,null) retourne une exception<br>
	 * (null,null) retourne une structure avec pour chaque élément la valeur 0<br>
	 * (null,cal) retourne une exception<br>
	 * 
	 * @param cal1
	 *            - java.util.Calendar
	 * @param cal2
	 *            - java.util.Calendar
	 * @return DifferenceDate
	 * @throws JardinException
	 *             - CnafDate_03 Une date est au moins null
	 */
//	public static DateDifference comparesTwoGregCalendar(Calendar cal1,
//			Calendar cal2) throws JardinException {
//
//		if (cal1 == null && cal2 == null)
//			return new DateDifference(0,0,0);
//		
//		if (cal1 == null || cal2 == null)
//			throw genererJardinException("CnafDate_03",
//					"Une date au moins est null<" + cal1 + "><" + cal2 + ">");
//
//		int annee1, mois1, jour1, annee2, mois2, jour2, diffAnnee, diffMois, diffJour;
//		boolean top1 = false, top2 = false;
//		String signe = "positif";
//
//		long comp = cal1.getTimeInMillis() - cal2.getTimeInMillis();
//
//		if (comp < 0) {
//			signe = "negatif";
//			annee1 = cal2.get(Calendar.YEAR);
//			mois1 = cal2.get(Calendar.MONTH) + 1;
//			jour1 = cal2.get(Calendar.DATE);
//			annee2 = cal1.get(Calendar.YEAR);
//			mois2 = cal1.get(Calendar.MONTH) + 1;
//			jour2 = cal1.get(Calendar.DATE);
//		} else if (comp > 0) {
//			annee1 = cal1.get(Calendar.YEAR);
//			mois1 = cal1.get(Calendar.MONTH) + 1;
//			jour1 = cal1.get(Calendar.DATE);
//			annee2 = cal2.get(Calendar.YEAR);
//			mois2 = cal2.get(Calendar.MONTH) + 1;
//			jour2 = cal2.get(Calendar.DATE);
//		} else {
//			return new DateDifference(0, 0, 0);
//		}
//		diffJour = jour1 - jour2;
//		if (diffJour < 0) {
//			if (mois1 == 1)
//				diffJour = getDayMaxOfMonth(12, annee1) - jour2 + jour1;
//			else {
//				int dayMaxOfMonthMoins1 = getDayMaxOfMonth(mois1 - 1, annee1);
//				if (dayMaxOfMonthMoins1 <= jour2) {
//					diffJour = jour1;
//				} else {
//					diffJour = dayMaxOfMonthMoins1 - jour2 + jour1;
//				}
//			}
//			top1 = true;
//		}
//		diffMois = mois1 - mois2;
//		if (diffMois < 0) {
//			diffMois = 12 - mois2 + mois1;
//			top2 = true;
//		} else if (diffMois == 0) {
//			if (top1) {
//				diffMois = 12;
//				top2 = true;
//			}
//		}
//		if (top1)
//			diffMois--;
//		diffAnnee = annee1 - annee2;
//		if (top2)
//			diffAnnee--;
//
//		if (signe.equals("positif"))
//			return new DateDifference(diffAnnee, diffMois, diffJour);
//		else
//			return new DateDifference(-diffAnnee, -diffMois, -diffJour);
//	}

	/**
	 * Convertit un String de type JJMMAAAA en AAAAMMJJ.
	 * 
	 * @param dateJJMMAAAA
	 *            - String
	 * @return String
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static String convertJJMMAAAAToAAAAMMJJ(String dateJJMMAAAA)
			throws JardinException {

		if (dateJJMMAAAA == null || dateJJMMAAAA.length() != 8)
			throw genererJardinException("CnafDate_01", "Date incorrecte : "
					+ dateJJMMAAAA);

		String s = dateJJMMAAAA.substring(4, 8) + dateJJMMAAAA.substring(2, 4)
				+ dateJJMMAAAA.substring(0, 2);

		if (!isDateValide(s))
			throw genererJardinException("CnafDate_02", "Date invalide : "
					+ dateJJMMAAAA);

		return s;
	}

	/**
	 * Complète la zone avec des zeros pour atteindre la longueur requise
	 * 
	 * @param champs
	 * @param longueur
	 * @return
	 */
	private static String fillWithZero(int champs, int longueur) {
		String retour = String.valueOf(champs);
		while (retour.length() < longueur)
			retour = "0" + retour;
		return retour;
	}

	/**
	 * Retourne l'année sous forme d'un String.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return annee java.lang.String
	 */
	public static String getAnnee(Calendar cal) {
		if (cal == null)
			return null;
		return fillWithZero(cal.get(Calendar.YEAR), 4);
	}

	/**
	 * Retourne l'année sous forme d'un String. Attention à privilégier la
	 * méthode avec le paramètre Calendar si plusieurs méthodes get sont
	 * appelées successivement sur ce même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return annee java.lang.String
	 */
	public static String getAnnee(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return fillWithZero(cal.get(Calendar.YEAR), 4);
	}

	/**
	 * Retourne la date moins 1 an sous forme d'un String.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return date java.lang.String
	 */
	public static String getAnneeMoins1(Calendar cal) {
		if (cal == null)
			return null;
		return fillWithZero(cal.get(Calendar.YEAR) - 1, 4);
	}

	/**
	 * Retourne la date moins 1 an sous forme d'un String. Attention à
	 * privilégier la méthode avec le paramètre Calendar si plusieurs méthodes
	 * get sont appelées successivement sur ce même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return date java.lang.String
	 */
	public static String getAnneeMoins1(Date date) {
		if (date == null)
			return null;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return fillWithZero(cal.get(Calendar.YEAR) - 1, 4);
	}

	/**
	 * Retourne la date formattée suivant le format passé en argument. Les
	 * formats acceptés sont ceux de {@link SimpleDateFormat}.
	 * 
	 * @param date
	 *            - java.util.Date
	 * @param format
	 *            - String
	 * @return String
	 */
	public static String getFormattedDate(Date date, String format) {
		if (format == null || format.trim().length() == 0)
			return "";

		if (date == null)
			return null;

		SimpleDateFormat dfs = new SimpleDateFormat(format);
		String formattedDate = dfs.format(date);
		if (format.equals(FORMAT_ANNEEDDANSDECENIEQUANTIEME)) {
			formattedDate = formattedDate.substring(1);
		}
		return formattedDate;
	}

	/**
	 * Retourne la date formattée suivant le format passé en argument. Les
	 * formats acceptés sont ceux de {@link SimpleDateFormat}.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @param format
	 *            - String
	 * @return String
	 */
	public static String getFormattedDate(Calendar cal, String format) {
		if (format == null || format.trim().length() == 0)
			return "";

		if (cal == null)
			return null;

		return getFormattedDate(cal.getTime(), format);
	}

	/**
	 * Retourne la date formattée suivant le format passé en argument. Les
	 * formats acceptés sont ceux de {@link SimpleDateFormat}.
	 * 
	 * @param stringDate
	 *            - String
	 * @param format
	 *            - String
	 * 
	 * @return java.util.Date
	 * @throws JardinException
	 *             - CnafDate_02
	 */
	/*
	 * public static Date getFormattedDate(String stringDate, String format)
	 * throws JardinException { if (format == null || format.trim().length() ==
	 * 0) return null; if (stringDate == null || stringDate.length() == 0)
	 * return null;
	 * 
	 * try { SimpleDateFormat dfs = new SimpleDateFormat(format); Calendar cal =
	 * new GregorianCalendar(); cal.setTime(dfs.parse(stringDate)); return
	 * cal.getTime(); } catch (ParseException e) { throw
	 * genererJardinException("CnafDate_02", "Format erroné : " + format); } }
	 */

	/**
	 * Permet de modifier la date à partir d'une chaine de caractères passée en
	 * argument et selon un format lui aussi passé en argument. Les formats
	 * acceptés sont ceux de {@link SimpleDateFormat}. Quelques formats préfinis
	 * existent, ce sont des static final de la classe DateUtil préfixés par
	 * FORMAT_ .
	 * 
	 * exemple setFormattedDate("dd!MM!yyyy","04!05!2006") permet de retourner
	 * la date "04/05/2006"
	 * 
	 * @exception JardinException
	 */
	public static Date setFormattedDate(String pFormat, String pStringDate)
			throws JardinException {

		if (pFormat == null || pFormat.trim().equals(""))
			return null;

		String format = pFormat;
		String stringDate = pStringDate;
		if (format.equals(FORMAT_ANNEEDDANSDECENIEQUANTIEME)) {
			format = "y" + format;
			Calendar today = createGregCalendar();
			String decenie = getDateAAAAMMJJ(today).substring(2, 3);
			// On ne tient pas compte de la decenie, on considere le formattage
			// à 0
			if (stringDate.equals("0000"))
				decenie = "0";
			stringDate = decenie + stringDate;
		}

		try {
			if (stringDate.indexOf('0') >= 0
					&& !stringDate.matches(".*[1-9].*")) {
				// si la date comporte au moins un 0 mais pas d'autres chiffres
				// alors c'est une date à null
				return null;
			}

			SimpleDateFormat dfs = new SimpleDateFormat(format);
			return dfs.parse(stringDate);

		} catch (ParseException e) {
			String paramsConseil = " setDateAAAAMMJJ : impossible de modifier les attributs courants pour "
					+ pStringDate + " au format" + pFormat;
			throw genererJardinException("CnafDate_02", paramsConseil);
		}

	}

	/**
	 * Retourne la date sous forme d'un String de format AAAAMMJJ.
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateAAAAMMJJ(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDateAAAAMMJJ(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format AAAAMMJJ.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateAAAAMMJJ(Calendar cal) {
		if (cal == null)
			return null;
		return getAnnee(cal) + getMois(cal) + getJour(cal);
	}

	/**
	 * Modifie l'Année/Mois/Jours de l'instance calendar à partir d'une string.
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static void setDateAAAAMMJJ(String dateAAAAMMJJ, Calendar cal)
			throws JardinException {
		if (dateAAAAMMJJ == null || dateAAAAMMJJ.length() == 0 || cal == null) {
			throw genererJardinException("CnafDate_01",
					"La date en entrée est nulle.");
		}

		if (!isDateValide(dateAAAAMMJJ)) {
			throw genererJardinException("CnafDate_02", "La date en entrée "
					+ dateAAAAMMJJ + " est d'un format invalide (AAAAMMJJ)");
		}

		int intAnnee = Integer.parseInt(dateAAAAMMJJ.substring(0, 4));
		int intMois = Integer.parseInt(dateAAAAMMJJ.substring(4, 6)) - 1;
		int intJour = Integer.parseInt(dateAAAAMMJJ.substring(6, 8));

		cal.set(Calendar.DATE, intJour);
		cal.set(Calendar.MONTH, intMois);
		cal.set(Calendar.YEAR, intAnnee);
	}

	/**
	 * Modifie l'Année/Mois/Jours de la date à partir d'une string.
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static void setDateAAAAMMJJ(String dateAAAAMMJJ, Date date)
			throws JardinException {
		if (dateAAAAMMJJ == null || dateAAAAMMJJ.length() == 0 || date == null) {
			throw genererJardinException("CnafDate_01",
					"La date en entrée est nulle.");
		}

		if (!isDateValide(dateAAAAMMJJ)) {
			throw genererJardinException("CnafDate_02", "La date en entrée "
					+ dateAAAAMMJJ + " est d'un format invalide (AAAAMMJJ)");
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		setDateAAAAMMJJ(dateAAAAMMJJ, cal);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Modifie l'année de l'instance calendar à partir d'un entier.
	 * 
	 * @param a_annee
	 *            - entier
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_05
	 */
	public static void setAAAA(int a_annee, Calendar cal) throws JardinException {
		// 1. Verifier validite de l annee
		if (a_annee < 0) {
			throw genererJardinException("CnafDate_05", "L'année " + a_annee
					+ " est invalide (attendu supérieure à 0)");
		}

		cal.set(Calendar.YEAR, a_annee);
	}

	/**
	 * Modifie l'année de l'instance date à partir d'un entier. Attention à
	 * privilégier la méthode avec le paramètre Calendar si plusieurs méthodes
	 * set sont appelées successivement sur ce même objet date
	 * 
	 * @param a_annee
	 *            - entier
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_05
	 */

	public static void setAAAA(int a_annee, Date date) throws JardinException {
		// 1. Verifier validite de l annee
		if (a_annee < 0) {
			throw genererJardinException("CnafDate_05", "L'année " + a_annee
					+ " est invalide (attendu supérieure à 0)");
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.YEAR, a_annee);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Modifie le mois de l'instance calendar à partir d'un entier.
	 * 
	 * @param mois
	 *            - entier
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_05
	 */
	public static void setMM(int mois, Calendar cal) throws JardinException {
		// 1. Verifier validite du mois
		if (mois <= 0 || mois > 12) {
			throw genererJardinException("CnafDate_05", "Le mois " + mois
					+ " est invalide (attendu entre 1 et 12)");
		}

		cal.set(Calendar.MONTH, mois - 1);
	}

	/**
	 * Modifie le mois de l'instance date à partir d'un entier. Attention à
	 * privilégier la méthode avec le paramètre Calendar si plusieurs méthodes
	 * set sont appelées successivement sur ce même objet date
	 * 
	 * @param mois
	 *            - entier
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_05
	 */

	public static void setMM(int mois, Date date) throws JardinException {
		// 1. Verifier validite du mois
		if (mois <= 0 || mois > 12) {
			throw genererJardinException("CnafDate_05", "Le mois " + mois
					+ " est invalide (attendu entre 1 et 12)");
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.MONTH, mois - 1);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Modifie le jour de l'instance calendar à partir d'un entier.
	 * 
	 * @param a_jour
	 *            - entier
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_05
	 */
	public static void setJJ(int a_jour, Calendar cal) throws JardinException {
		if (!isJourValide(a_jour, getIntMois(cal), getIntAnnee(cal))) {
			throw genererJardinException("CnafDate_05", "Le jour " + a_jour
					+ " est invalide (attendu jour inclus dans le mois "
					+ getIntMois(cal) + " et l'année " + getIntAnnee(cal) + ")");
		}
		cal.set(Calendar.DATE, a_jour);
	}

	/**
	 * Modifie le jour de l'instance date à partir d'un entier. Attention à
	 * privilégier la méthode avec le paramètre Calendar si plusieurs méthodes
	 * set sont appelées successivement sur ce même objet date
	 * 
	 * @param a_jour
	 *            - entier
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_05
	 */
	public static void setJJ(int a_jour, Date date) throws JardinException {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		if (!isJourValide(a_jour, getIntMois(cal), getIntAnnee(cal))) {
			throw genererJardinException("CnafDate_05", "Le jour " + a_jour
					+ " est invalide (attendu jour inclus dans le mois "
					+ getIntMois(cal) + " et l'année " + getIntAnnee(cal) + ")");
		}
		cal.set(Calendar.DATE, a_jour);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Modifie les Heures/Minutes/Secondes de l'instance calendar à partir d'une
	 * string
	 * 
	 * @param dateHHMMSS
	 *            - String
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static void setDateHHMMSS(String dateHHMMSS, Calendar cal)
			throws JardinException {
		if (dateHHMMSS == null) {
			throw genererJardinException("CnafDate_01",
					"La date en entrée est nulle.");
		}

		if (!isHeuresValide(dateHHMMSS)) {
			throw genererJardinException("CnafDate_02", "La date en entrée "
					+ dateHHMMSS + " est d'un format invalide (HHMMSS)");
		}

		int intHeure = Integer.parseInt(dateHHMMSS.substring(0, 2));
		int intMinutes = Integer.parseInt(dateHHMMSS.substring(2, 4));
		int intSecondes = Integer.parseInt(dateHHMMSS.substring(4, 6));
		cal.set(Calendar.HOUR_OF_DAY, intHeure);
		cal.set(Calendar.MINUTE, intMinutes);
		cal.set(Calendar.SECOND, intSecondes);
	}

	/**
	 * Modifie les Heures/Minutes/Secondes de l'instance calendar à partir d'une
	 * string Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes set sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param dateHHMMSS
	 *            - String
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static void setDateHHMMSS(String dateHHMMSS, Date date)
			throws JardinException {
		if (dateHHMMSS == null) {
			throw genererJardinException("CnafDate_01",
					"La date en entrée est nulle.");
		}

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		setDateHHMMSS(dateHHMMSS, cal);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Modifie les Heures/Minutes de l'instance calendar à partir d'une string
	 * 
	 * @param dateHHMM
	 *            - String
	 * @param cal
	 *            - java.util.Calendar
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */
	public static void setDateHHMM(String dateHHMM, Calendar cal)
			throws JardinException {
		if (dateHHMM == null)
			setDateHHMMSS(null, cal);
		else
			setDateHHMMSS(dateHHMM + "00", cal);
	}

	/**
	 * Modifie les Heures/Minutes de l'instance calendar à partir d'une string
	 * Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes set sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param dateHHMM
	 *            - String
	 * @param date
	 *            - java.util.Date
	 * @throws JardinException
	 *             - CnafDate_01 et CnafDate_02
	 */

	public static void setDateHHMM(String dateHHMM, Date date)
			throws JardinException {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		if (dateHHMM == null)
			setDateHHMMSS(null, cal);
		else
			setDateHHMMSS(dateHHMM + "00", cal);
		date.setTime(cal.getTimeInMillis());
	}

	/**
	 * Vérifie la validité de l'heure
	 * 
	 * @param hhmmss
	 *            - String
	 * @return boolean
	 */
	public static boolean isHeuresValide(String hhmmss) {
		// test de longueur
		if (hhmmss.length() != 6)
			return false;
		// est-ce une chaine numérique?
		try {
			new Long(hhmmss);
		} catch (NumberFormatException e) {
			return false;
		}
		if ((Integer.parseInt(hhmmss.substring(0, 2)) > 23)
				|| (Integer.parseInt(hhmmss.substring(2, 4)) > 59)
				|| (Integer.parseInt(hhmmss.substring(4, 6)) > 59))
			return false;
		// tout va bien
		return true;
	}

	/**
	 * Teste si une année est bissextile
	 * 
	 * @param annee
	 *            - entier
	 * @return boolean
	 */
	public static boolean isAnneeBissextile(int annee) {
		if (annee % 4 == 0) {
			if (annee % 100 == 0) {
				if (annee % 400 == 0)
					return true;
				return false;
			} else
				return true;
		}
		return false;
	}

	/**
	 * Retourne true si le String fourni en paramètre (AAAAMMJJ) correspond bien
	 * à une date. <li>la date doit être numérique <li>le mois doit être
	 * différent de 0 et inf à 13 <li>la jour doit être différent de 0 et inf au
	 * nbre maximum + 1 de jours dans le mois
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @return boolean
	 */
	public static boolean isDateValide(String dateAAAAMMJJ) {

		if (dateAAAAMMJJ == null || dateAAAAMMJJ.length() != 8)
			return false;

		// Est-ce une chaine numérique?
		try {
			new Long(dateAAAAMMJJ);
		} catch (NumberFormatException e) {
			return false;
		}

		int annee = Integer.parseInt(dateAAAAMMJJ.substring(0, 4));
		int mois = Integer.parseInt(dateAAAAMMJJ.substring(4, 6));
		int jour = Integer.parseInt(dateAAAAMMJJ.substring(6, 8));

		if (mois <= 0 || mois > 12)
			return false;
		if (!isJourValide(jour, mois, annee))
			return false;

		return true;
	}

	/**
	 * Teste la validité du jour passé en paramètre en tenant compte du mois et
	 * de l'année.
	 * 
	 * @param a_jour
	 *            - entier
	 * @param a_mois
	 *            - entier
	 * @param a_annee
	 *            - entier
	 * @return boolean
	 */
	public static boolean isJourValide(int a_jour, int a_mois, int a_annee) {
		if (a_jour <= 0 || a_jour > getDayMaxOfMonth(a_mois, a_annee))
			return false;
		return true;
	}

	/**
	 * Retourne un booléen égal à true si la date est égale à la valeur 'maxi'
	 * Cristal (année = 9999 , mois = 12 et jour = 31). <br>
	 * La date '99991231' est une Spécificité Cristal. <br>
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return boolean
	 */
	public static boolean isDateMax(Date date) {
		if (date == null)
			return false;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return isDateMax(cal);
	}

	/**
	 * Retourne un booléen égal à true si la date est égale à la valeur 'maxi'
	 * Cristal (année = 9999 , mois = 12 et jour = 31). <br>
	 * La date '99991231' est une Spécificité Cristal. <br>
	 * Retourne false si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return boolean
	 */
	public static boolean isDateMax(Calendar cal) {
		if (cal == null)
			return false;
		return (getIntAnnee(cal) == 9999 && getIntMois(cal) == 12 && getIntJour(cal) == 31);
	}

	/**
	 * Retourne un booléen égal à true si la date est égale à la valeur 'mini'
	 * Cristal (année = 0001 , mois = 01 et jour = 01). <br>
	 * La date '00010101' est une Spécificité Cristal. <br>
	 * (Utilisée pour les recherches dans des intervalles). Retourne false si le
	 * paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return boolean
	 */
	public static boolean isDateMin(Date date) {
		if (date == null)
			return false;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return isDateMin(cal);
	}

	/**
	 * Retourne un booléen égal à true si la date est égale à la valeur 'mini'
	 * Cristal (année = 0001 , mois = 01 et jour = 01). <br>
	 * La date '00010101' est une Spécificité Cristal. <br>
	 * (Utilisée pour les recherches dans des intervalles). Retourne false si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return boolean
	 */
	public static boolean isDateMin(Calendar cal) {
		if (cal == null)
			return false;

		return (getIntAnnee(cal) == 1 && getIntMois(cal) == 1 && getIntJour(cal) == 1);
	}

	/**
	 * Retourne l'année sous forme d'un int Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
//	public static int getIntAnnee(Calendar cal) {
//		if (cal == null)
//			return -1;
//
//		return cal.get(Calendar.YEAR);
//	}

	/**
	 * Retourne l'année sous forme d'un int Retourne -1 si le paramètre est null
	 * Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntAnnee(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getIntAnnee(cal);
	}

	/**
	 * Retourne le mois sous forme d'un int. Retourne -1 si le paramètre est
	 * null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntMois(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * Retourne le mois sous forme d'un int. Retourne -1 si le paramètre est
	 * null Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntMois(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntMois(cal);
	}

	/**
	 * Retourne le jour sous forme d'un int Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntJour(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.DATE);
	}

	/**
	 * Retourne le jour sous forme d'un int Retourne -1 si le paramètre est null
	 * Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntJour(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntJour(cal);
	}

	/**
	 * Retourne l'heure sous forme d'un int Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntHeure(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * Retourne l'heure sous forme d'un int Retourne -1 si le paramètre est null
	 * Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntHeure(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntHeure(cal);
	}

	/**
	 * Retourne les minutes sous forme d'un int. Retourne -1 si le paramètre est
	 * null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntMinutes(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.MINUTE);
	}

	/**
	 * Retourne les minutes sous forme d'un int. Retourne -1 si le paramètre est
	 * null Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntMinutes(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntMinutes(cal);
	}

	/**
	 * Retourne les secondes sous forme d'un int. Retourne -1 si le paramètre
	 * est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntSecondes(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.SECOND);
	}

	/**
	 * Retourne les secondes sous forme d'un int. Retourne -1 si le paramètre
	 * est null Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntSecondes(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntSecondes(cal);
	}

	/**
	 * Retourne la date sous forme d'un int de format AAAAMMJJ. Retourne -1 si
	 * le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getIntDateAAAAMMJJ(Calendar cal) {
		if (cal == null)
			return -1;

		return Integer.parseInt(getAnnee(cal) + getMois(cal) + getJour(cal));
	}

	/**
	 * Retourne la date sous forme d'un int de format AAAAMMJJ. Retourne -1 si
	 * le paramètre est null Attention à privilégier la méthode avec le
	 * paramètre Calendar si plusieurs méthodes get sont appelées successivement
	 * sur ce même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getIntDateAAAAMMJJ(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getIntDateAAAAMMJJ(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format 'de libellé mois AAAA'.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateDeLibelleMoisAnnee(Calendar cal) {
		if (cal == null)
			return null;

		int intMois = getIntMois(cal);

		if (intMois == 4 || intMois == 8 || intMois == 10)
			return "d'" + getLibelleMois(cal) + " " + getAnnee(cal);
		return "de " + getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format 'de libellé mois AAAA'.
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateDeLibelleMoisAnnee(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int intMois = getIntMois(cal);

		if (intMois == 4 || intMois == 8 || intMois == 10)
			return "d'" + getLibelleMois(cal) + " " + getAnnee(cal);
		return "de " + getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format "libelléMois AAAA".
	 * Retourne null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateLibelleMoisAnnee(Calendar cal) {
		if (cal == null)
			return null;

		return getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format "libelléMois AAAA".
	 * Retourne null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateLibelleMoisAnnee(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format 'JJ libellé mois AAAA'.
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateJJLibelleMoisAAAA(Calendar cal) {
		if (cal == null)
			return null;

		return getJour(cal) + " " + getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format 'JJ libellé mois AAAA'.
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateJJLibelleMoisAAAA(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getJour(cal) + " " + getLibelleMois(cal) + " " + getAnnee(cal);
	}

	/**
	 * Retourne le libellé du mois sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getLibelleMois(Calendar cal) {
		if (cal == null)
			return null;

		return tabLibelleMois[cal.get(Calendar.MONTH)];
	}

	/**
	 * Retourne le libellé du mois sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getLibelleMois(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getLibelleMois(cal);
	}

	/**
	 * Retourne le libellé du jour sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getLibelleJour(Calendar cal) {
		if (cal == null)
			return null;

		return tabLibelleJours[getDayOfWeek(cal) - 1];
	}

	/**
	 * Retourne le libellé du jour sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getLibelleJour(Date date) {
		if (date == null)
			return null;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getLibelleJour(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ?MM?AAAA avec un
	 * délimiteur passé en parametre. Retourne null si le paramètre est null
	 * 
	 * @param delim
	 *            - caractère
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	private static String getDateJJMMAAAA(char delim, Calendar cal) {
		if (cal == null)
			return null;

		return getJour(cal) + delim + getMois(cal) + delim + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJMMAAAA. Retourne null
	 * si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateJJMMAAAA(Calendar cal) {
		if (cal == null)
			return null;

		return getJour(cal) + getMois(cal) + getAnnee(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJMMAAAA. Retourne null
	 * si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateJJMMAAAA(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDateJJMMAAAA(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ.MM.AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateJJDotMMDotAAAA(Calendar cal) {
		if (cal == null)
			return null;

		return getDateJJMMAAAA(DELIM_DOT, cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ.MM.AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateJJDotMMDotAAAA(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDateJJMMAAAA(DELIM_DOT, cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ/MM/AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateJJSlashMMSlashAAAA(Calendar cal) {
		if (cal == null)
			return null;

		return getDateJJMMAAAA(DELIM_SLASH, cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ/MM/AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateJJSlashMMSlashAAAA(Date date) {
		if (date == null)
			return null;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getDateJJSlashMMSlashAAAA(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ-MM-AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateJJMinusMMMinusAAAA(Calendar cal) {
		if (cal == null)
			return null;

		return getDateJJMMAAAA(DELIM_MINUS, cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format JJ-MM-AAAA. Retourne
	 * null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateJJMinusMMMinusAAAA(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getDateJJMinusMMMinusAAAA(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format HHMMSS. Retourne null
	 * si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateHHMMSS(Calendar cal) {
		if (cal == null)
			return null;

		StringBuffer sb = new StringBuffer("");
		sb.append(getHeure(cal)).append(getMinutes(cal)).append(
				getSecondes(cal));
		return sb.toString();
	}

	/**
	 * Retourne la date sous forme d'un String de format HHMMSS. Retourne null
	 * si le paramètre est null Attention à privilégier la méthode avec le
	 * paramètre Calendar si plusieurs méthodes get sont appelées successivement
	 * sur ce même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateHHMMSS(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDateHHMMSS(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format HHMM. Retourne null si
	 * le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getDateHHMM(Calendar cal) {
		if (cal == null)
			return null;

		StringBuffer sb = new StringBuffer("");
		sb.append(getHeure(cal)).append(getMinutes(cal));
		return sb.toString();
	}

	/**
	 * Retourne la date sous forme d'un String de format HHMM. Retourne null si
	 * le paramètre est null Attention à privilégier la méthode avec le
	 * paramètre Calendar si plusieurs méthodes get sont appelées successivement
	 * sur ce même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getDateHHMM(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDateHHMM(cal);
	}

	/**
	 * Retourne le nombre maximum de jours pour la date fournie en paramètres
	 * (mois et année).
	 * 
	 * @param mois
	 *            - entier
	 * @param annee
	 *            - entier
	 * @return entier
	 */
	public static int getDayMaxOfMonth(int mois, int annee) {
		if (mois == 2)
			return isAnneeBissextile(annee) ? 29 : 28;

		return tabDayMaxOfMonth[mois - 1];
	}

	/**
	 * Retourne le numéro du jour dans la semaine. Retourne -1 si le paramètre
	 * est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getDayOfWeek(Calendar cal) {
		if (cal == null)
			return -1;

		int j = cal.get(Calendar.DAY_OF_WEEK);
		return (j == 1) ? 7 : j - 1;
	}

	/**
	 * Retourne le numéro du jour dans la semaine. Retourne -1 si le paramètre
	 * est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getDayOfWeek(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDayOfWeek(cal);
	}

	/**
	 * Retourne le n<sup>ième</sup> jour de la semaine dans le mois. Exemple :
	 * le 18 fev 2003, retourne 3 (pour le 3<sup>ème</sup> jeudi du mois de
	 * février 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getDayOfWeekInMonth(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.DAY_OF_WEEK_IN_MONTH);
	}

	/**
	 * Retourne le n<sup>ième</sup> jour de la semaine dans le mois. Exemple :
	 * le 18 fev 2003, retourne 3 (pour le 3<sup>ème</sup> jeudi du mois de
	 * février 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getDayOfWeekInMonth(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDayOfWeekInMonth(cal);
	}

	/**
	 * Retourne le n<sup>ième</sup> jour dans l'année. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 49 (pour le 49<sup>ème</sup> jour
	 * depuis le 1er janvier).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getDayOfYear(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * Retourne le n<sup>ième</sup> jour dans l'année. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 49 (pour le 49<sup>ème</sup> jour
	 * depuis le 1er janvier).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getDayOfYear(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getDayOfYear(cal);
	}

	/**
	 * Retourne la n<sup>ième</sup> semaine dans le mois. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 4 (pour la 4<sup>ème</sup> semaine
	 * pour le mois de février 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getWeekOfMonth(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * Retourne la n<sup>ième</sup> semaine dans le mois. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 4 (pour la 4<sup>ème</sup> semaine
	 * pour le mois de février 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return entier
	 */
	public static int getWeekOfMonth(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getWeekOfMonth(cal);
	}

	/**
	 * Retourne la n<sup>ième</sup> semaine dans l'année. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 8 (pour la 8<sup>ème</sup> semaine
	 * pour l'année 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getWeekOfYear(Calendar cal) {
		if (cal == null)
			return -1;

		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Retourne la n<sup>ième</sup> semaine dans l'année. <br>
	 * <br>
	 * Exemple : le 18 fev 2003, retourne 8 (pour la 8<sup>ème</sup> semaine
	 * pour l'année 2003).
	 * 
	 * Retourne -1 si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Calendar
	 * @return entier
	 */
	public static int getWeekOfYear(Date date) {
		if (date == null)
			return -1;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getWeekOfYear(cal);
	}

	/**
	 * Retourne le nombre de jours pour l'année fournie. Retourne 366 ou 365
	 * 
	 * @param year
	 *            - entier
	 * @return entier
	 */
	public static int getMaxDaysOfYear(int year) {
		return (isAnneeBissextile(year)) ? 366 : 365;
	}

	/**
	 * Retourne le mois sous forme d'un String. Retourne null si le paramètre
	 * est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getMois(Calendar cal) {
		if (cal == null)
			return null;

		return tabj[cal.get(Calendar.MONTH) + 1];
	}

	/**
	 * Retourne le mois sous forme d'un String. Retourne null si le paramètre
	 * est null Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getMois(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getMois(cal);
	}

	/**
	 * Retourne le mois - 1 sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getMoisMoins1(Calendar cal) {
		if (cal == null)
			return null;

		int mois, moisMoins1;
		mois = getIntMois(cal);

		if (mois == 1)
			moisMoins1 = 12;
		else
			moisMoins1 = mois - 1;

		return tabj[moisMoins1];
	}

	/**
	 * Retourne le mois - 1 sous forme d'un String. Retourne null si le
	 * paramètre est null Attention à privilégier la méthode avec le paramètre
	 * Calendar si plusieurs méthodes get sont appelées successivement sur ce
	 * même objet date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getMoisMoins1(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getMoisMoins1(cal);
	}

	/**
	 * Retourne le jour sous forme d'un String. Retourne null si le paramètre
	 * est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getJour(Calendar cal) {
		if (cal == null)
			return null;

		return tabj[cal.get(Calendar.DATE)];
	}

	/**
	 * Retourne le jour sous forme d'un String. Retourne null si le paramètre
	 * est null. Attention à privilégier la méthode avec le paramètre Calendar
	 * si plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getJour(Date date) {
		if (date == null)
			return null;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getJour(cal);
	}

	/**
	 * Retourne l'heure sous forme d'un String. Retourne null si le paramètre
	 * est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getHeure(Calendar cal) {
		if (cal == null)
			return null;

		return tabj[cal.get(Calendar.HOUR_OF_DAY)];
	}

	/**
	 * Retourne l'heure sous forme d'un String. Retourne null si le paramètre
	 * est null Attention à privilégier la méthode avec le paramètre Calendar si
	 * plusieurs méthodes get sont appelées successivement sur ce même objet
	 * date
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getHeure(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getHeure(cal);
	}

	/**
	 * Retourne les minutes sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getMinutes(Calendar cal) {
		if (cal == null)
			return null;

		return tabj[cal.get(Calendar.MINUTE)];
	}

	/**
	 * Retourne les minutes sous forme d'un String. Retourne null si le
	 * paramètre est null Attention à privilégier la méthode avec le paramètre
	 * Calendar si plusieurs méthodes get sont appelées successivement sur ce
	 * même objet date
	 * 
	 * @param date
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getMinutes(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getMinutes(cal);
	}

	/**
	 * Retourne les secondes sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return String
	 */
	public static String getSecondes(Calendar cal) {
		if (cal == null)
			return null;

		return tabj[cal.get(Calendar.SECOND)];
	}

	/**
	 * Retourne les secondes sous forme d'un String. Retourne null si le
	 * paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return String
	 */
	public static String getSecondes(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return getSecondes(cal);
	}

	/**
	 * Retourne une Date correspondant au 1er jour du mois qui précède. Retourne
	 * null si le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return java.util.Date
	 */
	public static Date getDatePremierJourMoisMoins1(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getDatePremierJourMoisMoins1(cal);
	}

	/**
	 * Retourne une Date correspondant au 1er jour du mois qui précède. Retourne
	 * null si le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return java.util.Date
	 */
	public static Date getDatePremierJourMoisMoins1(Calendar cal) {
		if (cal == null)
			return null;

		return (getMois(cal).equals("01")) ? createDate(getAnneeMoins1(cal)
				+ getMoisMoins1(cal) + "01") : createDate(getAnnee(cal)
				+ getMoisMoins1(cal) + "01");
	}

	/**
	 * Retourne une Date correspondant au 1er jour du mois. Retourne null si le
	 * paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return java.util.Date
	 */
	public static Date getDatePremierJourMois(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getDatePremierJourMois(cal);
	}

	/**
	 * Retourne une Date correspondant au 1er jour du mois. Retourne null si le
	 * paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return java.util.Date
	 */
	public static Date getDatePremierJourMois(Calendar cal) {
		if (cal == null)
			return null;

		return createDate(getAnnee(cal) + getMois(cal) + "01");
	}

	/**
	 * Retourne une Date correspondant au dernier jour du mois. Retourne null si
	 * le paramètre est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return java.util.Date
	 */
	public static Date getDateDernierJourMois(Date date) {
		if (date == null)
			return null;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);

		return getDateDernierJourMois(cal);
	}

	/**
	 * Retourne une Date correspondant au dernier jour du mois. Retourne null si
	 * le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return java.util.Date
	 */
	public static Date getDateDernierJourMois(Calendar cal) {
		if (cal == null)
			return null;

		return createDate(getAnnee(cal) + getMois(cal)
				+ getDayMaxOfMonth(getIntMois(cal), getIntAnnee(cal)));
	}

	/**
	 * Retourne une Date correspondant au dernier jour du mois. Retourne null si
	 * le paramètre est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return java.util.Calendar
	 */
	public static Calendar getGregCalDernierJourMois(Calendar cal) {
		if (cal == null)
			return null;

		return createGregCalendar(getAnnee(cal) + getMois(cal)
				+ getDayMaxOfMonth(getIntMois(cal), getIntAnnee(cal)));
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour. <br>
	 * Retourne un entier : -1 : La 1</up>ère</sup> date est strictement
	 * inférieure à la 2<sup>ème</sup>. 0 : La 1</up>ère</sup> date est égale à
	 * la 2<sup>ème</sup>. 1 : La 1</up>ère</sup> date est strictement
	 * supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (date,null) retourne 1<br>
	 * (null,null) retourne 0<br>
	 * (null,date) retourne -1<br>
	 * 
	 * @param date1
	 *            - java.util.Date
	 * @param date2
	 *            - java.util.Date
	 * @return entier
	 * @throws JardinException
	 *             - CnafDate_01
	 */
//	public static int compareToAAAAMMJJ(Date date1, Date date2)
//			throws JardinException {
//
//		if (date1 == null && date2 == null)
//			return 0;
//		
//		if (date1 == null)
//			return -1;
//		
//		if (date2== null)
//			return 1;
//				
//		Calendar cal1 = new GregorianCalendar();
//		cal1.setTime(date1);
//		Calendar cal2 = new GregorianCalendar();
//		cal2.setTime(date2);
//
//		return compareToAAAAMMJJ(cal1, cal2);
//	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour. <br>
	 * Retourne un entier : -1 : La 1</up>ère</sup> date est strictement
	 * inférieure à la 2<sup>ème</sup>. 0 : La 1</up>ère</sup> date est égale à
	 * la 2<sup>ème</sup>. 1 : La 1</up>ère</sup> date est strictement
	 * supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (cal,null) retourne 1<br>
	 * (null,null) retourne 0<br>
	 * (null,cal) retourne -1<br>
	 * 
	 * @param cal1
	 *            - java.util.Calendar
	 * @param cal2
	 *            - java.util.Calendar
	 * @return entier
	 * @throws JardinException
	 *             - CnafDate_01
	 */
//	public static int compareToAAAAMMJJ(Calendar cal1, Calendar cal2)
//			throws JardinException {
//
//		if (cal1 == null && cal2 == null)
//			return 0;
//		
//		if (cal1 == null)
//			return -1;
//		
//		if (cal2== null)
//			return 1;
//
//		if (getIntAnnee(cal1) != getIntAnnee(cal2))
//			return (getIntAnnee(cal1) < getIntAnnee(cal2) ? -1 : 1);
//		else if (getIntMois(cal1) != getIntMois(cal2))
//			return (getIntMois(cal1) < getIntMois(cal2) ? -1 : 1);
//		else if (getIntJour(cal1) != getIntJour(cal2))
//			return (getIntJour(cal1) < getIntJour(cal2) ? -1 : 1);
//		else
//			return 0;
//	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour/heure/minute/seconde. <br>
	 * Retourne un entier : -1 : La 1</up>ère</sup> date est strictement
	 * inférieure à la 2<sup>ème</sup>. 0 : La 1</up>ère</sup> date est égale à
	 * la 2<sup>ème</sup>. 1 : La 1</up>ère</sup> date est strictement
	 * supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (date,null) retourne 1<br>
	 * (null,null) retourne 0<br>
	 * (null,date) retourne -1<br>
	 * 
	 * @param date1
	 *            - java.util.Date
	 * @param date2
	 *            - java.util.Date
	 * @return entier
	 * @throws JardinException
	 *             - CnafDate_01
	 */
	public static int compareToAAAAMMJJHHMMSS(Date date1, Date date2)
			throws JardinException {

		if (date1 == null && date2 == null)
			return 0;
		
		if (date1 == null)
			return -1;
		
		if (date2== null)
			return 1;

		Calendar cal1 = new GregorianCalendar();
		cal1.setTime(date1);
		Calendar cal2 = new GregorianCalendar();
		cal2.setTime(date2);

		return compareToAAAAMMJJHHMMSS(cal1, cal2);
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour/heure/minute/seconde. <br>
	 * Retourne un entier : -1 : La 1</up>ère</sup> date est strictement
	 * inférieure à la 2<sup>ème</sup>. 0 : La 1</up>ère</sup> date est égale à
	 * la 2<sup>ème</sup>. 1 : La 1</up>ère</sup> date est strictement
	 * supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (cal,null) retourne 1<br>
	 * (null,null) retourne 0<br>
	 * (null,cal) retourne -1<br>
	 * 
	 * @param cal1
	 *            - java.util.Calendar
	 * @param cal2
	 *            - java.util.Calendar
	 * @return entier
	 * @throws JardinException
	 *             - CnafDate_01
	 */
	public static int compareToAAAAMMJJHHMMSS(Calendar cal1, Calendar cal2)
			throws JardinException {

		if (cal1 == null && cal2 == null)
			return 0;
		
		if (cal1 == null)
			return -1;
		
		if (cal2== null)
			return 1;

		if (getIntAnnee(cal1) != getIntAnnee(cal2))
			return (getIntAnnee(cal1) < getIntAnnee(cal2) ? -1 : 1);
		else if (getIntMois(cal1) != getIntMois(cal2))
			return (getIntMois(cal1) < getIntMois(cal2) ? -1 : 1);
		else if (getIntJour(cal1) != getIntJour(cal2))
			return (getIntJour(cal1) < getIntJour(cal2) ? -1 : 1);
		else if (getIntHeure(cal1) != getIntHeure(cal2))
			return (getIntHeure(cal1) < getIntHeure(cal2) ? -1 : 1);
		else if (getIntMinutes(cal1) != getIntMinutes(cal2))
			return (getIntMinutes(cal1) < getIntMinutes(cal2) ? -1 : 1);
		else if (getIntSecondes(cal1) != getIntSecondes(cal2))
			return (getIntSecondes(cal1) < getIntSecondes(cal2) ? -1 : 1);
		else
			return 0;
	}

	/**
	 * Vérifie si la date est comprise dans l'intervalle des 2 dates. <br>
	 * <li>1<sup>er</sup> paramètre : date à tester <li>2<sup>nd</sup> paramètre
	 * : date début de période (début intervalle) <li>3<sup>ème</sup> paramètre
	 * : date fin de période (fin intervalle) <br>
	 * Cette méthode retourne un booléen égal à true si la 1<sup>ère</sup> date
	 * est : <br> <li>supérieure ou égale à la 2<sup>ème</sup> <b>et</b> <li>
	 * inférieure ou égale à la 3<sup>ème</sup>.
	 * 
	 * (date, null, null) retourne false<br>
	 * (null, null, null) retourne true<br>
	 * (date, null, date) retourne false<br>
	 * (date, date, null) retourne false<br>
	 * (null, null, date) retourne true<br>
	 * (null, date, null) retourne false<br>
	 * (null, date, date) retourne false<br>
	 * 
	 * @param dateAAAAMMJJ
	 *            - String
	 * @param dateDebutAAAAMMJJ
	 *            - String
	 * @param dateFinAAAAMMJJ
	 *            - String
	 * @return boolean
	 * @throws JardinException
	 *             - CnafDate_04
	 */
	public static boolean isDateCompriseDansLaPeriode(String dateAAAAMMJJ,
			String dateDebutAAAAMMJJ, String dateFinAAAAMMJJ)
			throws JardinException {
			
		if (dateAAAAMMJJ==null && dateDebutAAAAMMJJ==null)
			return true;		
			
	
		if ((dateAAAAMMJJ) == null || dateDebutAAAAMMJJ == null
				|| dateFinAAAAMMJJ == null) 
			return false;

		Date date = createDate(dateAAAAMMJJ);
		Date dateDebut = createDate(dateDebutAAAAMMJJ);
		Date dateFin = createDate(dateFinAAAAMMJJ);

		return isDateCompriseDansLaPeriode(date, dateDebut, dateFin);
	}

	/**
	 * Vérifie si la date est comprise dans l'intervalle des 2 dates. <br>
	 * <li>1<sup>er</sup> paramètre : date à tester <li>2<sup>nd</sup> paramètre
	 * : date début de période (début intervalle) <li>3<sup>ème</sup> paramètre
	 * : date fin de période (fin intervalle) <br>
	 * Cette méthode retourne un booléen égal à true si la 1<sup>ère</sup> date
	 * est : <br> <li>supérieure ou égale à la 2<sup>ème</sup> <b>et</b> <li>
	 * inférieure ou égale à la 3<sup>ème</sup>.<br>
	 * 
	 * (cal, null, null) retourne false<br>
	 * (null, null, null) retourne true<br>
	 * (cal, null, cal) retourne false<br>
	 * (cal, cal, null) retourne false<br>
	 * (null, null, cal) retourne true<br>
	 * (null, cal, null) retourne false<br> 
	 * (null, cal, cal) retourne false<br>
	 * 
	 * @param calAAAAMMJJ
	 *            - java.util.Calendar
	 * @param calDebutAAAAMMJJ
	 *            - java.util.Calendar
	 * @param calFinAAAAMMJJ
	 *            - java.util.Calendar
	 * @return boolean
	 * @throws JardinException
	 *             - CnafDate_04
	 */
	public static boolean isDateCompriseDansLaPeriode(Calendar calAAAAMMJJ,
			Calendar calDebutAAAAMMJJ, Calendar calFinAAAAMMJJ)
			throws JardinException {

		if (calAAAAMMJJ==null && calDebutAAAAMMJJ==null)
			return true;			
	
		if ((calAAAAMMJJ) == null || calDebutAAAAMMJJ == null
				|| calFinAAAAMMJJ == null) 
			return false;
		

		if (calAAAAMMJJ.getTimeInMillis() >= calDebutAAAAMMJJ.getTimeInMillis()
				&& calAAAAMMJJ.getTimeInMillis() <= calFinAAAAMMJJ
						.getTimeInMillis())
			return true;
		return false;
	}

	/**
	 * Vérifie si la date est comprise dans l'intervalle des 2 dates. <br>
	 * <li>1<sup>er</sup> paramètre : date à tester <li>2<sup>nd</sup> paramètre
	 * : date début de période (début intervalle) <li>3<sup>ème</sup> paramètre
	 * : date fin de période (fin intervalle) <br>
	 * Cette méthode retourne un booléen égal à true si la 1<sup>ère</sup> date
	 * est : <br> <li>supérieure ou égale à la 2<sup>ème</sup> <b>et</b> <li>
	 * inférieure ou égale à la 3<sup>ème</sup>.<br>
	 * 
	 * (date, null, null) retourne false<br>
	 * (null, null, null) retourne true<br>
	 * (date, null, date) retourne false<br>
	 * (date, date, null) retourne false<br>
	 * (null, null, date) retourne true<br>
	 * (null, date, null) retourne false<br>
	 * (null, date, date) retourne false<br>
	 * 
	 * @param dateAAAAMMJJ
	 *            - java.util.Date
	 * @param dateDebutAAAAMMJJ
	 *            - java.util.Date
	 * @param dateFinAAAAMMJJ
	 *            - java.util.Date
	 * @return boolean
	 * @throws JardinException
	 *             - CnafDate_04
	 */
	public static boolean isDateCompriseDansLaPeriode(Date dateAAAAMMJJ,
			Date dateDebutAAAAMMJJ, Date dateFinAAAAMMJJ) throws JardinException {

		if (dateAAAAMMJJ==null && dateDebutAAAAMMJJ==null)
			return true;		
	
		if ((dateAAAAMMJJ) == null || dateDebutAAAAMMJJ == null
				|| dateFinAAAAMMJJ == null) 
			return false;

		if (dateAAAAMMJJ.getTime() >= dateDebutAAAAMMJJ.getTime()
				&& dateAAAAMMJJ.getTime() <= dateFinAAAAMMJJ.getTime())
			return true;
		return false;
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour. <br>
	 * 
	 * (date,null) retourne false<br>
	 * (null,null) retourne false<br>
	 * (null,date) retourne true<br> 
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isDateInferieure(Date date, Date dateDeComparaison)
			throws JardinException {

		if (dateDeComparaison == null)
			return false;

		if (date == null)
			return true;
		
		return (compareToAAAAMMJJ(date, dateDeComparaison) == -1);
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères année/mois/jour. <br>
	 * 
	 * (cal,null) retourne false<br>
	 * (null,null) retourne false<br>
	 * (null,cal) retourne true<br>   
	 * 
	 * @return boolean
	 * @param cal
	 *            Calendar
	 * @param calDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isDateInferieure(Calendar cal,
			Calendar calDeComparaison) throws JardinException {

		if (calDeComparaison == null)
			return false;

		if (cal == null)
			return true;

		return (compareToAAAAMMJJ(cal, calDeComparaison) == -1);
	}

	/**
	 * Détermine si la 1<sup>ère</sup> date est inférieure ou égale à la
	 * 2<sup>ème</sup> date.
	 * 
	 * (date1,null) retourne false<br>
	 * (null, null) retourne true<br>
	 * (null,date1) retourne true<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isDateInferieureOuEgale(Date date,
			Date dateDeComparaison) throws JardinException {
		if (date == null && dateDeComparaison == null)
			return true;

		return (isDateInferieure(date, dateDeComparaison))
				|| (isDatesEgales(date, dateDeComparaison));
	}

	/**
	 * Détermine si la 1<sup>ère</sup> date est inférieure ou égale à la
	 * 2<sup>ème</sup> date.
	 * 
	 * (cal,null) retourne false<br>
	 * (null, null) retourne true<br>
	 * (null,cal) retourne true<br>
	 * 
	 * @return boolean
	 * @param cal
	 *            Calendar
	 * @param calDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isDateInferieureOuEgale(Calendar cal,
			Calendar calDeComparaison) throws JardinException {
		if (cal == null && calDeComparaison == null)
			return true;

		return (isDateInferieure(cal, calDeComparaison))
				|| (isDatesEgales(cal, calDeComparaison));

	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères
	 * année/mois/jour/heure/minute/seconde. Retourne un booléen qui est à true
	 * si la 1</up>ère</sup> date est strictement inférieure à la
	 * 2<sup>ème</sup>.
	 * 
	 * (date,null) retourne false<br>
	 * (null,null) retourne false<br>
	 * (null,date) retourne true<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isAAAAMMJJHHMMSS_Inferieure(Date date,
			Date dateDeComparaison) throws JardinException {

		if (date == null && dateDeComparaison == null)
			return false;

		if (date == null)
			return true;
		
		if (dateDeComparaison == null)
			return false;
		
		return (compareToAAAAMMJJHHMMSS(date, dateDeComparaison) == -1);
	}

	/**
	 * Teste l'infériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères
	 * année/mois/jour/heure/minute/seconde. Retourne un booléen qui est à true
	 * si la 1</up>ère</sup> date est strictement inférieure à la
	 * 2<sup>ème</sup>.
	 * 
	 * (cal,null) retourne false<br>
	 * (null,null) retourne false<br>
	 * (null,cal) retourne true<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Calendar
	 * @param dateDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isAAAAMMJJHHMMSS_Inferieure(Calendar cal,
			Calendar calDeComparaison) throws JardinException {

		if (cal == null && calDeComparaison == null)
			return false;

		if (cal == null)
			return true;
		
		if (calDeComparaison == null)
			return false;

		return (compareToAAAAMMJJHHMMSS(cal, calDeComparaison) == -1);
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * strictement supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (date,null) retourne true<br>
	 * (null,null) retourne false<br>
	 * (null,date) retourne false<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isDateSuperieure(Date date, Date dateDeComparaison)
			throws JardinException {

		if (date == null)
			return false;

		if (dateDeComparaison == null)
			return true;

		return (compareToAAAAMMJJ(date, dateDeComparaison) == 1);
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * strictement supérieure à la 2<sup>ème</sup>.<br>
	 * 
	 * (cal,null) retourne true<br>
	 * (null,null) retourne false<br>
	 * (null,cal) retourne false<br>
	 * 
	 * @return boolean
	 * @param cal
	 *            Calendar
	 * @param calDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isDateSuperieure(Calendar cal,
			Calendar calDeComparaison) throws JardinException {
		if (cal == null)
			return false;

		if (calDeComparaison == null)
			return true;

		return (compareToAAAAMMJJ(cal, calDeComparaison) == 1);
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * supérieure ou égale à la 2<sup>ème</sup>.<br>
	 * 
	 * (date,null) retourne true<br>
	 * (null,null) retourne true<br>
	 * (null,date) retourne false<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isDateSuperieureOuEgale(Date date,
			Date dateDeComparaison) throws JardinException {

		return (isDateSuperieure(date, dateDeComparaison) || isDatesEgales(
				date, dateDeComparaison));
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * supérieure ou égale à la 2<sup>ème</sup>.<br>
	 * 
	 * (date,null) retourne true<br>
	 * (null,null) retourne true<br>
	 * (null,date) retourne false<br>
	 * 
	 * @return boolean
	 * @param cal
	 *            Calendar
	 * @param calDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isDateSuperieureOuEgale(Calendar cal,
			Calendar calDeComparaison) throws JardinException {

		return (isDateSuperieure(cal, calDeComparaison) || isDatesEgales(cal,
				calDeComparaison));
	}

	/**
	 * Teste la supériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères
	 * année/mois/jour/heure/minute/seconde. Retourne un booléen qui est à true
	 * si la 1</up>ère</sup> date est strictement supérieure à la
	 * 2<sup>ème</sup>.
	 * 
	 * (date,null) retourne true<br>
	 * (null,null) retourne false<br>
	 * (null,date) retourne false<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Date
	 * @param dateDeComparaison
	 *            Date
	 * @exception JardinException
	 */
	public static boolean isAAAAMMJJHHMMSS_Superieure(Date date,
			Date dateDeComparaison) throws JardinException {

		if (date == null)
			return false;

		if (dateDeComparaison == null)
			return true;

		return (compareToAAAAMMJJHHMMSS(date, dateDeComparaison) == 1);
	}

	/**
	 * Teste la supériorité stricte de la 1<sup>ère</sup> date fournie à la
	 * 2<sup>ème</sup> date, pour les critères
	 * année/mois/jour/heure/minute/seconde. Retourne un booléen qui est à true
	 * si la 1</up>ère</sup> date est strictement supérieure à la
	 * 2<sup>ème</sup>.
	 * 
	 * (date,null) retourne true<br>
	 * (null,null) retourne false<br>
	 * (null,date) retourne false<br>
	 * 
	 * @return boolean
	 * @param date
	 *            Calendar
	 * @param dateDeComparaison
	 *            Calendar
	 * @exception JardinException
	 */
	public static boolean isAAAAMMJJHHMMSS_Superieure(Calendar cal,
			Calendar calDeComparaison) throws JardinException {

		if (cal == null)
			return false;

		if (calDeComparaison == null)
			return true;

		return (compareToAAAAMMJJHHMMSS(cal, calDeComparaison) == 1);
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * strictement égale à la 2<sup>ème</sup>.
	 * 
	 * (date,null) retourne false 
	 * (null, null) retourne true
	 * (null,date) retourne false
	 *  
	 * @return boolean
	 * @param date1
	 *            Date
	 * @param date2
	 *            Date
	 */
	public static boolean isDatesEgales(Date date1, Date date2) {
		if (date1 == null && date2 == null) {
			return true;
		}
		if (date1 == null || date2 == null) {
			return false;
		}
		Calendar cal1 = createGregCalendar(date1);
		Calendar cal2 = createGregCalendar(date2);

		if (getIntAnnee(cal1) != getIntAnnee(cal2))
			return false;
		else if (getIntMois(cal1) != getIntMois(cal2))
			return false;
		else if (getIntJour(cal1) != getIntJour(cal2))
			return false;
		else
			return true;
	}

	/**
	 * Retourne un booléen qui est à true si la 1<sup>ère</sup> date est
	 * strictement égale à la 2<sup>ème</sup>.
	 * 
	 * (cal,null) retourne false 
	 * (null, null) retourne true
	 * (null,cal) retourne false
	 * 
	 * @return boolean
	 * @param cal1
	 *            Calendar
	 * @param cal2
	 *            Calendar
	 */
	public static boolean isDatesEgales(Calendar cal1, Calendar cal2) {
		if (cal1 == null && cal2 == null) {
			return true;
		}
		if (cal1 == null || cal2 == null) {
			return false;
		}

		if (getIntAnnee(cal1) != getIntAnnee(cal2))
			return false;
		else if (getIntMois(cal1) != getIntMois(cal2))
			return false;
		else if (getIntJour(cal1) != getIntJour(cal2))
			return false;
		else
			return true;
	}

	/**
	 * Teste la validité de l'année passée en paramètre. L'année doit être
	 * supérieure à 0.
	 * 
	 * @param a_annee
	 *            l'année à tester
	 * @return boolean
	 */
	public static boolean isAnneeValide(int a_annee) {
		if (a_annee < 0)
			return false;
		return true;
	}

	/**
	 * Teste la validité du mois passé en paramètre. Le mois doit &ecirc;tre
	 * supérieur à 0 et inférieur à 13.
	 * 
	 * @param a_mois
	 *            le mois à tester
	 * @return boolean
	 */
	public static boolean isMoisValide(int a_mois) {
		if (a_mois <= 0 || a_mois > 12)
			return false;
		return true;
	}

	/**
	 * Retourne la date sous forme d'un long de format AAAAMMJJ. Retourne -1 si
	 * la date est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return long
	 */
	public static long toLongAAAAMMJJ(Calendar cal) {
		if (cal == null)
			return -1;

		return Long.parseLong(getDateAAAAMMJJ(cal));
	}

	/**
	 * Retourne la date sous forme d'un long de format AAAAMMJJ. Retourne -1 si
	 * la date est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return long
	 */
	public static long toLongAAAAMMJJ(Date date) {
		if (date == null)
			return -1;

		return Long.parseLong(getDateAAAAMMJJ(date));
	}

	/**
	 * Retourne la date sous forme d'un long de format HHMMSS. Retourne -1 si la
	 * date est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return long
	 */
	public static long toLongHHMMSS(Calendar cal) {
		if (cal == null)
			return -1;

		return Long.parseLong(getDateHHMMSS(cal));
	}

	/**
	 * Retourne la date sous forme d'un long de format HHMMSS. Retourne -1 si la
	 * date est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return long
	 */
	public static long toLongHHMMSS(Date date) {
		if (date == null)
			return -1;

		return Long.parseLong(getDateHHMMSS(date));
	}

	/**
	 * Retourne la date sous forme d'un long de format HHMM. Retourne -1 si la
	 * date est null
	 * 
	 * @param cal
	 *            - java.util.Calendar
	 * @return long
	 */
	public static long toLongHHMM(Calendar cal) {
		if (cal == null)
			return -1;

		return Long.parseLong(getDateHHMM(cal));
	}

	/**
	 * Retourne la date sous forme d'un long de format HHMM. Retourne -1 si la
	 * date est null
	 * 
	 * @param date
	 *            - java.util.Date
	 * @return long
	 */
	public static long toLongHHMM(Date date) {
		if (date == null)
			return -1;

		return Long.parseLong(getDateHHMM(date));
	}


	/**
	 * Retourne la date sous forme d'un String de format 'JJ/MM/AAAA HH:MM:SS,MS'.<br>
	 * 
	 * Surcharge de la méthode toString().
	 * @param Date date
	 * @return java.lang.String
	 */
	public static String toString(Date date) {
		if (date == null)
			return null;
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		
		return toString(cal);
	}

	/**
	 * Retourne la date sous forme d'un String de format 'JJ/MM/AAAA HH:MM:SS,MS'.<br>
	 * 
	 * Surcharge de la méthode toString().
	 * @param Calendar cal
	 * @return java.lang.String
	 */
	public static String toString(Calendar cal) {
		if (cal!= null)
		return getJour(cal)
			+ "/"
			+ getMois(cal)
			+ "/"
			+ getAnnee(cal)
			+ " "
			+ getHeure(cal)
			+ ":"
			+ getMinutes(cal)
			+ ":"
			+ getSecondes(cal)
			+ ","
			+ cal.get(Calendar.MILLISECOND);
		
		return "";
	}
	/**
	 * Generation d'une exception de type JardinException
	 * 
	 * @param errId
	 * @param message
	 * @return
	 */
	private static JardinException genererJardinException(String errId,
			String message) {
		JardinException ex = new JardinException(errId, message);
		return ex;
	}
}

