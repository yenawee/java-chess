package chess.dao.chessroom;

import chess.dao.H2Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InMemoryChessRoomDao implements ChessRoomDao {
    private final H2Connection h2Connection = new H2Connection();

    @Override
    public List<String> readRoomIds() {
        final var query = "SELECT id from chess_room WHERE state != 'end'";
        try (final var connection = h2Connection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeQuery();
            List<String> roomIds = new ArrayList<>();
            while (resultSet.next()) {
                roomIds.add(String.valueOf(resultSet.getInt("id")));
            }
            return roomIds;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public String readTurn(String id) {
        final var query = "SELECT turn FROM chess_room WHERE id = ?";
        try (final var connection = h2Connection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("turn");
            }
            throw new RuntimeException("해당 체스 방에 대한 값을 불러올 수 없습니다");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String create(String turn, String state) {
        final var query = "INSERT INTO chess_room(turn, state) VALUES (?, ?)";
        try (final var connection = h2Connection.getConnection();
             final var preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setString(2, state);
            preparedStatement.executeUpdate();

            final var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return String.valueOf(resultSet.getInt(1));
            }
            throw new RuntimeException("체스 게임방을 생성하는데 실패했습니다");
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateTurn(String id, String turn) {
        final var query = "UPDATE chess_room SET turn = ? WHERE id = ?";
        try (final var connection = h2Connection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, turn);
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(String id, String state) {
        final var query = "UPDATE chess_room SET state = ? WHERE id = ?";
        try (final var connection = h2Connection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, state);
            preparedStatement.setInt(2, Integer.parseInt(id));
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
