package skyWars;

import java.io.Serializable;
import java.util.ArrayList;

public class Grid implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private ArrayList<Row> theRows = new ArrayList<>();
	private static final int NUMBER_OF_ROWS = 4;
	
	public Grid() {
		for (int i = 0; i < NUMBER_OF_ROWS; i++) {
			Row row = new Row(i);
			theRows.add(row);
		}
	}
	
	public ArrayList<Row> getTheRows() {
		return this.theRows;
	}
	
	public boolean isThereAShipOn(int row, int square) {
		for (Row tempRow : this.theRows) {
			if (tempRow.getNumber() == row) {
				for (Square tempSquare : tempRow.getTheSquares()) {
					if (tempSquare.getNumber() == square) {
						return !tempSquare.getSpaceships().isEmpty();
					}
				}
			}
		}
		return false;
	}
	
	public void addShip(Spaceship spaceship, int row, int square) {
		for (Row tempRow : this.theRows) {
			if (tempRow.getNumber() == row) {
				for (Square tempSquare : tempRow.getTheSquares()) {
					if (tempSquare.getNumber() == square) {
						tempSquare.addSpaceship(spaceship);
						spaceship.setSpaceshipPositionY(row);
						spaceship.setSpaceshipPositionX(square);
						
					}
				}
			}
		}
	}
	
	public void removeSpaceship(Spaceship spaceship, int row, int square) {
		for (Row tempRow : this.theRows) {
			if (tempRow.getNumber() == row) {
				for (Square tempSquare : tempRow.getTheSquares()) {
					if (tempSquare.getNumber() == square) {
						tempSquare.removeSpaceship(spaceship);
						System.out.println("Remove successfully.");
					}
				}
			}
		}
	}
	
	public void moveSpaceship(Spaceship spaceship, int newRow, int newSquare) {
		int currentRow = spaceship.getSpaceshipPositionY();
	    int currentSquare = spaceship.getSpaceshipPositionX();
	    // Remove spaceship from current square
	    removeSpaceship(spaceship, currentRow, currentSquare);
	    // Add spaceship to new square
	    addShip(spaceship, newRow, newSquare);
	    System.out.println("Spaceship moved successfully.");
	}

	public ArrayList<Spaceship> getCurrentSpaceships(int row, int square) {
		for (Row tempRow : this.theRows) {
			if (tempRow.getNumber() == row) {
				for (Square tempSquare : tempRow.getTheSquares()) {
					if (tempSquare.getNumber() == square) {
						return tempSquare.getSpaceships();
					}
				}
			}
		}
		return new ArrayList<>();
	}
	public static boolean isValidMove(int currentRow, int currentCol, int newRow, int newCol) {
		
		// Check if new position is within grid boundaries
        if (newRow < 0 || newRow >= NUMBER_OF_ROWS || newCol < 0 || newCol >= NUMBER_OF_ROWS) {
            return false;
        }
        // Check if new position is a valid neighbour of current position
        int rowDiff = Math.abs(newRow - currentRow);
        int colDiff = Math.abs(newCol - currentCol);
        if (rowDiff > 1 || colDiff > 1 || (rowDiff == 0 && colDiff == 0)) {
            return false;
        }

        return true;
    }
	
}
