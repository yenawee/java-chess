package chess.dao;

import java.util.List;

public interface ChessRoomDao {
    List<String> readRoomIds();
    String readTurn(String id);
    void create();
    void update(String id, String turn);
}
