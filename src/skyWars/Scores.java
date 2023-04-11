package skyWars;

import java.io.Serializable;

public class Scores implements Serializable, Observer {


	private static final long serialVersionUID = 1L;
	
	private int score = 0;
	private int highScore;
	
	public Scores() {}
	
	public void update(int newScore) {
		setScore(newScore);
		increaseHighScore();

	}

	// Increase highscore
	public void increaseHighScore() {
		if (this.score > this.highScore) {
			setHighScore(this.score);
		}
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void increaseScore(int score) {
		this.score += score;
	}

	public int getHighScore() {
		return this.highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
}
