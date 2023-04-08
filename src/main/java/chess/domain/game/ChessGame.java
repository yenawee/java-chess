package chess.domain.game;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;

import java.util.Map;

public final class ChessGame {
    private final ChessBoard chessBoard;
    private TeamColor currentTeamColor;

    public ChessGame() {
        this.chessBoard = ChessBoard.getInstance(this);
        this.currentTeamColor = TeamColor.WHITE;
    }

    public ChessGame(final ChessBoard chessBoard, final TeamColor turn) {
        this.chessBoard = chessBoard;
        this.currentTeamColor = turn;
    }

    public void play(final Position source, final Position target) {
        final Piece piece = chessBoard.getPiece(source);
        if (piece == null) {
            throw new IllegalArgumentException("기물이 존재하는 위치를 입력해주세요.");
        }
        validateTurn(piece);
        if (!piece.canMove(source, target, chessBoard.getPiece(target))
                || !chessBoard.isPossibleRoute(source, target, currentTeamColor)) {
            throw new IllegalArgumentException("기물 규칙 상 움직일 수 없는 위치입니다.");
        }
        movePiece(source, target, piece);
        changeTurn();
    }

    private void validateTurn(final Piece piece) {
        if (!piece.isSameTeam(currentTeamColor)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    private void movePiece(final Position source, final Position target, final Piece piece) {
        chessBoard.removePiece(source);
        chessBoard.putPiece(target, piece);
    }

    private void changeTurn() {
        this.currentTeamColor = currentTeamColor.changeTurn();
    }

    public Map<Position, Piece> getBoard() {
        return chessBoard.getBoard();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isEnd() {
        return chessBoard.checkKingDie();
    }

    public TeamColor getCurrentTeamColor() {
        return currentTeamColor;
    }
}
