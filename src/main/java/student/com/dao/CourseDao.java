package student.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import student.com.models.Course;


public class CourseDao {
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}

	// create (insert)
	public boolean add(Course course) {

		if (isCourseNameExists(course.getName())) {
			return false;
		}

		String sql = "INSERT INTO course (code ,name) VALUES (? ,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, course.getCode());
			ps.setString(2, course.getName());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("SQL Insert Error: " + e);

		}
		return false;
	}

	public boolean isCourseNameExists(String name) {
		String sql = "SELECT COUNT(*) FROM course WHERE name = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
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
	
	//Read
			public Course findById(int id) {
				Course course = new Course();
			String sql = "SELECT * FROM course WHERE id=?";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1,id);
				
				ResultSet rs =ps.executeQuery();
				while(rs.next()) {
					course.setId(id);
					System.out.println(course.getId());
					course.setCode(rs.getString("code"));
					course.setName(rs.getString("name"));
				
				}
			}catch(SQLException e) {
				
				System.out.println("SQL findByCode Error: "+e);
				
			}
			return course;
			}	

		//Update
				public boolean edit(Course course) {
					int result = 0;
					String sql="UPDATE course SET code=?,name=? where id=?";
					try {
						PreparedStatement ps = con.prepareStatement(sql);
						
						ps.setString(1,course.getCode());
						ps.setString(2,course.getName());
						ps.setInt(3,course.getId());
						result = ps.executeUpdate();
						return result > 0;
					}catch(SQLException e) {
						System.out.println("Update Error: "+e);
						
					}
					return false;
				}
				

	public String getLastCourseCode() {
		String lastCode = "";
		String sql = "SELECT code FROM course ORDER BY id DESC LIMIT 1";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				lastCode = rs.getString("code");

			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e);
		}
		return lastCode;
	}

	// Read
	public List<Course> findAll() {
		List<Course> courses = new ArrayList<>();
		String sql = "SELECT * FROM course where is_deleted = false";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getInt("id"));
				course.setCode(rs.getString("code"));
				course.setName(rs.getString("name"));

				courses.add(course);
			}
		} catch (SQLException e) {
			System.out.println("SQL Error: " + e);
		}
		return courses;
	}

	// Delete
	public boolean delete(int id) {
		int result = 0;
		String sql = "UPDATE course SET is_deleted = true WHERE id=?";//
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
