package chess.domain.move;

import chess.domain.piece.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookMoveTest {

    @ParameterizedTest(name = "source에서 target으로 룩이 이동할 수 있으면 true를 반환한다.")
    @CsvSource(value = {"0:1", "0:7", "1:0", "7:0"}, delimiter = ':')
    void canMoveSuccess(final int rank, final int file) {
        // given
        final RookMove rookMove = new RookMove();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = rookMove.canMove(source, new Position(sourceRank + rank, sourceFile + file));

        // then
        assertThat(actual)
                .isTrue();
    }

    @ParameterizedTest(name = "source에서 target으로 이동하는 방향이 룩이 갈 수 없는 위치면 false를 반환한다.")
    @CsvSource(value = {"1:2", "0:8", "-8:0", "8:0", "0:-8", "1:1", "-1:-1", "8:8"}, delimiter = ':')
    void canMoveFailWhenWrongTarget(final int rank, final int file) {
        // given
        final RookMove rookMove = new RookMove();
        final int sourceRank = 0, sourceFile = 0;
        final Position source = new Position(sourceRank, sourceFile);

        // when
        boolean actual = rookMove.canMove(source, new Position(sourceRank + rank, sourceFile + file));

        // then
        assertThat(actual)
                .isFalse();
    }
}