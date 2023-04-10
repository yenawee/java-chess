package chess.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.
                    getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        } catch (final SQLException e) {
            throw new DBConnectionException("DB 연결 오류");
        }
    }
}
