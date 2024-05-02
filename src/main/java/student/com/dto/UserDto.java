package student.com.dto;

import lombok.Data;

@Data
public class UserDto {
	private int id;
	private String name, email, password, role;
	private boolean is_deleted;
	

}
