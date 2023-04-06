package chess.dao.chessroom;

import java.util.Collections;
import java.util.List;

public class InMemoryChessRoomDao implements ChessRoomDao {
    @Override
    public List<String> readRoomIds() {
        return Collections.emptyList();
    }

    @Override
    public String readTurn(String id) {
        return id;
    }

    @Override
    public void create() {

    }

    @Override
    public void update(String id, String turn) {

    }
}
