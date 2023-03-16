package chess.domain.piece;

public final class PositionConverter {
    private static final char FIRST_FILE = 'a';
    private static final char LAST_FILE = 'h';
    private static final char FIRST_RANK = '1';
    private static final char LAST_RANK = '8';
    private static final int POSITION_LENGTH = 2;

    public Position convert(final String position) {
        validate(position);
        final int file = position.charAt(0);
        final int rank = position.charAt(1);
        return new Position(rank - FIRST_RANK, file - FIRST_FILE);
    }

    private void validate(final String position) {
        validateLength(position);
        validateRange(position);
    }

    private void validateLength(final String position) {
        if (position.length() != POSITION_LENGTH) {
            throw new IllegalArgumentException("잘못된 입력입니다.");
        }
    }

    private void validateRange(final String position) {
        final String lowerCasePosition = position.toLowerCase();
        if (lowerCasePosition.charAt(0) < FIRST_FILE || lowerCasePosition.charAt(0) > LAST_FILE) {
            throw new IllegalArgumentException("이동 위치 열은 a~h 사이여야 합니다.");
        }
        if (lowerCasePosition.charAt(1) < FIRST_RANK || lowerCasePosition.charAt(1) > LAST_RANK) {
            throw new IllegalArgumentException("이동 위치 행은 1~8 사이여야 합니다.");
        }
    }
}
