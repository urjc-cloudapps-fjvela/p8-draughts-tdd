package es.urjccode.mastercloudapps.adcs.draughts.models;

public abstract class Piece implements Cloneable {

	protected Color color;

	Piece(Color color) {
		assert color != null;
		this.color = color;
	}

	abstract boolean isAdvanced(Coordinate origin, Coordinate target);

	abstract Error isCorrectCustom(Coordinate origin, Coordinate target, PieceProvider pieceProvider);

	Error isCorrect(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
		if (!origin.isDiagonal(target)) {
			return Error.NOT_DIAGONAL;
		}
		if (!pieceProvider.isEmpty(target)) {
			return Error.NOT_EMPTY_TARGET;
		}
		if (!this.isAdvanced(origin, target)) {
			return Error.NOT_ADVANCED;
		}

		return isCorrectCustom(origin, target, pieceProvider);

	}

	boolean isLimit(Coordinate coordinate) {
		return coordinate.getRow() == 0 && this.getColor() == Color.WHITE
				|| coordinate.getRow() == 7 && this.getColor() == Color.BLACK;
	}

	Color getColor() {
		return this.color;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return (Piece) super.clone();

	}

}