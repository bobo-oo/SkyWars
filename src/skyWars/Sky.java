package skyWars;

import java.util.ArrayList;
import java.util.Random;

public class Sky {
    private Grid theGrid;

//    public Sky() {
//        grid = new Spaceship[size][size];
//    }

    
    public Sky() {
        theGrid = new Grid();
    }
    
    public boolean isThereAShipOn(int x, int y) {
        return theGrid.isThereAShipOn(x, y);
    }
    
    public void isConflict() {
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
                        // Check if the spaceship is the one we're looking for
                        if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() > 2) {
                            // There is a conflict to destory mastership
//                        	conflict = 2;
                        	this.destoryMasterSpaceship(spaceships);
                        	break;
                        }
                        else if (spaceship.getSpaceshipTypeId() == 0 && spaceships.size() == 2) {
                            // There is a conflict to destory enemy ship
                        	this.destoryEnemySpaceship(spaceships);
                        	break;
                        }   
                    }
                }
            }
        }

//		return None;
    }
    
    public void moveSpaceship(Spaceship spaceship, int y, int x) {
        this.theGrid.addShip(spaceship, y, x);
    }
    
    public void moveMasterSpaceships(Spaceship spaceship) {
//    	isConflict
        // randomly place the MasterSpaceship in the grid
    }
    
    public void moveEnemySpaceships(Spaceship spaceship) {
//    	isConflict
        // randomly place the MasterSpaceship in the grid
    }
    
    public void placeMasterSpaceship(MasterSpaceship masterSpaceship) {
        // randomly place the MasterSpaceship in the grid
    }

    
    
    public void addEnemySpaceship(Spaceship spaceship) {
        // add the enemy spaceship to the grid
    }
    
    

//    public boolean isConflict() {
//        // check if the MasterSpaceship is in the same square as an enemy spaceship
//        return false;
//    }
    public void destoryEnemySpaceship(ArrayList<Spaceship> spaceships) {
    	System.out.println("Enemy Spaceship(s) have(s) been destoryed.");
    	for (int i = 0; i < spaceships.size(); i++) {
    	    Spaceship spaceship = spaceships.get(i);
    	    if (spaceship.getSpaceshipTypeId() != 0) {
    	        theGrid.removeSpaceship(spaceship, spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX());
    	        spaceships.remove(spaceship);
    	        i--; // Decrement i to account for removal of current element
    	    }
    	}
        System.out.print(this.theGrid.getCurrentSpaceships(0,1));
    }
    public void destoryMasterSpaceship(ArrayList<Spaceship> spaceships) {
    	System.out.println("Master Spaceship has been destoryed.");
    	for (int i = 0; i < spaceships.size(); i++) {
    	    Spaceship spaceship = spaceships.get(i);
    	    if (spaceship.getSpaceshipTypeId() == 0) {
    	        theGrid.removeSpaceship(spaceship, spaceship.getSpaceshipPositionY(), spaceship.getSpaceshipPositionX());
    	        spaceships.remove(spaceship);
    	        i--; // Decrement i to account for removal of current element
    	    }
    	}
        System.out.println(this.theGrid.getCurrentSpaceships(0,1));
    }

    public void printSky() {
        // print out the current state of the grid
    }
}
