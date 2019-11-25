package es.urjccode.mastercloudapps.adcs.draughts.models;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

class GameBuilder {

    private ArrayList<String> rows;
    private Turn turn;

    GameBuilder() {
        turn = null;
        rows = new ArrayList<String>();
        for (int i = 0; i < Board.DIMENSION; i++)
            rows.add(StringUtils.repeat(" ", Board.DIMENSION));
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
        char position;

        for (int i = 0; i < rows.size(); i++) {
            row = rows.get(i);
            for (int j = 0; j < row.length(); j++) {
                position = row.charAt(j);
                if (position == 'b')
                    board.put(new Coordinate(i, j), new Piece(Color.WHITE));
                if (position == 'n')
                    board.put(new Coordinate(i, j), new Piece(Color.BLACK));
            }
        }

        return turn == null ? new Game(board) : new Game(board, turn);
    }
}