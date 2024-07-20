import java.sql.Connection;
import java.sql.SQLException;

public interface IDatabaseAdapter {
    Connection getConnection() throws SQLException;
    void closeConnection() throws SQLException;
}
