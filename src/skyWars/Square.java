package skyWars;

import java.util.ArrayList;

public class Square {
	
	private ArrayList<Spaceship> spaceships = new ArrayList<>();
	private int number;
	
	public Square(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean isThereASpaceship() {
		return !spaceships.isEmpty();
	}
	
	public void addSpaceship(Spaceship spaceship) {
		spaceships.add(spaceship);
	}
	
	public void removeSpaceship(Spaceship spaceship) {
		spaceships.remove(spaceship);
	}
	
	public ArrayList<Spaceship> getSpaceships() {
		return spaceships;
	}
}
