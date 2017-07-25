package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class HikesDaoDBImpl implements HikesDAO {

	private static String url = "jdbc:mysql://localhost:3306/hikesdb";
	private String user = "hikesuser";
	private String pass = "hikesuser";
	
	
	@Autowired
	private WebApplicationContext wac;

	public HikesDaoDBImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}

	@Override
	public Hike getHikeByName(String name) {
		Hike hike = null;
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "Select name, length, difficulty, distance_from_city, fact, id FROM hike WHERE name LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + name + "%");
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				hike = new Hike(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getInt(6));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hike;
	}

	@Override
	public List<Hike> getListOfHikesByLength(double length) {
		List<Hike> hikesByLength = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "Select name, length, difficulty, distance_from_city, fact, id FROM hike WHERE length < ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, length);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				hikesByLength.add(
						new Hike(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hikesByLength;
	}

	@Override
	public List<Hike> getListOfHikesByDistanceFromDenver(double distanceFromDenver) {
		List<Hike> hikesByDistance = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "Select name, length, difficulty, distance_from_city, fact, id FROM hike WHERE distance_from_city < ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(1, distanceFromDenver);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				hikesByDistance.add(
						new Hike(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hikesByDistance;
	}

	@Override
	public List<Hike> getListOfHikesByDifficulty(String difficulty) {
		List<Hike> hikesByDifficulty = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "Select name, length, difficulty, distance_from_city, fact, id FROM hike WHERE difficulty = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, difficulty);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				hikesByDifficulty.add(
						new Hike(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hikesByDifficulty;
	}

	@Override
	public void addHike(Hike hike) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "INSERT INTO hike (name, difficulty, length, distance_from_city, fact) VALUES (?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, hike.getName());
			stmt.setString(2, hike.getDifficulty());
			stmt.setDouble(3, hike.getLength());
			stmt.setDouble(4, hike.getDistanceFromDenver());
			stmt.setString(5, hike.getFact());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newHikeId = keys.getInt(1);
					hike.setId(newHikeId);
				}
			} else {
				hike = null;
			}
			conn.commit(); // COMMIT TRANSACTION
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting hike " + hike.getName());
		}
	}

	@Override
	public List<Hike> getAllHikes() {
		List<Hike> allHikes = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
			String sql = "SELECT name, length, difficulty, distance_from_city, fact, id FROM hike";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				allHikes.add(
						new Hike(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getDouble(4), rs.getString(5), rs.getInt(6)));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allHikes;
	}

	@Override
	public void removeHike(Hike hike) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "DELETE FROM hike WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, hike.getId());
			stmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error deleting hike id " + hike.getId());
		}

	}
	
	public void updateHike(Hike hike) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE hike SET name = ?, difficulty = ?, length = ? distance_from_city = ?, fact = ? WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, hike.getName());
			stmt.setString(2, hike.getDifficulty());
			stmt.setDouble(3, hike.getLength());
			stmt.setDouble(4, hike.getDistanceFromDenver());
			stmt.setString(5, hike.getFact());
			stmt.setInt(6, hike.getId());
			stmt.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e2) {
					System.out.println("Error trying to rollback");
				}
			} throw new RuntimeException("Error updating hike id " + hike.getId());
		}
	}

}
