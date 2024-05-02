package student.com.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import student.com.models.Course;
import student.com.models.Student;
import student.com.service.ProfileImageService;



public class StudentDao {
	public static Connection con = null;
	static {
		con = MyConnection.getConnection();
	}
	//create (insert)
		public boolean addStudentWithCourses(Student student, String[] courseIDs, InputStream inputStream) {
		
        PreparedStatement stmt = null;
        boolean success = false;

        try {
           
            con.setAutoCommit(false);

            // Insert student details
            String insertStudentSQL = "INSERT INTO student (code, name, dob, gender, phone, education, photo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(insertStudentSQL);
            stmt.setString(1, student.getCode());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getDob());
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getPhone());
            stmt.setString(6, student.getEducation());
           
            
            if (inputStream != null) {
                
        		stmt.setBlob(7, inputStream);
            }
            
            stmt.executeUpdate();
            stmt.close();

            // Get the auto-generated student ID
            int studentId;
            try (PreparedStatement generatedKeys = con.prepareStatement("SELECT LAST_INSERT_ID()")) {
                generatedKeys.execute();
                generatedKeys.getResultSet().next();
                studentId = generatedKeys.getResultSet().getInt(1);
            }

            // Insert student-course associations
            String insertStudentCourseSQL = "INSERT INTO student_has_course (student_id, course_id) VALUES (?, ?)";
            stmt = con.prepareStatement(insertStudentCourseSQL);
            for (String courseId : courseIDs) {
                stmt.setInt(1, studentId);
                stmt.setInt(2, Integer.parseInt(courseId));
                stmt.addBatch();
            }
            stmt.executeBatch();

            con.commit();
            success = true;
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

		//Read
		public Student findById(int id) {
			Student student = new Student();
		String sql = "SELECT * FROM student WHERE id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,id);
			
			ResultSet rs =ps.executeQuery();
			while(rs.next()) {
				student.setId(id);
				student.setCode(rs.getString("code"));
				student.setName(rs.getString("name"));
				student.setDob(rs.getString("dob"));
				student.setGender(rs.getString("gender"));
				student.setPhone(rs.getString("phone"));
				student.setEducation(rs.getString("education"));
			
			}
		}catch(SQLException e) {
			
			System.out.println("SQL findByCode Error: "+e);
			
		}
		return student;
		}	
		
	
		//Read => ResponsedTO
		public List<Student> findAll() {
		    List<Student> students = new ArrayList<>();
		    String sql = "SELECT s.*, c.name AS course_name FROM student s LEFT JOIN student_has_course shc ON s.id = shc.student_id LEFT JOIN course c ON shc.course_id = c.id WHERE s.is_deleted = false;";
		    try {
		        PreparedStatement ps = con.prepareStatement(sql);
		        ResultSet rs = ps.executeQuery();
		        Map<Integer, Student> studentMap = new HashMap<>();
		        while (rs.next()) {
		            int studentId = rs.getInt("id");
		            if (!studentMap.containsKey(studentId)) {
		            	Student student = new Student();
		                student.setId(studentId);
		                student.setCode(rs.getString("code"));
		                student.setName(rs.getString("name"));
		                student.setDob(rs.getString("dob"));
		                student.setGender(rs.getString("gender"));
		                student.setPhone(rs.getString("phone"));
		                student.setEducation(rs.getString("education"));
		                student.setCourses(new ArrayList<>());
		               
		                student.setPhoto(ProfileImageService.generateStringOfImage(rs.getBlob("photo")));
		                // Set photo data
		               
		                students.add(student);
		                studentMap.put(studentId, student);
		            }
		            // Add the attended course to the student's list of courses
		            String courseName = rs.getString("course_name");
		            if (courseName != null) {
		                Course course = new Course();
		                course.setName(courseName);
		                studentMap.get(studentId).getCourses().add(course);
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("SQL Error: " + e);
		    }
		    return students;
		}
		
		public boolean editStudentWithCourses(Student student, String[] courseIDs, InputStream inputStream) {
	
		    String updateStudentSQL = "UPDATE student SET code=?, name=?, dob=?, gender=?, phone=?, education=?, photo=? WHERE id=?";
		    String deleteCoursesSQL = "DELETE FROM student_has_course WHERE student_id=?";
		    String insertCoursesSQL = "INSERT INTO student_has_course (student_id, course_id) VALUES (?, ?)";

		    try {
		        con.setAutoCommit(false);

		        // Update student details
		        PreparedStatement updateStudentStmt = con.prepareStatement(updateStudentSQL);
		        updateStudentStmt.setString(1, student.getCode());
		        updateStudentStmt.setString(2, student.getName());
		        updateStudentStmt.setString(3, student.getDob());
		        updateStudentStmt.setString(4, student.getGender());
		        updateStudentStmt.setString(5, student.getPhone());
		        updateStudentStmt.setString(6, student.getEducation());
		        if (inputStream != null) {
	                
		        	updateStudentStmt.setBlob(7, inputStream);
	            }
		       
		        updateStudentStmt.setInt(8, student.getId());
		        int updatedRows = updateStudentStmt.executeUpdate();
		        updateStudentStmt.close();

		        // Delete existing course associations
		        PreparedStatement deleteCoursesStmt = con.prepareStatement(deleteCoursesSQL);
		        deleteCoursesStmt.setInt(1, student.getId());
		        deleteCoursesStmt.executeUpdate();
		        deleteCoursesStmt.close();

		        // Insert new course associations
		        PreparedStatement insertCoursesStmt = con.prepareStatement(insertCoursesSQL);
		        System.out.println(student.getId());
		        // Validate and insert course associations
		        for (String courseId : courseIDs) {
		            // Check if student_id exists before inserting into student_has_course
		            if (studentExists(student.getId())) {
		                insertCoursesStmt.setInt(1, student.getId());
		                insertCoursesStmt.setInt(2, Integer.parseInt(courseId));
		                insertCoursesStmt.addBatch();
		            } else {
		                // Handle case where student_id doesn't exist
		                throw new SQLException("Student with ID " + student.getId() + " does not exist.");
		            }
		        }
		        
		        // Execute batch insert
		        int[] insertedRows = insertCoursesStmt.executeBatch();

		        con.commit();

		        // Check if any rows were updated or inserted
		        if (updatedRows > 0 || insertedRows.length > 0) {
		           return true;
		        } 
		    } catch (SQLException e) {
		        if (con != null) {
		            try {
		                con.rollback();
		            } catch (SQLException ex) {
		                ex.printStackTrace();
		            }
		        }
		        System.out.println("SQL Error: " + e);
		       return false;
		    } finally {
		        if (con != null) {
		            try {
		                con.setAutoCommit(true);
		            } catch (SQLException e) {
		                e.printStackTrace();
		            }
		        }
		    }

		    return false;
		}

		// Method to check if student with given ID exists
		private boolean studentExists(int studentId) throws SQLException {
		    String query = "SELECT id FROM student WHERE id=?";
		    PreparedStatement stmt = con.prepareStatement(query);
		    stmt.setInt(1, studentId);
		    ResultSet rs = stmt.executeQuery();
		    boolean exists = rs.next();
		    rs.close();
		    stmt.close();
		    return exists;
		}

		// Delete student by ID
		public boolean deleteStudent(int id) {
			int result = 0;
			String sql = "UPDATE student SET is_deleted = true WHERE id=?";//
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
		
		// Read => ResponsedTO with courses
	    public Student findByIdWithCourses(int id) {
	    	Student student = new Student();
	        
	        String sql = "SELECT s.*, c.id AS course_id, c.name AS course_name FROM student s LEFT JOIN student_has_course shc ON s.id = shc.student_id LEFT JOIN course c ON shc.course_id = c.id WHERE s.id=?";

	        try {
	            PreparedStatement ps = con.prepareStatement(sql);
	            ps.setInt(1, id);

	            ResultSet rs = ps.executeQuery();
	            Map<Integer, Course> coursesMap = new HashMap<>();
	           
	            while (rs.next()) {
	            	 System.out.println("Hello: ");
	                if (student.getId() == 0) {
	                    student.setId(id);
	                    student.setCode(rs.getString("code"));
	                    student.setName(rs.getString("name"));
	                    student.setDob(rs.getString("dob"));
	                    student.setGender(rs.getString("gender"));
	                    student.setPhone(rs.getString("phone"));
	                    student.setEducation(rs.getString("education"));
	                    student.setPhoto(ProfileImageService.generateStringOfImage(rs.getBlob("photo")));

	                }

	                // Fetch courses associated with the student
	                int courseId = rs.getInt("course_id");
	                if (courseId != 0) {
	                    if (!coursesMap.containsKey(courseId)) {
	                        Course course = new Course();
	                        course.setId(courseId);
	                        course.setName(rs.getString("course_name"));
	                        coursesMap.put(courseId, course);
	                 
	                    }
	                    student.getCourses().add(coursesMap.get(courseId));
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("SQL Error: " + e);
	        }
	        return student;
	    }
	    
	    public String getLastStudentCode() {
			String lastCode = "";
			String sql = "SELECT code FROM student ORDER BY id DESC LIMIT 1";
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
	    
	    public MultipartFile findImageById(int id) {
	        MultipartFile image = null;
	        String sql = "SELECT photo FROM student WHERE id=?";
	        try ( PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, id);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    Blob blob = rs.getBlob("photo");
	                    if (blob != null) {
	                        byte[] imageData = blob.getBytes(1, (int) blob.length());
	                        String fileName = "edited_image_" + id + ".jpg";
	                        image = new MockMultipartFile(fileName, new ByteArrayInputStream(imageData));
	                    }
	                }
	            }
	        } catch (SQLException | IOException e) {
	            System.out.println("Error fetching image: " + e.getMessage());
	        }
	        return image;
	    }

		
}
