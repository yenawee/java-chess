package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.controller.state.command.Command;
import chess.dao.chessgame.ChessGameDao;
import chess.dao.chessroom.ChessRoomDao;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;
import java.util.Map;

public class ChessController {
    private final ChessGameDao chessGameDao;
    private final ChessRoomDao chessRoomDao;


    public ChessController(final ChessGameDao chessGameDao, final ChessRoomDao chessRoomDao) {
        this.chessGameDao = chessGameDao;
        this.chessRoomDao = chessRoomDao;
    }

    public void run() {
        ChessGame chessGame;
//        OutputView.printStartMessage();
        List<String> roomIds = chessRoomDao.readRoomIds();
        String roomId = InputView.getChessRoomId(roomIds);
        if (roomId.equals("new")) {
            chessGame = new ChessGame();
            String newGameRoomId = chessRoomDao.create(chessGame.getCurrentTeamColor().name(), "start");
            chessGameDao.create(chessGame, newGameRoomId);
            roomId = newGameRoomId;
        }
        chessGame = chessGameDao.findChessGameById(roomId);
        State gameStatus = new Ready(chessGame);
        play(chessGame, gameStatus, roomId);
    }

    private void play(ChessGame chessGame, State gameStatus, String roomId) {
        while (!chessGame.isEnd() && gameStatus.isRun()) {
            gameStatus = getStatus(gameStatus);
            chessGameDao.update(chessGame, roomId);
            chessRoomDao.updateTurn(roomId, chessGame.getCurrentTeamColor().name());
            printChessBoard(gameStatus, chessGame.getChessBoard());
        }
        if (chessGame.isEnd()) {
            chessGameDao.update(chessGame, roomId);
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
