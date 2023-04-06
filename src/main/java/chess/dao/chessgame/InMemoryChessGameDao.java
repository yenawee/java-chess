package chess.dao.chessgame;

import chess.dao.chessgame.ChessGameDao;
import chess.domain.game.ChessGame;

public class InMemoryChessGameDao implements ChessGameDao {
    private ChessGame chessGame;

    @Override
    public void save(ChessGame chessGame) {
        this.chessGame = chessGame;

    }

    @Override
    public ChessGame findChessGame() {
        return chessGame;
    }

    @Override
    public void update(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void init(ChessGame chessGame) {
        this.chessGame = null;
    }
}