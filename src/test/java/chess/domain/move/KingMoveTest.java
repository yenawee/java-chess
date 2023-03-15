package chess.domain.move;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KingMoveTest {

    @ParameterizedTest(name = "source에서 target으로 킹이 이동할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"0:1", "1:0", "1:2", "2:1", "0:2", "0:0", "2:0", "2:2"}, delimiter = ':')
    void canMoveSuccess(final int targetRank, final int targetFile) {
        // given
        final KingMove kingMove = new KingMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = kingMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 킹이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"1:3", "0:8", "-8:0", "8:0", "0:-8", "3:1", "-1:1", "1:-2", "8:8"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int targetRank, final int targetFile) {
        // given
        final KingMove kingMove = new KingMove();
        final int sourceRank = 1, sourceFile = 1;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = kingMove.canMove(source, new Position(targetRank, targetFile));

        // then
        assertThat(actual)
                .isFalse();
    }
}
