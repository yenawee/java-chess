package chess.view;

import chess.controller.ScoreDto;

import java.util.List;

import static chess.controller.state.command.CommandType.*;

public final class OutputView {
    private static final String SELECT_CHESSROOM_MESSAGE = "입장하실 체스 게임방을 입력해주세요.\n " +
            "> 가능한 숫자 : %s\n" +
            "> new 입력 시 새로운 체스게임방이 만들어집니다.";

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다.\n" +
            "> 게임 시작 : %s\n" +
            "> 게임 종료 : %s\n" +
            "> 게임 이동 : %s source위치 target위치 - 예. move b2 b3";

    public static void print(final String message) {
        System.out.println(message);
    }

    public static void printSelectChessRoomMessage(List<String> roomIds) {
        print("입장하실 게임방을 선택해주세요\n");
        String avaliableRoomIds = String.join(",", roomIds.toString());
        print(avaliableRoomIds + " 방에 입장 가능합니다. 새로 입장하시려면 new 를 입력해주세요\n");
    }

    public static void printStartMessage() {
        print(String.format(START_MESSAGE, START.name().toLowerCase(),
                END.name().toLowerCase(), MOVE.name().toLowerCase()));
    }

    public static void printStatus(ScoreDto scoreDto) {
        print(String.format("Black : %f, White : %f",
                scoreDto.getBlackScore(), scoreDto.getWhiteScore()));
        System.out.println(System.lineSeparator());
    }
}
