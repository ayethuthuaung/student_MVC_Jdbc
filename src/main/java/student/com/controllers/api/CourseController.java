package student.com.controllers.api;

import java.util.ArrayList;
import java.util.List;

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

import lombok.RequiredArgsConstructor;
import lombok.experimental.Helper;
import student.com.dao.CourseDao;
import student.com.dto.CourseDto;
import student.com.dto.UserDto;
import student.com.models.Course;
import student.com.models.User;




@RestController
@RequestMapping(path= "/api/course" )
@RequiredArgsConstructor
public class CourseController {
	@Autowired
	private final CourseDao courseDao;
	
	@GetMapping(value = "/getLastCourseCode")
	public ResponseEntity<?> getLastCourseCode (){
		CourseDto courseDto = new CourseDto();
		System.out.println("Hi");
		courseDto.setLastCode(student.com.service.Helper.getCourseCode());
		
		if(courseDto.getLastCode() != null) {
			return ResponseEntity.status(HttpStatus.OK).body(courseDto);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Generated lastcoursecode.");
		}
	}

	
	@PostMapping(value = "/addCourse", consumes = {"application/xml","application/json"})
	public ResponseEntity<String> addCourse (@RequestBody CourseDto courseDto){
	
		ModelMapper modelMapper = new ModelMapper();
		if(courseDao.add(modelMapper.map(courseDto, Course.class))) {
			return ResponseEntity.status(HttpStatus.CREATED).body("register Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Failed to Register Courses.");
		}
	}
	
	@GetMapping("/courseExists")
    public ResponseEntity<String> courseExists(@RequestParam String name) {
		System.out.print("Hello" + courseDao.isCourseNameExists(name));
		if(courseDao.isCourseNameExists(name)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Course exits.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Course.");
		}

}
	
	@PostMapping("/viewCourses")
	public ResponseEntity<?> viewCourses(){
		
	
		ModelMapper modelMapper = new ModelMapper();
		List<Course> allCourses = courseDao.findAll();
		List<CourseDto> allCourseDtos = new ArrayList<>();
		for (Course course : allCourses) {
			
			allCourseDtos.add(modelMapper.map(course, CourseDto.class));
	        System.out.println(course.toString());
	    }

		if(allCourseDtos != null && !allCourseDtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(allCourseDtos);
		
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Generate Couse.");
		}
	}
	
	@PostMapping("/getCourseByID")
	public ResponseEntity<?> getCourseByID (@RequestParam("id") int id){
		ModelMapper modelMapper = new ModelMapper();
		CourseDto courseDto = modelMapper.map(courseDao.findById(id), CourseDto.class);
		if(courseDto != null) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body(courseDto);
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed edit.");
		}
	}
		
	@PostMapping("/edit")
	public ResponseEntity<String> edit (@RequestBody CourseDto courseDto){
		ModelMapper modelMapper = new ModelMapper();
		if(courseDao.edit(modelMapper.map(courseDto, Course.class))) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Edit Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed edit.");
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete (@RequestParam("id") int id){
	
		if(courseDao.delete(id)) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Delete Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed Delete.");
		}
	}
	

}
