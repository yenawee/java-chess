package chess.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class InputView {
    private static final String DELIMITER = " ";
    private static final String INPUT_ERROR_MESSAGE = "빈 값을 입력할 수 없습니다.";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> getCommand() {
        final String command = readCommand();
        return Arrays.asList(command.split(DELIMITER));
    }

    private static String readCommand() {
        final String command = scanner.nextLine();
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException(INPUT_ERROR_MESSAGE);
        }
        return command;
    }

    public static String getChessRoomId(List<String> roomIds) {
        final String input = scanner.nextLine();
        if (roomIds.contains(input) || input.equals("new")) {
            return input;
        }
        return getChessRoomId(roomIds);
    }
}
