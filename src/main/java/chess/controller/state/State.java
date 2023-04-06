package chess.controller.state;

import chess.controller.state.command.Command;

public interface State {
    State checkCommand(final Command command);

    boolean isRun();
}
