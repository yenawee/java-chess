package chess.dao.chessgame;

import chess.domain.game.ChessGame;

public class InMemoryChessGameDao implements ChessGameDao {
    private ChessGame chessGame;

    @Override
    public void create(ChessGame chessGame, String id) {

    }

    @Override
    public void updateBoard(ChessGame chessGame, String id) {
    }

    @Override
    public ChessGame findChessGameById(String id) {
        return chessGame;
    }

    @Override
    public void update(ChessGame chessGame, String id) {
        this.chessGame = chessGame;
    }
}
