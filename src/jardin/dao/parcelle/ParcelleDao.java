package jardin.dao.parcelle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jardin.constante.CsteDao;
import jardin.dao.utils.CommonDao;
import jardin.model.BmParcelle;
import jardin.technique.JardinException;
import jardin.technique.UtilsDao;

public class ParcelleDao {
	// ECRITURE
	public static void ajouterParcelle(BmParcelle b) throws JardinException {
		try {
			String req = "insert into " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PARCELLE;
			req = req + " values (";
			req = req + CommonDao.getNextID(CsteDao.TABLE_PARCELLE, CsteDao.COLUMN_ID_PARCELLE) + ",";
			req = req + "'" + b.getNom() + "',";
			req = req + "'" + b.getLargeur() + "',";
			req = req + "'" + b.getLongueur() + "',";
			req = req + "'" + b.getExposition() + "'";
			req = req + ")";

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur ajouterParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}
	}

	public static void modifierParcelle(BmParcelle b) throws JardinException {
 
		try {
			String req = "update " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PARCELLE;
			req = req + " set ";
			req = req + " " + CsteDao.COLUMN_NOM + " = '" + b.getNom() + "' , ";
			req = req + " " + CsteDao.COLUMN_LARGEUR + " = " +b.getLargeur() + " , ";
			req = req + " " + CsteDao.COLUMN_LONGUEUR + " = " +b.getLongueur() + " , ";
			req = req + " " + CsteDao.COLUMN_EXPOSITION + " = '" +b.getExposition() + "' ";
			req = req + " where " + CsteDao.COLUMN_ID_PARCELLE + " = " + b.getIdParcelle();

			UtilsDao.executeUpdateQuery(req);

		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur modifierParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}

	}

	// LECTURE
	public static List<BmParcelle> getListeParcelle() throws JardinException {
		String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PARCELLE;
		List<BmParcelle> l = new ArrayList<BmParcelle>();
		try {
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				l.add(getBmParcelle(r));

			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getListeParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}
		return l;
	}

	public static BmParcelle getParcelle(int idParcelle) throws JardinException {
		try {
			String req = "select * from " + CsteDao.DATABASE_NAME + "." + CsteDao.TABLE_PARCELLE + " where "
					+ CsteDao.COLUMN_ID_PARCELLE + " = " + idParcelle;
			ResultSet r = UtilsDao.executeQuery(req);

			while (r.next()) {
				return getBmParcelle(r);
			}
		} catch (SQLException s) {
			JardinException j = new JardinException();
			j.setMessage("Erreur getParcelle");
			j.setDetail(s.getMessage());
			throw j;
		}

		throw new JardinException("Parcelle avec id " + idParcelle + " introuvable");
	}

	private static BmParcelle getBmParcelle(ResultSet r) throws SQLException {
		BmParcelle c = new BmParcelle();
		c.setIdParcelle(r.getInt(CsteDao.COLUMN_ID_PARCELLE));
		c.setNom(r.getString(CsteDao.COLUMN_NOM));
		c.setLargeur(r.getDouble(CsteDao.COLUMN_LARGEUR));
		c.setLongueur(r.getDouble(CsteDao.COLUMN_LONGUEUR));
		c.setExposition(r.getString(CsteDao.COLUMN_EXPOSITION));
		return c;
	}

}
