package bedrijf.database;

import bedrijf.model.Persoon;

import java.sql.SQLException;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Sla de eigenschappen van een persoon op in de DB
 */
public abstract class PersoonDAO extends AbstractDAO {

    public PersoonDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    protected int slaPersoonOp(Persoon persoon) {
        int primaryKey = -1;
        String sql = "INSERT INTO persoon (naam, woonplaats, afdeling) VALUES (?, ?, ?);";

        try {
            setupPreparedStatementWithKey(sql);
            preparedStatement.setString(1, persoon.getNaam());
            preparedStatement.setString(2, persoon.getWoonplaats());
            preparedStatement.setString(3, persoon.getAfdeling().getAfdelingsNaam());
            primaryKey = executeInsertStatementWithKey();
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }

        return primaryKey;
    }
}
