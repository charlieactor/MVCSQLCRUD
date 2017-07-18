package data;

import java.util.List;

public interface HikesDAO {
	public Hike getHikeByName(String name);
	public List<Hike> getListOfHikesByLength(double length);
	public List<Hike> getListOfHikesByDistanceFromDenver(double distanceFromDenver);
	public List<Hike> getListOfHikesByDifficulty(String difficulty);
	public void addHike(Hike hike);
	public List<Hike> getAllHikes();
	public void removeHike(Hike hike);
	public void rewriteFile();

}
