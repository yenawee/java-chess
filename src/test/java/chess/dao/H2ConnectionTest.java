package chess.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class H2ConnectionTest {
    @Test
    void h2Connect() throws SQLException {
        Connection conn = DriverManager.
                getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        assertThat(conn).isNotNull();
        conn.close();
    }
}
