
//User
async function login(requestData) {
	try {
		const { email, password } = requestData;
		const response = await sendRequestWithTwoParams(`http://localhost:8080/Student_MVC_JDBC/api/user/login`, 'POST', "email", email, "password", password);

		if (response.ok) {
			const contentType = response.headers.get('content-type');
			if (contentType && contentType.includes('application/json')) {
				const responseData = await response.json();
				console.log(responseData)
				return responseData;
			} else {
				const textData = await response.text();
				return textData;
			}

		} else {
			throw new Error('Failed to fetch last course code');
		}
	} catch (error) {
		console.error('Error:', error);
	}
}

async function register(requestData) {
	try {
		const { name, email, password, role } = requestData;
		const response = await sendRequestWithFourParams(`http://localhost:8080/Student_MVC_JDBC/api/user/register`, 'POST', "name", name, "email", email, "password", password, "role", role);
		
		
		//if (role === "user") {
			const contentType = response.headers.get('content-type');
			if (contentType && contentType.includes('application/json')) {
				const responseData = await response.json();
				console.log(responseData)
				return responseData;
			} else {
				const textData = await response.text();
				return textData;
			}
			//handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/login`);
		//}
		//handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewUser`);
	} catch (error) {
		console.error('Error:', error);
	}
}

async function checkEmailExists(email) {
	try {
		const response = await $.ajax({
			url: 'http://localhost:8080/api/user/emailExists',
			method: 'GET',
			data: { email: email }
		});
		return response;
	} catch (error) {
		console.error('Error checking email:', error);
		return false; // Return false in case of error
	}
}
async function viewUsers() {
	try {

		const responseData = await sendRequest(`http://localhost:8080/Student_MVC_JDBC/api/user/viewUsers`, 'POST');
		console.table(responseData)
		return responseData;
	} catch (error) {
		console.error('Error:', error);
	}
}

async function deleteUser(requestData) {
	try {
		const { id } = requestData;
		const responseData = await sendRequestWithOneParam(`http://localhost:8080/Student_MVC_JDBC/api/user/delete`, 'POST', "id", id);
		handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewUser`);
	} catch (error) {
		console.error('Error:', error);
	}
}

//User

//Course
async function addCourse(requestData) {
	try {

		const responseData = await sendRequest(`http://localhost:8080/Student_MVC_JDBC/api/course/addCourse`, 'POST', requestData);
		
		handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewCourse`);
	} catch (error) {
		console.error('Error:', error);
	}
}

/*async function checkCourseExists(name) {
	try {
		const response = await $.ajax({
			url: 'http://localhost:8080/api/course/courseExists',
			method: 'GET',
			data: { name: name }
		});
		return response;
	} catch (error) {
		console.error('Error checking email:', error);
		return false; // Return false in case of error
	}
}*/

async function getLastCourseCode() {
	try {
		const response = await fetch('http://localhost:8080/Student_MVC_JDBC/api/course/getLastCourseCode');
		if (response.ok) {
			const data = await response.json(); // Parse JSON response
			return data; // Return the entire CourseDto object
		} else {
			throw new Error('Failed to fetch last course code');
		}
	} catch (error) {
		console.error('Error:', error);
	}
}

async function viewCourse() {
	try {

		const responseData = await sendRequest(`http://localhost:8080/Student_MVC_JDBC/api/course/viewCourses`, 'POST');
		console.table(responseData)
		return responseData;
	} catch (error) {
		console.error('Error:', error);
	}
}

async function getCourseById(requestData) {
	try {
		const { id } = requestData;
		const response = await sendRequestWithOneParam(`http://localhost:8080/Student_MVC_JDBC/api/course/getCourseByID`, 'POST', "id", id);
		if (response.ok) {
			const data = await response.json(); // Parse JSON response
			return data; // Return the entire CourseDto object
		} else {
			throw new Error('Failed to fetch last course code');
		}
		//handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewCourse`);

	} catch (error) {
		console.error('Error:', error);
	}
}

async function editCourse(requestData) {
	try {

		const responseData = await sendRequest(`http://localhost:8080/Student_MVC_JDBC/api/course/edit`, 'POST', requestData);
		localStorage.removeItem("editCourseData");
		handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewCourse`);
	} catch (error) {
		console.error('Error:', error);
	}
}

async function deleteCourse(requestData) {
	try {
		const { id } = requestData;
		const responseData = await sendRequestWithOneParam(`http://localhost:8080/Student_MVC_JDBC/api/course/delete`, 'POST', "id", id);
		handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewCourse`);
	} catch (error) {
		console.error('Error:', error);
	}
}

//Student
async function getLastStudentCode() {
	try {
		const response = await fetch('http://localhost:8080/Student_MVC_JDBC/api/student/getLastStudentCode');
		if (response.ok) {
			const data = await response.json(); // Parse JSON response
			return data; // Return the entire StudentDto object
		} else {
			throw new Error('Failed to fetch last student code');
		}
	} catch (error) {
		console.error('Error:', error);
	}
}

async function viewStudent() {
	try {

		const responseData = await sendRequest(`http://localhost:8080/Student_MVC_JDBC/api/student/viewStudents`, 'POST');
		console.table(responseData)
		return responseData;
	} catch (error) {
		console.error('Error:', error);
	}
}

async function deleteStudent(requestData) {
	try {
		const { id } = requestData;
		const responseData = await sendRequestWithOneParam(`http://localhost:8080/Student_MVC_JDBC/api/student/deleteStudent`, 'POST', "id", id);
		handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewStudent`);
	} catch (error) {
		console.error('Error:', error);
	}
}

async function getStudentById(requestData) {
	try {
		const { id } = requestData;
		const response = await sendRequestWithOneParam(`http://localhost:8080/Student_MVC_JDBC/api/student/getStudentByID`, 'POST', "id", id);
		if (response.ok) {
			const data = await response.json(); // Parse JSON response
			return data; // Return the entire CourseDto object
		} else {
			throw new Error('Failed to fetch last student code');
		}
		//handleResponse(responseData, `http://localhost:8080/Student_MVC_JDBC/viewCourse`);

	} catch (error) {
		console.error('Error:', error);
	}
}




