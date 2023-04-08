package chess.dao.chessgame;

import chess.domain.game.ChessGame;

public interface ChessGameDao {

    void create(ChessGame chessGame, String id);

    void updateBoard(ChessGame chessGame, String id);

    ChessGame findChessGameById(String id);

    void update(ChessGame chessGame, String id);
}
