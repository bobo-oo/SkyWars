package skyWars;

import java.io.Serializable;

public abstract class Spaceship implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int spaceshipTypeId;
	private String spaceshipName;

	private int positionX;
	private int positionY;
	
	public int getSpaceshipTypeId() {
		return this.spaceshipTypeId;
	}

	public void setSpaceshipTypeId(int spaceShipTypeId) {
		this.spaceshipTypeId = spaceShipTypeId;
	}

	public int getSpaceshipPositionX() {
		return this.positionX;
	}
	
	public int getSpaceshipPositionY() {
		return this.positionY;
	}
	
	public void setSpaceshipPositionX(int x) {
		this.positionX = x;
	}
	
	public void setSpaceshipPositionY(int y) {
		this.positionY = y;
	}
	
	public String getSpaceshipName() {
		return this.spaceshipName;
	}

	public void setSpaceshipName(String spaceshipName) {
		this.spaceshipName = spaceshipName;
	}
}
