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
public class AfdelingDAO {

    private DBaccess dBaccess;

    public AfdelingDAO(DBaccess dBaccess) {
        this.dBaccess = dBaccess;
    }

    public void slaAfdelingOp(Afdeling afdeling) {
        String sql = "INSERT INTO afdeling (afdelingsnaam, afdelingsplaats) VALUES (?, ?);";

        try {
            PreparedStatement preparedStatement = dBaccess.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, afdeling.getAfdelingsNaam());
            preparedStatement.setString(2, afdeling.getAfdelingsPlaats());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }
    }

    public ArrayList<Afdeling> geefAfdelingen() {
        ArrayList<Afdeling> afdelingen = new ArrayList<>();
        String sql = "SELECT afdelingsnaam, afdelingsplaats FROM afdeling;";

        try {
            PreparedStatement preparedStatement = dBaccess.getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
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
            PreparedStatement preparedStatement = dBaccess.getConnection().prepareStatement(sql);
            preparedStatement.setString(1, afdelingsPlaats);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                afdelingen.add(getAfdelingFromResultSet(resultSet));
            }
        } catch (SQLException sqlException) {
            sqlFoutMelding(sqlException);
        }

        return afdelingen;
    }

    private void sqlFoutMelding(SQLException sqlException) {
        System.err.println("SQL heeft de volgende melding gegeven: " + sqlException.getMessage());
    }

    private Afdeling getAfdelingFromResultSet(ResultSet resultSet) throws SQLException {
        return new Afdeling(
                resultSet.getString(1),
                resultSet.getString(2));
    }
}
