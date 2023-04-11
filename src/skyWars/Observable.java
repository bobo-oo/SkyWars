package skyWars;

public interface Observable {
	public void registerObserver(Scores scores);
    public void removeObserver(Scores scores);
    public void notifyObservers();

}

