package es.urjccode.mastercloudapps.adcs.draughts.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;

import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GameWithDraughtsTest {

    @Mock
    private Turn turn;
    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGivenGameWhenWhitePawnAtLimitThenNewDraugts()  {

        Coordinate origin = new Coordinate(1, 0);
        Coordinate target = new Coordinate(0, 1);

        Game game = new GameBuilder().addRow(1, "b       ").build();

        game.move(origin, target);

        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(draught instanceof Draught);
    }

    @Test
    public void testGivenGameWhenPawnAtLimitAndEatingThenNewDraugts()  {
        Coordinate origin = new Coordinate(2, 1);
        Coordinate target = new Coordinate(0, 3);

        Game game = new GameBuilder()
            .addRow(2, " b      ")
            .addRow(1, "  n     ").build();

        game.move(origin, target);

        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(draught instanceof Draught);
    }

    @Test
    public void testGivenGameWhenBlackPawnAtLimitThenNewDraugts()  {

        Coordinate origin = new Coordinate(6,3);
        Coordinate target = new Coordinate(7,2);
        when(turn.getColor()).thenReturn(Color.BLACK);

        Game game = new GameBuilder(turn) .addRow(6, "   n    ").build();

        game.move(origin, target);

        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(draught instanceof Draught);
    }

    @Test 
    public void testGivenGameWhenWhiteDraughtsThenMove() {
        Coordinate origin = new Coordinate(0, 1);

        Coordinate target = new Coordinate(1, 0);

        Game game = new GameBuilder().addRow(0, " B      ").build();

        game.move(origin, target);

        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(draught instanceof Draught);  
    }

    @Test 
    public void testGivenGameWhenBlackDraughtsThenMove() {
        Coordinate origin = new Coordinate(7,2);
        Coordinate target = new Coordinate(6,3);

        when(turn.getColor()).thenReturn(Color.BLACK);

        Game game = new GameBuilder(turn).addRow(7, "  N     ").build();

        game.move(origin, target);

        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertTrue(draught instanceof Draught);  
    }

    @Test 
    public void testGivenGameWhenBlackDraughtsMoveOverPieceThenCapturePiece() {
        Coordinate origin = new Coordinate(7,2);
        Coordinate target = new Coordinate(5,4);
        Coordinate pieceCaptured = new Coordinate(6, 3);

        when(turn.getColor()).thenReturn(Color.BLACK);

        Game game = new GameBuilder(turn).addRow(7, "  N     ")
            .addRow(6,"   b    " ).build();

        game.move(origin, target);
        
        Piece draught = game.getPiece(target);
        assertNull(game.getPiece(origin));
        assertNull(game.getPiece(pieceCaptured));
        assertTrue(draught instanceof Draught);  
    }

    @Test 
    public void testGivenGameWhenWhiteDraughtsMoveOverPieceSameColorThenError() {
        Coordinate origin = new Coordinate(0, 1);
        Coordinate target = new Coordinate(2, 3);

        Game game = new GameBuilder()
            .addRow(0, " B      ")
            .addRow(1, "  b     ").build();

       assertEquals(Error.EATING_SAME_COLOR, game.isCorrect(origin, target));

        // game.move(origin, target);

        // Piece draught = game.getPiece(target);
        // assertNull(game.getPiece(origin));
        // assertTrue(draught instanceof Draught);  
    }
}