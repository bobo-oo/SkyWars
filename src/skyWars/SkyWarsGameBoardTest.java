package skyWars;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SkyWarsGameBoardTest {

    @Test
    void testStartPositionMasterSpaceship() {
        SkyWarsGameBoard gameBoard = new SkyWarsGameBoard();
        gameBoard.startPositionMasterSpaceship();
        List<Spaceship> spaceships = gameBoard.getSky().getListOfSpaceships();
        Assertions.assertEquals(1, spaceships.size()); // should have only one master spaceship
        Assertions.assertTrue(spaceships.get(0) instanceof MasterSpaceship); // should be a MasterSpaceship instance
    }

    @Test
    void testMoveMasterSpaceship() {
        SkyWarsGameBoard gameBoard = new SkyWarsGameBoard();
        gameBoard.startPositionMasterSpaceship();
        gameBoard.setGameMode(1);
        List<Object> result = gameBoard.moveMasterSpaceship();
        Assertions.assertNotNull(result); // should not be null
    }

    @Test
    void testCheckForEnemyShip() {
        SkyWarsGameBoard gameBoard = new SkyWarsGameBoard();
        SkyWarsGameBoard.checkForEnemyShip();
        List<Spaceship> spaceships = gameBoard.getSky().getListOfSpaceships();
        Assertions.assertTrue(spaceships.size() <= 1); // should have at most one enemy spaceship
    }

    @Test
    void testReset() {
        SkyWarsGameBoard gameBoard = new SkyWarsGameBoard();
        gameBoard.startPositionMasterSpaceship();
        gameBoard.reset();
        List<Spaceship> spaceships = gameBoard.getSky().getListOfSpaceships();
        Assertions.assertEquals(0, spaceships.size()); // should have no spaceships after resetting the board
    }

    @Test
    void testGetScore() {
        SkyWarsGameBoard gameBoard = new SkyWarsGameBoard();
        Scores score = gameBoard.getScore();
        Assertions.assertNotNull(score); // should not be null
    }

}

