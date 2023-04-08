package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.controller.state.command.Command;
import chess.dao.chessboard.ChessBoardDao;
import chess.dao.chessroom.ChessRoomDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamColor;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {
    private final ChessBoardDao chessBoardDao;
    private final ChessRoomDao chessRoomDao;


    public ChessController(final ChessBoardDao chessBoardDao, final ChessRoomDao chessRoomDao) {
        this.chessBoardDao = chessBoardDao;
        this.chessRoomDao = chessRoomDao;
    }

    public void run() {
        ChessGame chessGame;
        List<String> roomIds = chessRoomDao.readRoomIds();
        String roomId = InputView.getChessRoomId(roomIds);
        if (roomId.equals("new")) {
            chessGame = new ChessGame();
            String newGameRoomId = chessRoomDao.create(chessGame.getCurrentTeamColor().name(), "start");
            chessBoardDao.create(chessGame.getChessBoard(), newGameRoomId);
            roomId = newGameRoomId;
        } else {
            chessGame = new ChessGame(chessBoardDao.findChessBoardById(roomId),
                    TeamColor.valueOf(chessRoomDao.readTurn(roomId)));
        }
        OutputView.printStartMessage();
        State gameStatus = new Ready(chessGame);
        play(chessGame, gameStatus, roomId);
    }

    private void play(ChessGame chessGame, State gameStatus, String roomId) {
        while (!chessGame.isEnd() && gameStatus.isRun()) {
            gameStatus = getStatus(gameStatus);
            chessBoardDao.update(chessGame.getChessBoard(), roomId);
            chessRoomDao.updateTurn(roomId, chessGame.getCurrentTeamColor().name());
            printChessBoard(gameStatus, chessGame.getBoard());
        }
        if (chessGame.isEnd()) {
            chessBoardDao.update(chessGame.getChessBoard(), roomId);
            chessRoomDao.updateState(roomId, "end");
        }
    }

    private State getStatus(State gameStatus) {
        try {
            List<String> commands = InputView.getCommand();
            final Command command = Command.findCommand(commands);
            return gameStatus.checkCommand(command);
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.print(e.getMessage());
            return getStatus(gameStatus);
        }
    }

    private void printChessBoard(State gameStatus, Map<Position, Piece> board) {
        if (gameStatus.isRun()) {
            ChessBoardDto chessBoardDTO = new ChessBoardDto(board);
            OutputView.print(chessBoardDTO.getBoardMessage().toString());
        }
    }
}
