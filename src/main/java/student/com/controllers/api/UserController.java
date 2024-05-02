package student.com.controllers.api;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import student.com.dao.UserDao;
import student.com.dto.UserDto;
import student.com.models.User;



@RestController
@RequestMapping(path= "/api/user" )
@RequiredArgsConstructor
public class UserController {
	@Autowired
	private final UserDao userDao;
	
	@PostMapping(value = "/login", consumes = {"application/xml","application/json"})
	public ResponseEntity<?> login (@RequestParam("email") String email, @RequestParam("password") String password,HttpSession session){
	    UserDto userDto = new UserDto();
	    userDto.setEmail(email);
	    userDto.setPassword(password);
	   
	    ModelMapper modelMapper = new ModelMapper();
	    User currentUser = userDao.login(modelMapper.map(userDto, User.class));

	    
	    if(currentUser != null ) {
		    session.setAttribute("userLogin", currentUser);

	        return ResponseEntity.status(HttpStatus.OK).body(currentUser);
	    } else {
	    	
	        return ResponseEntity.status(HttpStatus.OK).body("Invalid Email or Password.");
	    }
	}

	
	@PostMapping(value = "/register", consumes = {"application/xml","application/json"})
	public ResponseEntity<String> register (@RequestParam("name") String name,@RequestParam("email") String email, @RequestParam("password") String password,@RequestParam("role") String role ){
		UserDto userDto = new UserDto();
		userDto.setName(name);
		userDto.setEmail(email);
		userDto.setPassword(password);
		userDto.setRole(role);
		ModelMapper modelMapper = new ModelMapper();
		if(userDao.register(modelMapper.map(userDto, User.class))) {
			return ResponseEntity.status(HttpStatus.OK).body("register Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Failed to register users.");
		}
	}
	
	@GetMapping("/emailExists")
    public ResponseEntity<String> emailExists(@RequestParam String email) {
		System.out.print("Hello" + userDao.isEmailExists(email));
		if(userDao.isEmailExists(email)) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Email exits.");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Email.");
		}

}
	
	@PostMapping("/viewUsers")
	public ResponseEntity<?> viewUsers(){
		
	
		ModelMapper modelMapper = new ModelMapper();
		List<User> allUsers = userDao.findAll();
		List<UserDto> allUserDtos = new ArrayList<>();
		for (User user : allUsers) {
	        allUserDtos.add(modelMapper.map(user, UserDto.class));
	        System.out.println(user.toString());
	    }

		if(allUserDtos != null && !allUserDtos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(allUserDtos);
		
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to Generate User.");
		}
	}
	
	
	@PostMapping("/delete")
	public ResponseEntity<String> delete (@RequestParam("id") int id){
	
		if(userDao.delete(id)) {
			
			return ResponseEntity.status(HttpStatus.CREATED).body("Delete Successful.");
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Failed Delete.");
		}
	}
	

}
