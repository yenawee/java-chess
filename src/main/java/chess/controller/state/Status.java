package chess.controller.state;

import chess.controller.state.command.Command;
import chess.controller.ScoreDto;
import chess.domain.game.ChessGame;
import chess.domain.piece.TeamColor;
import chess.domain.result.Score;
import chess.view.OutputView;

public class Status implements State {
    private final ChessGame chessGame;

    public Status(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    State run() {
        Score score = Score.calculate(chessGame.getChessBoard());
        OutputView.printStatus(new ScoreDto(score));
        return new Status(chessGame);
    }

    @Override
    public State checkCommand(Command command) {
        if (command.isEnd()) {
            return new End().run(chessGame);
        }
        if (command.isMove()) {
            Move move = new Move(chessGame);
            return move.checkCommand(command);
        }
        if (command.isStatus()) {
            return run();
        }
        throw new UnsupportedOperationException("end, move 명령어만 입력 가능합니다.");
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
