package skyWars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkyWarsGameBoard implements Serializable, Observable {

	private static final long serialVersionUID = 1L;
	
    private static Sky theSky = new Sky();;
    private static Scores theScore = new Scores();
    
	private static final int SCORE_SHOOTER = 25;
    private static final int SCORE_STAR = 50;
	private static final int SCORE_CRUISER = 75;
	
	private static int GAME_MODE;
	
	private ArrayList<Scores> scoresObservers = new ArrayList<Scores>();

	public SkyWarsGameBoard() {
    }
	
    public void play() {
    	theSky.destoryAllSpaceships();
    	startPositionMasterSpaceship();
    	checkForEnemyShip();
    }
    
    public void reset() {
    	theSky.destoryAllSpaceships();
    }
    
	public Sky getSky() {
		return theSky;
	}
	
	public void startPositionMasterSpaceship() {
		theScore.setScore(0);
		ArrayList<Spaceship> spaceships = theSky.getListOfSpaceships();
	    theSky.cleanSpaceships(spaceships);

	    MasterSpaceship masterSpaceship = new MasterSpaceship();
	    int[] coordinates = theSky.getRandomCoordinates(4, 4);
    	masterSpaceship.setSpaceshipPositionX(coordinates[1]);
    	masterSpaceship.setSpaceshipPositionY(coordinates[0]);

    	theSky.addSpaceshipsToList(masterSpaceship);
    	
	}

	@SuppressWarnings("unchecked")
	public List<Object> moveMasterSpaceship() {
		theSky.moveMasterSpaceship();
		checkForEnemyShip();
		List<Object> type = theSky.isConflict(GAME_MODE);
		System.out.println((int) type.get(0));
		if ((int) type.get(0) == 1) {
			System.out.println(type.get(0));
			ArrayList<Spaceship> spaceships = (ArrayList<Spaceship>) type.get(1);
			for (Spaceship spaceship: (ArrayList<Spaceship>) type.get(1)) {
				System.out.println(spaceship);
				if (spaceship.getSpaceshipTypeId() > 0 )
				{
					switch (spaceship.getSpaceshipTypeId()) {
			        case 1:
			        	theScore.increaseScore(SCORE_SHOOTER);
			            break;
			        case 2:
			        	theScore.increaseScore(SCORE_STAR);
			            break;
			        case 3:
			        	theScore.increaseScore(SCORE_CRUISER);
			            break;
			        }
				}
				
			}
			theScore.increaseHighScore();
			theSky.destoryEnemySpaceship(spaceships);
			System.out.println("Hit Enemy!!!");
		}
		else if ((int) type.get(0) == 2) {
			theScore.setHighScore(theScore.getScore());;
			theScore.update(0);;

			ArrayList<Spaceship> spaceships = (ArrayList<Spaceship>) type.get(1);
			theSky.destoryMasterSpaceship(spaceships);
			theSky.destoryAllSpaceships();
			System.out.println("Game Over!!!");
		}
		else {
			System.out.println("No Score!!!");
			
		}
		return type;
	}
	
	public void setGameMode(int gameMode) { 
		GAME_MODE = gameMode;
	}
	
	public int getGameMode() { 
		return GAME_MODE;
	}
	
	public static void checkForEnemyShip() {
	    // Generate a random number from 1 to 3 (inclusive)
	    Random rand = new Random();
	    int randNum = rand.nextInt(3) + 1;
	    // If the number is 1, an enemy ship enters the sky
	    if (randNum == 1) {
	    	System.out.println("Generate a random enemy ship");
	        // Generate a random enemy ship type
	    	int enemyRandNum = rand.nextInt(3) + 1;
	    	switch (enemyRandNum) {
	        case 1:
	        	BattleShooter battleShooter = new BattleShooter();
	        	theSky.addSpaceshipsToList(battleShooter);
	            break;
	        case 2:
	        	BattleStar battleStar = new BattleStar();
	        	theSky.addSpaceshipsToList(battleStar);
	            break;
	        case 3:
	        	BattleCruiser battleCruiser = new BattleCruiser();
	        	theSky.addSpaceshipsToList(battleCruiser);
	            break;
	        }

	    }
	}
	
	public Scores getScore() {
		return SkyWarsGameBoard.theScore;
	}
	
	@Override
	public void registerObserver(Scores scores) {
		scoresObservers.add(scores);		
	}

	@Override
	public void removeObserver(Scores scores) {
		scoresObservers.remove(scores);		
	}

	@Override
	public void notifyObservers() {
		for (Scores s : scoresObservers) {
			s.update(theScore.getScore());
		}
	}
}
