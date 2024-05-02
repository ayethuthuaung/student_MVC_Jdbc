package student.com.dto;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import student.com.models.Course;


@Data
public class StudentDto {
	private int id;
	private String code, name, dob, gender, phone, education, photo, lastCode;	
	private List<Course> courses;
	private MultipartFile profileImageInput;
	private List<Integer> selectedCourseIDs;
}
