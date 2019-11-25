package es.urjccode.mastercloudapps.adcs.draughts.models;

class Pawns extends Piece {
    private static final int MAX_DISTANCE = 2;

    Pawns(Color color) {
        super(color);
    }

    @Override
    boolean isAdvanced(Coordinate origin, Coordinate target) {
        assert origin != null;
        assert target != null;
        int difference = origin.getRow() - target.getRow();
        if (color == Color.WHITE) {
            return difference > 0;
        }
        return difference < 0;
    }

    @Override
    Error isCorrectCustom(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
        int distance = origin.diagonalDistance(target);
        if (distance > MAX_DISTANCE) {
            return Error.BAD_DISTANCE;
        }
        if (distance == MAX_DISTANCE) {
            if (pieceProvider.getPiece(origin.betweenDiagonal(target)) == null) {
                return Error.EATING_EMPTY;
            }
        }
        return null;
    }
}