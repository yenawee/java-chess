package chess.dao.chessgame;

import chess.dao.DBConnection;
import chess.domain.board.ChessBoard;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DBChessGameDao implements ChessGameDao {
    private static final DBConnection dbConnection = new DBConnection();

    @Override
    public void create(ChessGame chessGame, String id) {
        final var query = "INSERT INTO chess_game(game_id, piece_type, piece_rank, piece_file, team) VALUES (?, ?, ?, ?, ?)";
        Map<Position, Piece> board = chessGame.getChessBoard();
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<Position, Piece> boardEntry : board.entrySet()) {
                preparedStatement.setInt(1, Integer.parseInt(id));
                preparedStatement.setString(2, boardEntry.getValue().getPieceType().name());
                preparedStatement.setInt(3, boardEntry.getKey().getRank());
                preparedStatement.setInt(4, boardEntry.getKey().getFile());
                preparedStatement.setString(5, boardEntry.getValue().getTeamColor().name());
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void updateBoard(ChessGame chessGame, String id) {
        final var query = "UPDATE chess_game SET piece_type = ?, piece_rank = ?, piece_file = ?, team = ? WHERE game_id = ?";
        Map<Position, Piece> board = chessGame.getChessBoard();
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            for (Map.Entry<Position, Piece> boardEntry : board.entrySet()) {
                preparedStatement.setString(1, boardEntry.getValue().getPieceType().name());
                preparedStatement.setInt(2, boardEntry.getKey().getRank());
                preparedStatement.setInt(3, boardEntry.getKey().getFile());
                preparedStatement.setString(4, boardEntry.getValue().getTeamColor().name());
                preparedStatement.setInt(5, Integer.parseInt(id));
                preparedStatement.executeUpdate();
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ChessGame findChessGameById(String id) {
        Map<Position, Piece> board = new HashMap<>();
        TeamColor turn = null;
        final var query = "SELECT piece_type, piece_rank, piece_file, team FROM chess_game WHERE game_id = ?";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            final var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                PieceType pieceType = PieceType.valueOf(resultSet.getString("piece_type"));
                int rank = resultSet.getInt("piece_rank");
                int file = resultSet.getInt("piece_file");
                TeamColor teamColor = TeamColor.valueOf(resultSet.getString("team"));
                Piece piece = PieceType.toPiece(pieceType, teamColor);
                board.put(Position.of(rank, file), piece);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
        if (board.isEmpty()) {
            return null;
        }
        return new ChessGame(new ChessBoard(board), turn.changeTurn());
    }

    @Override
    public void update(ChessGame chessGame, String id) {
        delete(id);
        updateBoard(chessGame, id);
    }

    public void delete(String id) {
        final var query = "DELETE from chess_game WHERE game_id = ?";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, Integer.parseInt(id));
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void init(ChessGame chessGame) {
        final var query = "TRUNCATE TABLE chess_game";
        try (final var connection = dbConnection.getConnection();
             final var preparedStatement = connection.prepareStatement(query)) {
            final var resultSet = preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
