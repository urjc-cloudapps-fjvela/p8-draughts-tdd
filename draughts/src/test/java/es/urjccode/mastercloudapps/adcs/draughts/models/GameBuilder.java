package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

class GameBuilder {

    private ArrayList<String> rows;
    private Map<Integer, Piece> boardPieces;
    private Turn turn;

    GameBuilder() {
        turn = null;
        initEmptyBoard();
        initBoardPieces();

    }

    GameBuilder(Turn turn) {
        this();
        this.turn = turn;

    }

    GameBuilder addRow(int row, String rowData) {
        assert row < rows.size() && rowData.length() == rows.size();
        rows.set(row, rowData);
        return this;
    }

    Game build() {

        Board board = new Board();
        String row;
        Integer key;
        for (int i = 0; i < rows.size(); i++) {
            row = rows.get(i);
            for (int j = 0; j < row.length(); j++) {
                putPiece(board, row, i, j);
            }
        }

        return turn == null ? new Game(board) : new Game(board, turn);
    }

    private void putPiece(Board board, String row, int i, int j) {
        try {
            Integer key;
            key = (int) row.charAt(j);
            if (boardPieces.containsKey(key)) {
                board.put(new Coordinate(i, j), ((Piece) boardPieces.get(key).clone()));
            }
        } catch (CloneNotSupportedException ex) {
        }
    }

    private void initBoardPieces() {
        boardPieces = new HashMap<Integer, Piece>();
        boardPieces.put((int) 'b', new Pawns(Color.WHITE));
        boardPieces.put((int) 'n', new Pawns(Color.BLACK));
        boardPieces.put((int) 'B', new Draught(Color.WHITE));
        boardPieces.put((int) 'N', new Draught(Color.BLACK));
    }

    private void initEmptyBoard() {
        rows = new ArrayList<String>();

        for (int i = 0; i < Board.DIMENSION; i++) {
            rows.add(StringUtils.repeat(" ", Board.DIMENSION));
        }
    }

}