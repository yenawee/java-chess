package chess.controller.state;

import chess.controller.state.command.Command;
import chess.domain.game.ChessGame;
import chess.domain.position.Position;
import chess.domain.position.PositionConverter;

import java.util.List;

public final class Move implements State {
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;

    private final ChessGame chessGame;

    public Move(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new End().run(chessGame);
        }
        if (command.isStatus()) {
            return new Status(chessGame).run();
        }
        validateCommand(command);
        return move(command);
    }

    private State move(final Command command) {
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(SOURCE_INDEX));
        final Position target = PositionConverter.convert(commands.get(TARGET_INDEX));
        chessGame.play(source, target);
        if (chessGame.isEnd()) {
            return new End().run(chessGame);
        }
        return new Move(chessGame);
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }

    @Override
    public boolean isRun() {
        return true;
    }
}
