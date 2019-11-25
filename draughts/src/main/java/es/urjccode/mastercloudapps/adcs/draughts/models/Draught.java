package es.urjccode.mastercloudapps.adcs.draughts.models;

class Draught extends Piece {

    Draught(Color color) {
        super(color);
    }

    @Override
    boolean isAdvanced(Coordinate origin, Coordinate target) {
        return true;
    }

    @Override
    Error isCorrectCustom(Coordinate origin, Coordinate target, PieceProvider pieceProvider) {
 
		return null;
    }

}