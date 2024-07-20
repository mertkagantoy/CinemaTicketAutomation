import java.sql.SQLException;
import java.util.List;

public class SinemaService {
    private FilmDAO filmDAO;
    private SeansDAO seansDAO;

    public SinemaService(IDatabaseAdapter dbAdapter) {
        this.filmDAO = new FilmDAO(dbAdapter);
        this.seansDAO = new SeansDAO(dbAdapter);
    }

    public List<Film> getAllFilms() throws SQLException {
        return filmDAO.getAllFilms();
    }

    public List<Seans> getSeanslarByFilmId(int filmId) throws SQLException {
        return seansDAO.getSeanslarByFilmId(filmId);
    }

    public boolean buyTickets(int seansId, int numTickets) throws SQLException {
        Seans seans = seansDAO.getSeansById(seansId);
        if (seans.getBiletStogu() >= numTickets) {
            seans.setBiletStogu(seans.getBiletStogu() - numTickets);
            seansDAO.updateBiletStogu(seansId, seans.getBiletStogu());
            return true;
        }
        return false;
    }
}
