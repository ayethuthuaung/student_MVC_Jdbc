package student.com.service;

import student.com.dao.CourseDao;
import student.com.dao.StudentDao;

public class Helper {
	static CourseDao courseDao = new CourseDao();
	static StudentDao studentDao = new StudentDao();
	
	public static String getCourseCode() {
		String courseCode = "";
		String lastCode = courseDao.getLastCourseCode();
		if (lastCode == "" || lastCode.equals("")) {
		    lastCode = "AI000";

		}
		int numericPart = Integer.parseInt(lastCode.substring(2))+1;
		courseCode = String.format("AI%03d", numericPart);
		return courseCode;
	}

	
	
	public static String getStudentCode() {
		String studentCode = "";
		System.out.println("Hi");
		String lastCode = studentDao.getLastStudentCode();
		if (lastCode == "" || lastCode.equals("") || lastCode == null) {
		    lastCode = "STU000";

		}
		System.out.println("lastCode: " + lastCode);
		int numericPart = Integer.parseInt(lastCode.substring(3))+1;
		studentCode = String.format("STU%03d", numericPart);
		return studentCode;
	}
}
