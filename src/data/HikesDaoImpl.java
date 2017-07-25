package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class HikesDaoImpl implements HikesDAO {

	private static final String FILE_NAME = "/WEB-INF/hikes.csv";
	private List<Hike> allHikes = new ArrayList<>();

	@Autowired
	private WebApplicationContext wac;

	@PostConstruct
	public void init() {
		try (InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				int counter = 1; 
				String[] tokens = line.split(", ");
				String name = tokens[0];
				String difficulty = tokens[1];
				double length = Double.parseDouble(tokens[2]);
				double distance = Double.parseDouble(tokens[3]);
				String fact = tokens[4];

				allHikes.add(new Hike(name, length, difficulty, distance, fact, counter++));
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public Hike getHikeByName(String name) {
		Hike h = null;

		for (Hike hike : allHikes) {
			if (hike.getName().equalsIgnoreCase(name)) {
				h = hike;
				break;
			}
		}
		if (h == null) {
			for (Hike hike : allHikes) {
				String hikeName = hike.getName().toLowerCase();
				name = name.toLowerCase();
				if (hikeName.startsWith(name)) {
					h = hike;
					break;
				}
			}
		}
		return h;
	}

	@Override
	public List<Hike> getListOfHikesByLength(double length) {
		List<Hike> hikesByLength = new ArrayList<>();
		if (length != 0) {
			for (Hike hike : allHikes) {
				if (hike.getLength() <= length) {
					hikesByLength.add(hike);
				}
			}
		}

		return hikesByLength;
	}

	@Override
	public List<Hike> getListOfHikesByDistanceFromDenver(double distanceFromDenver) {
		List<Hike> hikesByDistanceFromDenver = new ArrayList<>();

		for (Hike hike : allHikes) {
			if (hike.getDistanceFromDenver() <= distanceFromDenver) {
				hikesByDistanceFromDenver.add(hike);
			}
		}
		return hikesByDistanceFromDenver;
	}

	@Override
	public List<Hike> getListOfHikesByDifficulty(String difficulty) {
		List<Hike> hikesByDifficulty = new ArrayList<>();

		for (Hike hike : allHikes) {
			if (hike.getDifficulty().equalsIgnoreCase(difficulty)) {
				hikesByDifficulty.add(hike);
			}
		}
		return hikesByDifficulty;
	}

	@Override
	public void addHike(Hike hike) {
		allHikes.add(hike);
	}

	@Override
	public void removeHike(Hike hike) {
		allHikes.remove(hike);
	}

	public List<Hike> getAllHikes() {
		return allHikes;
	}

	public void setAllHikes(List<Hike> allHikes) {
		this.allHikes = allHikes;
	}

	public void rewriteFile() {
		String path = wac.getServletContext().getRealPath(FILE_NAME);
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			for (int i = 0; i < allHikes.size(); i++) {
				writer.println(allHikes.get(i).getName() + ", " + allHikes.get(i).getDifficulty() + ", "
						+ allHikes.get(i).getLength() + ", " + allHikes.get(i).getDistanceFromDenver() + ", "
						+ allHikes.get(i).getFact());
			}
			writer.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
