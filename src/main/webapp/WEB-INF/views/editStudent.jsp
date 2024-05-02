<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="test.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  


</head>

<body>
 
	   <jsp:include page="header.jsp"></jsp:include>


	
		<div class="main_contents">
			<div id="sub_content">
				<form enctype="multipart/form-data">

					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Student
						Registration</h2>
						
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="code" class="col-md-2 col-form-label">Student ID</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="code" id="editedStudentCode" readonly="readonly"/>
						</div>
					</div>
					
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="name" id="editedStudentName"/>
							 <p id="editedStudentNameError" style="color: red; padding: 0px;"></p>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="dob" class="col-md-2 col-form-label">DOB</label>
						<div class="col-md-4">
							<input type="date" class="form-control" name="dob" id="editedStudentDob"/>
							<p id="editedStudentDobError" style="color: red; padding: 0px;"></p>
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Gender</legend>
						<div class="col-md-4">
							<div class="form-check-inline">
								<input type="radio" name="gender" value="Male" id="editedStudentMaleGender"/>
								<span>Male</span>
								
							</div>
							<div class="form-check-inline">
								<input type="radio" name="gender" value="Female" id="editedStudentFemaleGender" />
								<span>Female</span>
								
							</div>
							<p id="editedStudentGenderError" style="color: red; padding: 0px;"></p>
						</div>
					</fieldset>


					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="phone" class="col-md-2 col-form-label">Phone</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="phone" id="editedStudentPhone"></input>
							<p id="editedStudentPhoneError" style="color: red; padding: 0px;"></p>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-2"></div>
						<label for="education" class="col-md-2 col-form-label">Education</label>
						<div class="col-md-4">
						<input type="text" class="form-control" name="education" id="editedStudentEducation"></input>
						<p id="editedStudentEducationError" style="color: red; padding: 0px;"></p>
							
						</div>
					</div>
					<fieldset class="row mb-4">
						<div class="col-md-2"></div>
						<legend class="col-form-label col-md-2 pt-0">Attend</legend>
						
						<div class="col-md-4" id="editedShowCourseID">
						 
							
					</div><br>
					
					<div class="row mb-4">
    <div class="col-md-2"></div>
    <label for="name" class="col-md-2 col-form-label">Photo</label><br><br>
    <img id="editedImageOutput" style="width:150px;"><br><br>
    <div class="col-md-4" style="margin-top: 10px;"> <!-- Adjust the margin-top value as needed -->
        <input type="file" class="form-control" name="photo" id="editedStudentPhoto"></input>
    </div>
</div>

				
					</fieldset>
					<div class="row mb-4">
						<div class="col-md-4"></div>

						<div class="col-md-4">
							<!-- <button type="reset" class="btn btn-danger ">Reset</button>  -->
							<button type="submit" class="btn btn-secondary col-md-2"
								id="editStudentBtn">
								Add</button> 
							<div class="modal fade" id="exampleModal" tabindex="-1"
								aria-labelledby="exampleModalLabel" aria-hidden="true">
								<div class="modal-dialog modal-dialog-centered">
									<div class="modal-content">
										<div class="modal-header">
											<h5 class="modal-title" id="exampleModalLabel">Student
												Registration</h5>
											<button type="button" class="btn-close"
												data-bs-dismiss="modal" aria-label="Close"></button>
										</div>
										<div class="modal-body">
											<h5 style="color: rgb(127, 209, 131);">Registered
												Succesfully !</h5>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-success col-md-2"
												data-bs-dismiss="modal">Ok</button>

										</div>
									</div>
								</div>
							</div>
						</div>


					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="testfooter">
		<span>Copyright &#169; ACE Inspiration 2024</span>
	</div>
	<script>
		/* Loop through all dropdown buttons to toggle between hiding and showing its dropdown content - This allows the user to have multiple dropdowns without any conflict */
		var dropdown = document.getElementsByClassName("dropdown-btn");
		var i;

		for (i = 0; i < dropdown.length; i++) {
			dropdown[i].addEventListener("click", function() {
				this.classList.toggle("active");
				var dropdownContent = this.nextElementSibling;
				if (dropdownContent.style.display === "block") {
					dropdownContent.style.display = "none";
				} else {
					dropdownContent.style.display = "block";
				}
			});
		}
	</script>
</body>

</html>