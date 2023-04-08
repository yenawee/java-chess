package chess;

import chess.controller.ChessController;
import chess.dao.chessboard.DBChessBoardDao;
import chess.dao.chessroom.DBChessRoomDao;

public class ChessApplication {
    public static void main(String[] args) {
        final ChessController chessController = new ChessController(new DBChessBoardDao(), new DBChessRoomDao());
        chessController.run();

    }
}
