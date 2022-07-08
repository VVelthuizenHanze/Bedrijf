package bedrijf.database;

import bedrijf.model.Afdeling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Vincent Velthuizen <v.r.velthuizen@pl.hanze.nl>
 * <p>
 * Haal afdelingen uit de database en sla ze daar weer in op
 */
public class AfdelingDAO extends AbstractDAO {

    public AfdelingDAO(DBaccess dBaccess) {
        super(dBaccess);
    }

    public void slaAfdelingOp(Afdeling afdeling) {
        String sql = "INSERT INTO afdeling (afdelingsnaam, afdelingsplaats) VALUES (?, ?);";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, afdeling.getAfdelingsNaam());
            preparedStatement.setString(2, afdeling.getAfdelingsPlaats());
            executeManipulateStatement();
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }
    }

    public ArrayList<Afdeling> geefAfdelingen() {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();
        String sql = "SELECT afdelingsnaam, afdelingsplaats FROM afdeling;";

        try {
            setupPreparedStatement(sql);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                afdelingen.add(getAfdelingFromResultSet(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }

        return afdelingen;
    }

    public ArrayList<Afdeling> geefAfdelingenMetPlaats(String afdelingsPlaats) {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();
        String sql = "SELECT afdelingsnaam, afdelingsplaats FROM afdeling WHERE afdelingsplaats = ?;";

        try {
            setupPreparedStatement(sql);
            preparedStatement.setString(1, afdelingsPlaats);
            ResultSet resultSet = executeSelectStatement();
            while (resultSet.next()) {
                afdelingen.add(getAfdelingFromResultSet(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }

        return afdelingen;
    }

    private Afdeling getAfdelingFromResultSet(ResultSet resultSet) throws SQLException {
        return new Afdeling(
                resultSet.getString(1),
                resultSet.getString(2));
    }
}
