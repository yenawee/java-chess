package chess.dao.chessroom;

import java.util.List;

public class InMemoryChessRoomDao implements ChessRoomDao {
    @Override
    public List<String> readRoomIds() {
        return null;
    }

    @Override
    public String readTurn(String id) {
        return "turn";
    }

    @Override
    public String create(String turn, String state) {
        return "null";
    }

    @Override
    public void updateTurn(String id, String turn) {

    }

    @Override
    public void updateState(String id, String state) {

    }
}
