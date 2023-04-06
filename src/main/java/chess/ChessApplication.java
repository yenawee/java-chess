package chess;

import chess.controller.ChessController;
import chess.dao.chessgame.DBChessGameDao;
import chess.dao.chessgame.InMemoryChessGameDao;
import chess.dao.chessroom.InMemoryChessRoomDao;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new InMemoryChessGameDao(), new InMemoryChessRoomDao());
        chessController.run();
    }
}
