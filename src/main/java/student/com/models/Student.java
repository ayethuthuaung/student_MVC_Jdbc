package student.com.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import student.com.dto.CourseDto;

@Data
public class Student {
	private int id;
	private String code;
	private String name;
	private String dob;
	private String gender;
	private String phone;
	private String education;
	private String photo;	
	private List<Course> courses = new ArrayList<>(); 
	private List<Integer> selectedCourseIDs;
}
