package bedrijf.database;

import bedrijf.model.Werknemer;

import java.sql.SQLException;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Sla een werknemer op in de DB
 */
public class WerknemerDAO extends PersoonDAO {

    public WerknemerDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaWerknemerOp(Werknemer werknemer) {
        int primaryKey = slaPersoonOp(werknemer);
        String sql = "INSERT INTO werknemer (personeelsnummer, maandsalaris) VALUES (?, ?);";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setInt(1, primaryKey);
            preparedStatement.setDouble(2, werknemer.getMaandSalaris());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }
    }
}
