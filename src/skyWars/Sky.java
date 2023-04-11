package skyWars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sky implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
    private Grid theGrid;

    private ArrayList<Spaceship> listOfSpaceshipsOnTile = new ArrayList<Spaceship>();
    
    public Sky() {
        theGrid = new Grid();
    }
    
    public boolean isThereAShipOn(int x, int y) {
        return theGrid.isThereAShipOn(x, y);
    }
    
    public List<Object> isConflict(int gameMode) {
    	List<Object>  conflict = new ArrayList<Object>(); 
    	conflict.add(0);
        // Loop through each row
        for (Row row : theGrid.getTheRows()) {
        	// Loop through each square
            for (Square square : row.getTheSquares()) {
                // Get the list of spaceships in the current square
                ArrayList<Spaceship> spaceships = square.getSpaceships();

                // Check if there are more than one spaceship in the square
                if (spaceships.size() >= 2) {
                    // Loop through each spaceship in the square
                    for (Spaceship spaceship : spaceships) {
                    	if (gameMode == 1) {
                    		// Offensive Mode
                    		// Check if the spaceship is the one we're looking for
                            if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() > 3) {
                                // There is a conflict to destory mastership
                            	System.out.println("There is a conflict to destory mastership");
                            	conflict.clear();
                            	conflict.add(2);
                            	conflict.add(spaceships);
                            	break;
                            }

                            else if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() == 3) {
                                // There is a conflict to destory enemy ship
                            	System.out.println("There is a conflict to destory enemy ship");
                            	conflict.clear();
                            	conflict.add(1);
                            	conflict.add(spaceships);
                            	break;
                            }
                            else if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() == 2) {
                                // There is a conflict to destory enemy ship
                            	System.out.println("There is a conflict to destory enemy ship");
                            	conflict.clear();
                            	conflict.add(1);
                            	conflict.add(spaceships);
//                            	this.destoryEnemySpaceship(spaceships);
                            	break;
                            }
                    	}
                    	else {

                    		// Defensive Mode
                    		// Check if the spaceship is the one we're looking for
                            if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() > 2) {
                                // There is a conflict to destory mastership
                            	System.out.println("There is a conflict to destory mastership");
                            	conflict.clear();
                            	conflict.add(2);
                            	conflict.add(spaceships);
                            	break;
                            }
                            else if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() == 2) {
                                // There is a conflict to destory enemy ship
                            	System.out.println("There is a conflict to destory enemy ship");
                            	conflict.clear();
                            	conflict.add(1);
                            	conflict.add(spaceships);
                            	break;
                            }  
                    	}
                         
                    }
                }
            }
        }

		return conflict;
    }

    public int[] moveSpaceship(int currentRow, int currentCol, int numRows, int numCols) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
        int[] randomDirection = directions[(int) (Math.random() * directions.length)];
        int newRow = currentRow + randomDirection[0];
        int newCol = currentCol + randomDirection[1];
        while (newRow < 0 || newRow >= numRows || newCol < 0 || newCol >= numCols) {
            randomDirection = directions[(int) (Math.random() * directions.length)];
            newRow = currentRow + randomDirection[0];
            newCol = currentCol + randomDirection[1];
        }
        return new int[] {newRow, newCol};
    }

    public void moveMasterSpaceship() {
        System.out.println("moveMasterSpaceship");
        for (int i = 0; i < listOfSpaceshipsOnTile.size(); i++) {
            Spaceship spaceship = listOfSpaceshipsOnTile.get(i);
            int[] coordinates = getRandomCoordinates(4, 4);
            coordinates = this.moveSpaceship(spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX(), 4, 4);
            theGrid.moveSpaceship(spaceship, coordinates[0], coordinates[1]);
        }
    }
    public ArrayList<Spaceship> getAllSpaceships() {
    	System.out.println("getAllSpaceships");
    	ArrayList<Spaceship> spaceships = new ArrayList<Spaceship>();
    	// Loop through each row
        for (Row row : theGrid.getTheRows()) {
        	// Loop through each square
            for (Square square : row.getTheSquares()) {
                // Get the list of spaceships in the current square
            	spaceships.addAll(square.getSpaceships());
            }
        }
        return spaceships;
    }
    public void destoryAllSpaceships() {
        System.out.println("destoryAllSpaceships");
        for (int i = 0; i < listOfSpaceshipsOnTile.size(); i++) {
//        	Remove Spaceships From List
            cleanSpaceships(listOfSpaceshipsOnTile);
        }
    }

    
    public void cleanSpaceships(ArrayList<Spaceship> spaceships) {
    	for (int i = 0; i < spaceships.size(); i++) {
    	    Spaceship spaceship = spaceships.get(i);
    	    theGrid.removeSpaceship(spaceship, spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX());
    	    spaceships.remove(spaceship);
    	    removeSpaceshipsFromList(spaceship);
    	}
    }
    
    public void destoryEnemySpaceship(ArrayList<Spaceship> spaceships) {
    	System.out.println("Enemy Spaceship(s) have(s) been destoryed.");
    	for (int i = 0; i < spaceships.size(); i++) {
    	    Spaceship spaceship = spaceships.get(i);
    	    if (spaceship.getSpaceshipTypeId() != 0) {
    	        theGrid.removeSpaceship(spaceship, spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX());
    	        spaceships.remove(spaceship);
    	        removeSpaceshipsFromList(spaceship);
    	        i--; // Decrement i to account for removal of current element
    	    }
    	}
    }
    public void destoryMasterSpaceship(ArrayList<Spaceship> spaceships) {
    	System.out.println("Master Spaceship has been destoryed.");
    	for (int i = 0; i < spaceships.size(); i++) {
    	    Spaceship spaceship = spaceships.get(i);
    	    if (spaceship.getSpaceshipTypeId() == 0) {
    	        theGrid.removeSpaceship(spaceship, spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX());
    	        spaceships.remove(spaceship);
    	        removeSpaceshipsFromList(spaceship);
    	        i--; // Decrement i to account for removal of current element
    	    }
    	}
    }
    
    public int[] getRandomCoordinates(int gridWidth, int gridHeight) {
    	int row, col;
    	Random random = new Random();
    	do {
    	    row = random.nextInt(gridWidth);
    	    col = random.nextInt(gridHeight);
    	} while ((row == 0 && col == 0) || (row == 0 && col == 1) || (row == 1 && col == 0) || (row == 1 && col == 1));
        return new int[] { row, col };
    }
    
    // add ships to list of ships on tile
 	public void addSpaceshipsToList(Spaceship spaceship) {
 		this.listOfSpaceshipsOnTile.add(spaceship);
 	}
 	
 	// remove ships from list of ships on tile
 	public void removeSpaceshipsFromList(Spaceship spaceship) {
 		this.listOfSpaceshipsOnTile.remove(spaceship);
 	}
 	
 	public ArrayList<Spaceship> getListOfSpaceships() {
		return this.listOfSpaceshipsOnTile;
	}
 	
}
