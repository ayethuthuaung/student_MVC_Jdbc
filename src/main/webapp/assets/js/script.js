/**
 * 
 */

$(document).ready(async function() {
	//User
	//login

	const loginBtn = $("#login-btn");
	console.log(loginBtn);
	const emailInput = $("#userEmail");
	console.log(emailInput);
	const passwordInput = $("#userPassword");
	console.log(passwordInput);
	loginBtn.on("click", async function(event) {
		event.preventDefault()
		var email = emailInput.val();
		var password = passwordInput.val();

		if (!email || !password) {

			if (!email) {
				$("#loginEmailError").text("Email is required.");
			} if (email) {
				$("#loginEmailError").text("");
			}
			if (!password) {
				$("#loginPasswordError").text("Password is required.");
			} if (password) {
				$("#loginPasswordError").text("");
			}
			return;
		}
		var requestData = {
			email: email,
			password: password
		};
		console.table(requestData)
		$("#loginEmailError").text("");
		$("#loginPasswordError").text("");
		const currentUser = await login(requestData);
		console.log(currentUser);
		if (currentUser === "Invalid Email or Password.") {
			$("#loginError").text(currentUser);
			return;
		}

		localStorage.setItem('userEmail', currentUser.email);
		localStorage.setItem('userRole', currentUser.role);


		window.location.href = "http://localhost:8080/Student_MVC_JDBC/dashboard";


	});


	//User Register
	const signupBtn = $("#signup-btn");
	console.log(signupBtn);
	const userName = $("#userName");
	console.log(userName);
	const userEmail = $("#userEmail");

	console.log(userEmail);
	const userPassword = $("#userPassword");
	console.log(userPassword);

	signupBtn.on("click", async function(event) {
		event.preventDefault()
		var name = userName.val();
		var email = userEmail.val();
		var password = userPassword.val();
		var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;

		$("#userNameError").text("");
		$("#userEmailError").text("");
		$("#userPasswordError").text("");

		// Validate inputs
		if (!name) {
			$("#userNameError").text("Name is required.");
			return;
		}
		if (!email) {
			$("#userEmailError").text("Email is required.");
			return;
		} else if (!emailRegex.test(email)) {
			$("#userEmailError").text("Invalid email format.");
			return;
		}
		if (!password) {
			$("#userPasswordError").text("Password is required.");
			return;
		} else if (!passwordRegex.test(password)) {
			$("#userPasswordError").text("Password must be 6-20 characters long and contain at least one digit, one lowercase letter, and one uppercase letter.");
			return;
		}

		$("#userNameError").text("");
		$("#userEmailError").text("");
		$("#userPasswordError").text("");
		const role = "user"
		var requestData = {
			name: name,
			email: email,
			password: password,
			role: role
		};
		console.table(requestData)
		const response = await register(requestData)
		if (response === "Failed to register users.") {
			$("#userEmailError").text("email already exits.");
			return;
		} else {

			window.location.href = "http://localhost:8080/Student_MVC_JDBC/login";
		}
	});

	//Admin Register
	const addAdminBtn = $("#addAdminBtn");
	console.log(addAdminBtn);
	const adminName = $("#adminName");
	console.log(adminName);
	const adminEmail = $("#adminEmail");
	console.log(adminEmail);
	const adminPassword = $("#adminPassword");
	console.log(adminPassword);
	const adminConfirmPassword = $("#adminConfirmPassword");
	console.log(adminConfirmPassword);

	addAdminBtn.on("click", async function(event) {
		event.preventDefault()
		var name = adminName.val();
		var email = adminEmail.val();
		var password = adminPassword.val();
		var confirmPassword = adminConfirmPassword.val();
		var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		var passwordRegex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;


		$("#adminNameError").text("");
		$("#adminEmailError").text("");
		$("#adminPasswordError").text("");
		$("#adminConfirmEmailError").text("");

	
			console.log("Hi Admin")
			if (!name) {
				$("#adminNameError").text("Name is required.");
			} if (name) {
				$("#adminNameError").text("");
			}
			if (!email) {
				$("#adminEmailError").text("Email is required.");
			} else if (!emailRegex.test(email)) {
				$("#adminEmailError").text("Invalid email format.");

			}
			if (!password) {
				$("#adminPasswordError").text("Password is required.");
			} else if (!passwordRegex.test(password)) {
				console.log("adminPasswordError:"+ adminPasswordError);
				$("#adminPasswordError").text("Password must be 6-20 characters long and contain at least one digit, one lowercase letter, and one uppercase letter.");


			}
			if (!confirmPassword) {
				$("#adminConfirmEmailError").text("Password is required.");
			} if (confirmPassword) {
				$("#adminConfirmEmailError").text("");
			}

		
		$("#adminNameError").text("");
		$("#adminEmailError").text("");
		$("#adminPasswordError").text("");
		$("#adminConfirmEmailError").text("");
		if (password !== confirmPassword) {
			$("#adminConfirmEmailError").text("Passwords do not match");
			return;
		}
		const role = "admin"
		var requestData = {
			name: name,
			email: email,
			password: password,
			role: role
		};
		console.table(requestData)
		const response = await register(requestData)
		if (response === "Failed to register users.") {
			$("#adminEmailError").text("email already exits.");
			return;
		} else {
			window.location.href = "http://localhost:8080/Student_MVC_JDBC/viewUser";
		}

	});

	//Header Data
	setHeaderData();
	function setHeaderData() {
		console.log("Hi");
		//if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/dashboard')) {
		$("#headerUserEmail").text("User: " + localStorage.getItem('userEmail'));
		$("#headerDate").text("Date: " + new Date().toLocaleDateString());
		const currentUserRole = localStorage.getItem('userRole');
		if (currentUserRole === "admin") {

		} if (currentUserRole === "user") {
			$(".hide-to-user").hide();
		}
		//}
	}

	//View Users
	await fetchUsers();
	async function fetchUsers() {

		if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/viewUser')) {
			const response = await viewUsers();
			const responseData = response.json();
			console.table(responseData);
			appendUserTable();

		}
	}

	//Delete User
	async function deleteUserById(id) {
		var requestData = {
			id: id

		};
		console.table(requestData)

		await deleteUser(requestData)

	}


	//Data Table
	$('#userTable').on('click', '.btn-danger', async function(event) {
		console.log("Hi");
		const result = await showYesNoModal();

		if (result.isConfirmed) {
			const userId = $(this).data('id');
			deleteUserById(userId);
		}
	});

	async function appendUserTable() {
		var table = new DataTable('#userTable', {
			paging: true
		});
		try {
			const response = await viewUsers();
			if (response.ok) {
				const userData = await response.json();//change backenddata into json object
				const currentUserRole = localStorage.getItem('userRole');

				table.clear();
				var count = 0;
				userData.forEach(user => {
					count++;
					let deleteButton;
					if (user.role === 'admin' && currentUserRole !== 'master' || user.role === 'master') {
						deleteButton = `<button type="submit" class="btn btn-secondary mb-3" disabled="disabled">Delete</button>`;
					} else {
						deleteButton = `<button type="submit" class="btn btn-danger mb-3" data-id="${user.id}">Delete</button>`;
					}


					const $deleteButton = $(deleteButton);//JQuery Object

					table.row.add([
						`<div style="text-align: center;">${count}</div>`,

						user.name,
						user.email,
						user.role,
						$deleteButton.prop('outerHTML') // Convert jQuery object back to HTML string
					]).draw(false);
				});
			} else {
				console.error('Failed to fetch student data:', response.statusText);
			}
		} catch (error) {
			console.error('Error fetching student data:', error);
		}
	}

	//Course


	//Add Course
	const addCourseBtn = $("#addCourseBtn");
	console.log(addCourseBtn);
	const courseCode = $("#courseCode");
	console.log(courseCode);
	const lastCourseCode = await getLastCourseCode();

	console.log(lastCourseCode);
	courseCode.val(lastCourseCode.lastCode);

	const courseName = $("#courseName");
	console.log(courseName);


	addCourseBtn.on("click", async function(event) {
		event.preventDefault()
		var code = courseCode.val();
		var name = courseName.val();

		if (!name) {


			if (!name) {
				$("#courseNameError").text("Name is required.");
			} if (name) {
				$("#courseNameError").text("");

				return;
			}

		}

		$("#courseNameError").text("");


		var requestData = {
			code: code,
			name: name

		};
		console.table(requestData)
		await addCourse(requestData)

	});

	//View Course
	await fetchCourses();
	async function fetchCourses() {

		if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/viewCourse')) {
			const response = await viewCourse();
			const responseData = response.json();
			console.table(responseData);
			appendCourseTable();

		}
	}

	//Edit Course 


	async function editCourseById(id) {
		var requestData = {
			id: id

		};
		console.table(requestData)
		const response = await getCourseById(requestData)
		console.log(response);
		localStorage.setItem('editCourseData', JSON.stringify(response));
		window.location.href = "http://localhost:8080/Student_MVC_JDBC/editCourse"
	}
	if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/editCourse')) {
		const editCourseBtn = $("#editCourseBtn");
		console.log(editCourseBtn);
		const editedCourseCode = $("#editedCourseCode");
		console.log(editedCourseCode);
		const editedCourseName = $("#editedCourseName");
		console.log(editedCourseName);
		const editCourseData = JSON.parse(localStorage.getItem('editCourseData'));
		console.log(editCourseData);
		if (editCourseData !== null) {
			editedCourseCode.val(editCourseData.code);
			editedCourseName.val(editCourseData.name)
		}
		editCourseBtn.on("click", async function(event) {

			event.preventDefault()
			const result = await showYesNoModal2();

			if (result.isConfirmed) {

				var code = editedCourseCode.val();
				var name = editedCourseName.val();

				if (!name) {


					if (!name) {
						$("#editedCourseNameError").text("Name is required.");
					} if (name) {
						$("#editedCourseNameError").text("");

						return;
					}

				}

				$("#editedCourseNameError").text("");


				var requestData = {
					id: editCourseData.id,
					code: code,
					name: name

				};
				console.table(requestData)
				await editCourse(requestData)
			}
		});


	}


	//Delete Course
	async function deleteCourseById(id) {
		var requestData = {
			id: id

		};
		console.table(requestData)

		await deleteCourse(requestData)
	}

	//Data Table
	$('#courseTable').on('click', '.btn-info', async function(event) {
		console.log("Hi");
		const courseId = $(this).data('id');
		editCourseById(courseId);
	});

	$('#courseTable').on('click', '.btn-danger', async function(event) {
		console.log("Hi");
		const result = await showYesNoModal();

		if (result.isConfirmed) {
			const courseId = $(this).data('id');
			deleteCourseById(courseId);
		}

	});


	async function appendCourseTable() {
		var table = new DataTable('#courseTable', {
			paging: true
		});
		try {
			const response = await viewCourse();
			if (response.ok) {
				const courseData = await response.json();//change backenddata into json object
				var count = 0;
				table.clear();

				courseData.forEach(course => {
					count++;
					let deleteButton = `<button type="submit" class="btn btn-danger mb-3" data-id="${course.id}">Delete</button>`;
					const $deleteButton = $(deleteButton);//JQuery Object
					let editButton = `<button type="submit" class="btn btn-info mb-3" data-id="${course.id}">Edit</button>`;
					const $editButton = $(editButton);//JQuery Object

					table.row.add([
						`<div style="text-align: center;">${count}</div>`,
						course.code,
						course.name,

						$editButton.prop('outerHTML') + ` ` + $deleteButton.prop('outerHTML') // Convert jQuery object back to HTML string
					]).draw(false);
				});
			} else {
				console.error('Failed to fetch Course data:', response.statusText);
			}
		} catch (error) {
			console.error('Error fetching Course data:', error);
		}
	}

	//Course

	//View Student
	await fetchStudents();
	async function fetchStudents() {

		if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/viewStudent')) {
			const response = await viewStudent();
			const responseData = response.json();
			console.table(responseData);
			appendStudentTable();

		}
	}

	//Edit Student 


	async function editStudentById(id) {
		var requestData = {
			id: id

		};
		console.table(requestData)
		const response = await getStudentById(requestData)
		console.log(response);
		localStorage.setItem('editStudentData', JSON.stringify(response));
		window.location.href = "http://localhost:8080/Student_MVC_JDBC/editStudent"
	}
	if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/editStudent')) {
		const editCourseBtn = $("#editCourseBtn");
		console.log(editCourseBtn);
		const editedCourseCode = $("#editedCourseCode");
		console.log(editedCourseCode);
		const editedCourseName = $("#editedCourseName");
		console.log(editedCourseName);
		const editCourseData = JSON.parse(localStorage.getItem('editCourseData'));
		console.log(editCourseData);
		if (editCourseData !== null) {
			editedCourseCode.val(editCourseData.code);
			editedCourseName.val(editCourseData.name)
		}
		editCourseBtn.on("click", async function(event) {
			event.preventDefault()
			var code = editedCourseCode.val();
			var name = editedCourseName.val();

			if (!name) {


				if (!name) {
					$("#editedCourseNameError").text("Name is required.");
				} if (name) {
					$("#editedCourseNameError").text("");

					return;
				}

			}

			$("#editedCourseNameError").text("");


			var requestData = {
				id: editCourseData.id,
				code: code,
				name: name

			};
			console.table(requestData)
			await editCourse(requestData)
		});


	}


	//Delete Student
	async function deleteStudentById(id) {
		var requestData = {
			id: id

		};
		console.table(requestData)
		await deleteStudent(requestData)
	}

	//Data Table
	$('#studentTable').on('click', '.btn-info', async function(event) {
		console.log("Hi");
		const studentId = $(this).data('id');
		editStudentById(studentId);
	});

	$('#studentTable').on('click', '.btn-danger', async function(event) {
		console.log("Hi");
		const result = await showYesNoModal();

		if (result.isConfirmed) {
			const studentId = $(this).data('id');
			deleteStudentById(studentId);
		}
	});

	async function appendStudentTable() {
		var table = new DataTable('#studentTable', {
			paging: true
		});
		try {
			const response = await viewStudent();
			if (response.ok) {
				const studentData = await response.json();//change backenddata into json object
			
				var count = 0;
				table.clear();

				studentData.forEach(student => {
					count++;
					let deleteButton = `<button type="submit" class="btn btn-danger mb-3" data-id="${student.id}">Delete</button>`;
					const $deleteButton = $(deleteButton);//JQuery Object
					let editButton = `<button type="submit" class="btn btn-info mb-3" data-id="${student.id}">Edit</button>`;
					const $editButton = $(editButton);//JQuery Object
					const coursesName = [];
					const coursesNames = student.courses;
					coursesNames.forEach(course => {
						const nameCourses = course.name;
						coursesName.push(nameCourses);
					});
				

					table.row.add([
						`<div style="text-align: center;">${count}</div>`,
						student.code,
						student.name,
						student.dob,
						student.gender,
						student.phone,
						student.education,
						//student.photo,
						` <img src="data:image/jpeg;base64,${student.photo}" style="width: 50px;" >`,
						coursesName,


						$editButton.prop('outerHTML') + ` ` + $deleteButton.prop('outerHTML') // Convert jQuery object back to HTML string
					]).draw(false);
				});
			} else {
				console.error('Failed to fetch student data:', response.statusText);
			}
		} catch (error) {
			console.error('Error fetching student data:', error);
		}
	}


	//add Student


	if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/addStudent')) {

		try {
			const response = await viewCourse();

			if (response.ok) {
				const addStudentBtn = $("#addStudentBtn");
				const lastStudentCode = await getLastStudentCode();
				const studentCode = $("#studentCode")
				studentCode.val(lastStudentCode.lastCode);
				const studentName = $("#studentName")
				const studentDob = $("#studentDob");
				let studentGender;

				// Assuming studentMaleGender and studentFemaleGender are checkbox elements
				const studentMaleGender = $("#studentMaleGender");
				const studentFemaleGender = $("#studentFemaleGender");

				// Event listener for male gender checkbox
				studentMaleGender.change(function() {
					if (studentMaleGender.is(":checked")) {
						studentGender = "Male";
						console.log("Gender: " + studentGender);
					}
				});

				// Event listener for female gender checkbox
				studentFemaleGender.change(function() {
					if (studentFemaleGender.is(":checked")) {
						studentGender = "Female";
						console.log("Gender: " + studentGender);
					}
				});

				const studentPhone = $("#studentPhone");
				const studentEducation = $("#studentEducation");

				// Array to store selected course IDs
				let selectedCourseIDs = [];


				addStudentBtn.on("click", async function(event) {
					event.preventDefault()
					var code = studentCode.val();
					var name = studentName.val();
					var dob = studentDob.val();
					var gender = studentGender;
					var phone = studentPhone.val();
					var education = studentEducation.val();
					var courseIDs = selectedCourseIDs;




					console.log("Hi686")
					if (!name) {
						console.log("Hi688")
						$("#studentNameError").text("Name is required.");
						return;
					} if (name) {
						console.log("Hi691")
						$("#studentNameError").text("");

					}
					if (!dob) {
						$("#studentDobError").text("Dob is required.");
						return;
					} if (dob) {
						$("#studentDobError").text("");

					}
					if (!gender) {
						$("#studentGenderError").text("Gender is required.");
						return;
					} if (gender) {
						$("#studentGenderError").text("");


					}
					if (!phone) {
						$("#studentPhoneError").text("Phone is required.");
						return;
					} if (phone) {
						$("#studentPhoneError").text("");

					}
					if (!education) {
						$("#studentEducationError").text("Education is required.");
						return;
					} if (education) {
						$("#studentEducationError").text("");


					}
					if (courseIDs.length === 0) {
						$("#studentCourseError").text("Courses is required.");
						return;
					} if (courseIDs.length > 0) {
						$("#studentCourseError").text("");


					}



					$("#studentNameError").text("");
					$("#studentDobError").text("");
					$("#studentGenderError").text("");
					$("#studentPhoneError").text("");
					$("#studentEducationError").text("");

					var requestData = {
						code: code,
						name: name,
						dob: dob,
						gender: gender,
						phone: phone,
						education: education,
						courseIDs: courseIDs,


					};
					console.table(requestData)
					var formData = new FormData();
					formData.append('code', code);
					formData.append('name', name);
					formData.append('dob', dob);
					formData.append('gender', gender);
					formData.append('phone', phone);
					formData.append('education', education);
					formData.append('courseIDs', courseIDs);
					formData.append('studentImage', $("#studentPhoto")[0].files[0]);
					$.ajax({
						url: 'http://localhost:8080/Student_MVC_JDBC/api/student/addStudent',
						type: 'POST',
						data: formData,
						processData: false,
						contentType: false,
						success: function(response) {
							console.log("Successful : ", response);

							Swal.fire({
								title: "Success!",
								text: "Student Alert Successfully",
								icon: "success",
								timer: 1000,
								timerProgressBar: true,
								showConfirmButton: false
							}).then(() => {
								window.location.href = 'http://localhost:8080/Student_MVC_JDBC/viewStudent';
							});
						},
						error: function(error) {
							console.error('Error:', error);
						}
					});

				});




				const courseData = await response.json(); // Change backend data into a JSON object
				console.table(courseData);
				const checkboxContainer = document.getElementById('showCourseID'); // Get the container by ID

				courseData.forEach(course => {
					const checkboxDiv = document.createElement('div');
					checkboxDiv.classList.add('form-check-inline', 'col-md-2');

					const checkboxInput = document.createElement('input');
					checkboxInput.classList.add('form-check-input');
					checkboxInput.type = 'checkbox';
					checkboxInput.name = 'courseID';
					checkboxInput.value = course.id; // Set value to course ID
					checkboxInput.id = `course_${course.id}`; // Unique ID for each checkbox

					const checkboxLabel = document.createElement('label');
					checkboxLabel.classList.add('form-check-label');
					checkboxLabel.htmlFor = `course_${course.id}`; // Matching label with input ID
					checkboxLabel.textContent = course.name; // Display course name

					checkboxDiv.appendChild(checkboxInput);
					checkboxDiv.appendChild(checkboxLabel);
					checkboxContainer.appendChild(checkboxDiv);

					// Add click event listener to each checkbox
					checkboxInput.addEventListener('change', function() {
						if (this.checked) {
							// Add course ID to the array if checkbox is checked
							selectedCourseIDs.push(course.id);
						} else {
							// Remove course ID from the array if checkbox is unchecked
							selectedCourseIDs = selectedCourseIDs.filter(id => id !== course.id);
						}
						// Log the updated array of selected course IDs
						console.log("Selected course IDs: ", selectedCourseIDs);
					});
				});

			}
		} catch (error) {
			console.error('Error fetching Course data:', error);
		}
	}



	//edit Student


	if (window.location.href.includes('http://localhost:8080/Student_MVC_JDBC/editStudent')) {

		try {
			const response = await viewCourse();

			if (response.ok) {

				const editStudentBtn = $("#editStudentBtn");
				const editedStudentCode = $("#editedStudentCode")
				const editedStudentName = $("#editedStudentName")
				const editedStudentDob = $("#editedStudentDob")


				const editedStudentPhone = $("#editedStudentPhone")
				const editedStudentEducation = $("#editedStudentEducation")
				let studentGender;

				// Assuming studentMaleGender and studentFemaleGender are checkbox elements
				const studentMaleGender = $("#studentMaleGender");
				const studentFemaleGender = $("#studentFemaleGender");

				// Event listener for male gender checkbox
				studentMaleGender.change(function() {
					if (studentMaleGender.is(":checked")) {
						studentGender = "Male";
						console.log("Gender: " + studentGender);
					}
				});

				// Event listener for female gender checkbox
				studentFemaleGender.change(function() {
					if (studentFemaleGender.is(":checked")) {
						studentGender = "Female";
						console.log("Gender: " + studentGender);
					}
				});



				const editStudentData = JSON.parse(localStorage.getItem('editStudentData'));

				const editedImageOutput = $("#editedImageOutput");
				var editedImageOutputUrl = 'data:image/jpeg;base64,' + editStudentData.photo;
				editedImageOutput.attr("src", editedImageOutputUrl);
				console.log(editStudentData);
				if (editStudentData !== null) {
					editedStudentCode.val(editStudentData.code);
					editedStudentName.val(editStudentData.name);
					editedStudentDob.val(editStudentData.dob);
					editedStudentPhone.val(editStudentData.phone);
					editedStudentEducation.val(editStudentData.education);
				}
				const editedStudentMaleGender = $("#editedStudentMaleGender");
				const editedStudentFemaleGender = $("#editedStudentFemaleGender");
				const editStudentDataGender = editStudentData.gender;
				let editedStudentGender;
				if (editStudentDataGender === "Male") {
					editedStudentGender = "Male";
					editedStudentMaleGender.prop("checked", true);
				} else if (editStudentDataGender === "Female") {
					editedStudentGender = "Female";
					editedStudentFemaleGender.prop("checked", true);
				}
				editedStudentMaleGender.change(function() {
					
					if (editedStudentMaleGender.is(":checked")) {
						editedStudentGender = "Male";
						console.log("Gender: " + editedStudentGender);
					}
				});

				// Event listener for female gender checkbox
				editedStudentFemaleGender.change(function() {
					if (editedStudentFemaleGender.is(":checked")) {
						editedStudentGender = "Female";
						console.log("Gender: " + editedStudentGender);
					}
				});

				// Array to store selected course IDs
				let selectedCourseIDs = [];
				let editSelectedCourseIDs = [];
				var editStudentDataCourses = editStudentData.courses;
				console.table(editStudentDataCourses);
				editStudentDataCourses.forEach(editStudentDataCourse => {
					editSelectedCourseIDs.push(editStudentDataCourse.id);
				})
				console.log(editSelectedCourseIDs);
				const courseData = await response.json(); // Change backend data into a JSON object
				console.table(courseData);
				const checkboxContainer = document.getElementById('editedShowCourseID');

				//
				courseData.forEach(course => {
					const checkboxDiv = document.createElement('div');
					checkboxDiv.classList.add('form-check-inline', 'col-md-2');

					const checkboxInput = document.createElement('input');
					checkboxInput.classList.add('form-check-input');
					checkboxInput.type = 'checkbox';
					checkboxInput.name = 'courseID';
					checkboxInput.value = course.id; // Set value to course ID
					checkboxInput.id = `course_${course.id}`; // Unique ID for each checkbox

					const checkboxLabel = document.createElement('label');
					checkboxLabel.classList.add('form-check-label');
					checkboxLabel.htmlFor = `course_${course.id}`; // Matching label with input ID
					checkboxLabel.textContent = course.name; // Display course name

					// Check if the current course ID is in editStudentDataCourses
					if (editSelectedCourseIDs.includes(course.id)) {

						checkboxInput.checked = true;
						// If the checkbox is checked, add the course ID to selectedCourseIDs array
						selectedCourseIDs.push(course.id);
						console.table(selectedCourseIDs);
					}

					checkboxDiv.appendChild(checkboxInput);
					checkboxDiv.appendChild(checkboxLabel);
					checkboxContainer.appendChild(checkboxDiv);

					// Add click event listener to each checkbox
					checkboxInput.addEventListener('change', function() {
						if (this.checked) {
							// Add course ID to the array if checkbox is checked
							selectedCourseIDs.push(course.id);
						} else {
							// Remove course ID from the array if checkbox is unchecked
							selectedCourseIDs = selectedCourseIDs.filter(id => id !== course.id);
						}
						// Log the updated array of selected course IDs
						console.log("Selected course IDs: ", selectedCourseIDs);
					});
				});


				//


				editStudentBtn.on("click", async function(event) {
					event.preventDefault()
					const result = await showYesNoModal2();

					if (result.isConfirmed) {

						var code = editedStudentCode.val();
						var name = editedStudentName.val();
						var dob = editedStudentDob.val();
						var gender = editedStudentGender;
						var phone = editedStudentPhone.val();
						var education = editedStudentEducation.val();
						var courseIDs = selectedCourseIDs;

						if (!name) {


							if (!name) {
								$("#editedStudentNameError").text("Name is required.");
							} if (name) {
								$("#editedStudentNameError").text("");
								return;
							}
							if (!dob) {
								$("#editedStudentDobError").text("Dob is required.");
							} if (dob) {
								$("#editedStudentDobError").text("");
								return;
							}
							if (!gender) {
								$("#editedStudentGenderError").text("Gender is required.");
							} if (gender) {
								$("#editedStudentGenderError").text("");
								return;
							}
							if (!phone) {
								$("#editedStudentPhoneError").text("Phone is required.");
							} if (phone) {
								$("#editedStudentPhoneError").text("");
								return;
							}
							if (!education) {
								$("#editedStudentEducationError").text("Education is required.");
							} if (education) {
								$("#editedStudentEducationError").text("");
								return;
							}


						}

						$("#editedStudentNameError").text("");
						$("#editedStudentDobError").text("");
						$("#editedStudentGenderError").text("");
						$("#editedStudentPhoneError").text("");
						$("#editedStudentEducationError").text("");

						var requestData = {
							code: code,
							name: name,
							dob: dob,
							gender: gender,
							phone: phone,
							education: education,
							courseIDs: courseIDs,


						};
						console.table(requestData)
						var formData = new FormData();
						formData.append('id', editStudentData.id);
						formData.append('code', code);
						formData.append('name', name);
						formData.append('dob', dob);
						formData.append('gender', gender);
						formData.append('phone', phone);
						formData.append('education', education);
						formData.append('courseIDs', courseIDs);
						formData.append('studentImage', $("#editedStudentPhoto")[0].files[0]);
						$.ajax({
							url: 'http://localhost:8080/Student_MVC_JDBC/api/student/editStudent',
							type: 'POST',
							data: formData,
							processData: false,
							contentType: false,
							success: function(response) {
								console.log("Successful : ", response);

								window.location.href = 'http://localhost:8080/Student_MVC_JDBC/viewStudent';
							},
							error: function(error) {
								console.error('Error:', error);
							}
						});
					}

				});

			}
		} catch (error) {
			console.error('Error fetching Course data:', error);
		}
	}


	async function showYesNoModal() {
		const result = await Swal.fire({
			title: 'Are you sure?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Yes, delete it!'
		});
		return result;
	}
	async function showYesNoModal2() {
		const result = await Swal.fire({
			title: 'Are you sure?',
			text: "You won't be able to revert this!",
			icon: 'warning',
			showCancelButton: true,
			confirmButtonColor: '#3085d6',
			cancelButtonColor: '#d33',
			confirmButtonText: 'Confirm' // Updated confirm button text
		});
		return result;
	}



	const logoutButton = $("#logoutButton");
	logoutButton.on("click", async function(event) {
		
	

		
		localStorage.removeItem('userEmail');
		localStorage.removeItem('userRole');
		window.location.href = "http://localhost:8080/Student_MVC_JDBC/login"
		
	});
	
		
});

