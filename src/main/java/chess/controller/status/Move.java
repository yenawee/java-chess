package chess.controller.status;

import chess.controller.Command;
import chess.domain.camp.CampType;
import chess.domain.chess.ChessGame;
import chess.domain.piece.Position;
import chess.domain.piece.PositionConverter;

import java.util.List;

public class Move implements Status {
    private final ChessGame chessGame;
    private final CampType campType;

    public Move(final ChessGame chessGame, final CampType campType) {
        this.chessGame = chessGame;
        this.campType = campType;
    }

    private Status move(final Command command) {
        validateCommand(command);
        final List<String> commands = command.getCommands();
        final Position source = PositionConverter.convert(commands.get(1));
        final Position target = PositionConverter.convert(commands.get(2));
        chessGame.play(source, target, campType);
        return new Move(chessGame, campType.changeTurn());
    }

    private void validateCommand(final Command command) {
        if (!command.isCorrectWhenMove()) {
            throw new IllegalArgumentException("'move source위치 target위치 - 예. move b2 b3'와 같은 형태로 입력해 주세요.");
        }
    }

    @Override
    public Status checkCommand(final Command command) {
        if (command.isStart()) {
            throw new IllegalArgumentException("이미 시작이 완료되었습니다.");
        }
        if (command.isEnd()) {
            return new End();
        }
        return move(command);
    }

    @Override
    public boolean isRun() {
        return true;
    }
}