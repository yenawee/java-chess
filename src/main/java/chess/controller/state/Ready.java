package chess.controller.state;

import chess.controller.state.command.Command;
import chess.domain.game.ChessGame;

public final class Ready implements State {

    private final ChessGame chessGame;

    public Ready(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State checkCommand(final Command command) {
        if (command.isStart()) {
            return new Move(chessGame);
        }
        if (command.isEnd()) {
            return new End();
        }
        throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
