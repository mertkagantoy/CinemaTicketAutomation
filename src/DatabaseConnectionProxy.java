import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionProxy implements IDatabaseAdapter {
    private DatabaseAdapter dbAdapter;

    public DatabaseConnectionProxy() {
        this.dbAdapter = DatabaseAdapter.getInstance();
    }

    @Override
    public Connection getConnection() throws SQLException {
        // Proxy logic can be added here
        return dbAdapter.getConnection();
    }

    @Override
    public void closeConnection() throws SQLException {
        // Proxy logic can be added here
        dbAdapter.closeConnection();
    }
}
