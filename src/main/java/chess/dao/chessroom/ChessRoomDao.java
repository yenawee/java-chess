package chess.dao.chessroom;

import java.util.List;

public interface ChessRoomDao {
    List<String> readRoomIds();

    String readTurn(String id);

    String create(String turn, String state);

    void updateTurn(String id, String turn);
    void updateState(String id, String state);
}
