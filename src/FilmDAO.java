import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO {
    private IDatabaseAdapter dbAdapter;  // Burada DatabaseAdapter yerine IDatabaseAdapter kullanılıyor

    public FilmDAO(IDatabaseAdapter dbAdapter) {
        this.dbAdapter = dbAdapter;
    }

    public List<Film> getAllFilms() throws SQLException {
        Connection connection = dbAdapter.getConnection();
        String query = "SELECT * FROM filmler";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        List<Film> filmler = new ArrayList<>();
        while (resultSet.next()) {
            Film film = new Film(
                    resultSet.getInt("film_id"),
                    resultSet.getString("ad"),
                    resultSet.getString("yonetmen"),
                    resultSet.getString("tur"),
                    resultSet.getInt("sure")
            );
            filmler.add(film);
        }

        dbAdapter.closeConnection();
        return filmler;
    }
}
