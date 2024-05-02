
package student.com.controllers.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

import student.com.dao.CourseDao;
import student.com.dao.StudentDao;
import student.com.dto.CourseDto;
import student.com.dto.StudentDto;
import student.com.models.Course;
import student.com.models.Student;





@RestController
@RequestMapping(path= "/api/student" )
@RequiredArgsConstructor
public class StudentController {
	@Autowired
	private final StudentDao studentDao;
	
	@GetMapping(value = "/getLastStudentCode")
	public ResponseEntity<?> getLastStudentCode (){
		StudentDto studentDto = new StudentDto();
	
		studentDto.setLastCode(student.com.service.Helper.getStudentCode());
		
		if(studentDto.getLastCode() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Generated lastcoursecode.");
		}
	}

	
	@PostMapping(value = "/addStudent")
	public ResponseEntity<String> addStudent (
			@RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dob") String dob,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "education") String education,
			@RequestParam(value = "courseIDs") String[] courseIDs,
			@RequestParam(value = "studentImage", required = false) MultipartFile studentImage
			
		) throws IOException{
		StudentDto studentDto = new StudentDto();
		studentDto.setName(name);
		studentDto.setCode(code);
		studentDto.setDob(dob);
		studentDto.setGender(gender);
		studentDto.setPhone(phone);
		studentDto.setEducation(education);
	
		studentDto.setProfileImageInput(studentImage);	    
		
		ModelMapper modelMapper = new ModelMapper();
		
	    InputStream inputStream = studentDto.getProfileImageInput().getInputStream();
	    
		
		
		if(studentDao.addStudentWithCourses(modelMapper.map(studentDto, Student.class), courseIDs, inputStream )) {
			return ResponseEntity.status(HttpStatus.CREATED).body("register Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Register.");
		}
	}
	
	@PostMapping("/viewStudents")
	public ResponseEntity<?> viewStudents(){
		
	
		ModelMapper modelMapper = new ModelMapper();
		List<Student> allStudents = studentDao.findAll();
		List<StudentDto> allStudentDtos = new ArrayList<>();
		for (Student student : allStudents) {
			
			allStudentDtos.add(modelMapper.map(student, StudentDto.class));
	        System.out.println(student.toString());
	    }

		if(allStudentDtos != null && !allStudentDtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(allStudentDtos);
		
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Generate Student.");
		}
	}
	
	@PostMapping("/getStudentByID")
	public ResponseEntity<?> getStudentByID (@RequestParam("id") int id){
		ModelMapper modelMapper = new ModelMapper();
		StudentDto studentDto = modelMapper.map(studentDao.findByIdWithCourses(id), StudentDto.class);
		if(studentDto != null) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(studentDto);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed edit.");
		}
	}
		
	@PostMapping("/editStudent")
	public ResponseEntity<String> editStudent (
			@RequestParam(value = "id") String id,
			@RequestParam(value = "code") String code,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "dob") String dob,
			@RequestParam(value = "gender") String gender,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "education") String education,
			@RequestParam(value = "courseIDs") String[] courseIDs,
			@RequestParam(value = "studentImage", required = false) MultipartFile studentImage
			
		) throws IOException{
		StudentDto studentDto = new StudentDto();
		studentDto.setId(Integer.parseInt(id));
		studentDto.setName(name);
		studentDto.setCode(code);
		studentDto.setDob(dob);
		studentDto.setGender(gender);
		studentDto.setPhone(phone);
		studentDto.setEducation(education);
		if (studentImage == null || studentImage.isEmpty()) {
			System.out.println("Hi5");
			studentDto.setProfileImageInput(studentDao.findImageById(studentDto.getId()));	 
		}else {
			studentDto.setProfileImageInput(studentImage);	 
		}	
		System.out.println(studentDto.toString());
		
		ModelMapper modelMapper = new ModelMapper();
		InputStream inputStream = studentDto.getProfileImageInput().getInputStream();
	    
		
		
		if(studentDao.editStudentWithCourses(modelMapper.map(studentDto, Student.class), courseIDs, inputStream)) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Edit Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed edit.");
		}
	}
	
	@PostMapping("/deleteStudent")
	public ResponseEntity<String> deleteStudent (@RequestParam("id") int id){
	
		if(studentDao.deleteStudent(id)) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Delete Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed Delete.");
		}
	}
	

}
