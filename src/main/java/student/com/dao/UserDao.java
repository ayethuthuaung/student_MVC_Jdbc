package student.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.com.models.User;

public class UserDao {
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}
	
	//Register
	public boolean register(User user) {
		int result = 0;

		if (isEmailExists(user.getEmail())) {

			return false;
		}

		String sql = "INSERT INTO user(name, email, password, role) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getRole());

			result = ps.executeUpdate();
			return true;

		} catch (SQLException e) {
			System.out.println("SQL Insert Error: " + e);

		}
		return false;
	}

	public boolean isEmailExists(String email) {
		String sql = "SELECT COUNT(*) FROM user WHERE email = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e);
		}
		return false;
	}

	public User login(User user) {
	    String sql = "SELECT * FROM user WHERE email = ? AND password = ? AND is_deleted = false ";
	    try {
	        PreparedStatement ps = con.prepareStatement(sql);
	        ps.setString(1, user.getEmail());
	        ps.setString(2, user.getPassword());
	       
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            User loggedInUser = new User();
	            loggedInUser.setId(rs.getInt("id"));
	            loggedInUser.setEmail(rs.getString("email"));
	            loggedInUser.setRole(rs.getString("role"));
	            return loggedInUser; // Return user details upon successful login
	        }
	    } catch (SQLException e) {
	        System.out.println("SQL Error: " + e);
	    }
	    return null; // Default return, indicating login failure
	}

	
	//Read
	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user where is_deleted = false";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e);
		}
		return users;
	}

	// Delete
	public boolean delete(int id) {
		int result = 0;
		String sql = "UPDATE user SET is_deleted = true WHERE id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);

			result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			System.out.println("Update Error: " + e);
			return false;
		}
		
	}

	
}
