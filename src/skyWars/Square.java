package skyWars;

import java.io.Serializable;
import java.util.ArrayList;

public class Square implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
