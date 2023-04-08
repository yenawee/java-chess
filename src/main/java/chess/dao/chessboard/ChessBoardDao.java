package chess.dao.chessboard;

import chess.domain.board.ChessBoard;

public interface ChessBoardDao {

    void create(ChessBoard chessBoard, String id);

    void updateBoard(ChessBoard chessBoard, String id);

    ChessBoard findChessBoardById(String id);

    void update(ChessBoard chessBoard, String id);
}
