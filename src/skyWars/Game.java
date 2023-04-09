package skyWars;

import java.util.Random;

public class Game {
//    private Sky sky;
    private Spaceship[] spaceship;
//    private EnemySpaceship[] enemySpaceships;
    private boolean gameOver;
    
    public Game() {
        sky = new Sky();
        masterSpaceship = new MasterSpaceship();
        enemySpaceships = new EnemySpaceship[3];
        gameOver = false;
    }
    
    public void startGame() {
        sky.init();
        sky.placeMasterSpaceship(masterSpaceship);
    }
    
    public void moveMasterSpaceship(int x, int y) {
        sky.clearSquare(masterSpaceship.getX(), masterSpaceship.getY());
        masterSpaceship.move(x, y);
        sky.placeSpaceship(masterSpaceship);
        spawnEnemySpaceship();
        updateGameState();
    }
    
    private void spawnEnemySpaceship() {
        Random rand = new Random();
        if (rand.nextInt(3) == 0) {
            int type = rand.nextInt(3);
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            EnemySpaceship enemySpaceship;
            switch (type) {
                case 0:
                    enemySpaceship = new BattleStar();
                    break;
                case 1:
                    enemySpaceship = new BattleCruiser();
                    break;
                case 2:
                    enemySpaceship = new BattleShooter();
                    break;
                default:
                    enemySpaceship = new BattleStar();
                    break;
            }
            enemySpaceship.setX(x);
            enemySpaceship.setY(y);
            sky.placeSpaceship(enemySpaceship);
            for (int i = 0; i < enemySpaceships.length; i++) {
                if (enemySpaceships[i] == null) {
                    enemySpaceships[i] = enemySpaceship;
                    break;
                }
            }
        }
    }
    
    private void updateGameState() {
        for (int i = 0; i < enemySpaceships.length; i++) {
            EnemySpaceship enemySpaceship = enemySpaceships[i];
            if (enemySpaceship != null) {
                if (masterSpaceship.collidesWith(enemySpaceship)) {
                    gameOver = true;
                } else {
                    for (int j = i + 1; j < enemySpaceships.length; j++) {
                        EnemySpaceship otherEnemySpaceship = enemySpaceships[j];
                        if (otherEnemySpaceship != null && enemySpaceship.collidesWith(otherEnemySpaceship)) {
                            sky.clearSquare(enemySpaceship.getX(), enemySpaceship.getY());
                            enemySpaceships[i] = null;
                            sky.clearSquare(otherEnemySpaceship.getX(), otherEnemySpaceship.getY());
                            enemySpaceships[j] = null;
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public boolean isGameOver() {
        return gameOver;
    }
    
    public String getGameState() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                if (masterSpaceship.getX() == x && masterSpaceship.getY() == y) {
                    sb.append("M");
                } else {
                    boolean occupied = false;
                    for (int i = 0; i < enemySpaceships.length; i++) {
                        EnemySpaceship enemySpaceship = enemySpaceships[i];
                        if (enemySpaceship != null && enemySpaceship.getX() == x && enemySpaceship.getY() == y) {
                        	sb.append("E");
                        	occupied = true;
                        	break;
                        	}
                        	}
                        	if (!occupied) {
                        	sb.append("-");
                        	}
                        	}
                        	}
                        	sb.append("\n");
                        	}
                        	return sb.toString();
                        	}

                        	public MasterSpaceship getMasterSpaceship() {
                        	    return masterSpaceship;
                        	}

                        	public EnemySpaceship[] getEnemySpaceships() {
                        	    return enemySpaceships;
                        	}
                        	}