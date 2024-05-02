<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<title>User Registration</title>
</head>
<jsp:include page="header.jsp"></jsp:include>

<body>


	</div>

	<div class="container">

		<div class="main_contents">
			<div id="sub_content">

				<form>

					<h2 class="col-md-6 offset-md-2 mb-5 mt-4">Admin Register</h2>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="name" class="col-md-2 col-form-label">Name</label>
						<div class="col-md-4">
							<input type="text" class="form-control" id="adminName"></input>
							<p id="adminNameError" style="color: red; padding: 0px;"></p>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="email" class="col-md-2 col-form-label">Email</label>
						<div class="col-md-4">
							<input type="email" class="form-control" id="adminEmail"></input>
							<p id="adminEmailError" style="color: red;"></p>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="Password" class="col-md-2 col-form-label">Password</label>
						<div class="col-md-4">
							<input type="password" class="form-control" id="adminPassword"></input>
							<p id="adminPasswordError" style="color: red;"></p>

						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="confirmPassword" class="col-md-2 col-form-label">ConfirmPassword</label>
						<div class="col-md-4">
							<input type="password" class="form-control" id="adminConfirmPassword"></input>
							<p id="adminConfirmEmailError" style="color: red;"></p>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-md-2"></div>
						<label for="userRole" class="col-md-2 col-form-label">User
							Role</label>
						<div class="col-md-4">
							<input type="text" class="form-control" value="admin" disabled="disabled"></input>
						</div>
					</div>
					<div class="row mb-4">
						<div class="col-md-4"></div>

						<div class="col-md-6">

							<button type="submit" class="btn btn-primary col-md-2" id="addAdminBtn">Add</button>
							
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


