<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.0.1/css/dataTables.bootstrap5.css">

</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>

	</div>

	<!-- <div id="testsidebar">Hello World </div> -->
	<div class="container">

		<div class="main_contents">
			<div id="sub_content" style="width: 80%;">


				<table class="table table-striped" id="userTable">
					<thead>
						<tr>
							<th style="text-align: center;" scope="col">No</th>
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">Role</th>
				<th scope="col">Details</th>
            

						</tr>
					</thead>
					<tbody>
					
<!-- 							<tr>
								<td style="text-align: center;">${index.count}</td>
								<td>${user.name}</td>
								<td>${user.email}</td>
								<td>${user.role}</td>
								<td>
                   <a href="/Student/updateUser?id=${user.id}">
                   <button type="button" class="btn btn-primary">Update</button>
                   </a>
                </td>
               
                
                <td> <button type="submit" class="btn btn-secondary mb-3" data-bs-toggle="modal"
                    data-bs-target="#exampleModal" onclick="location.href='/Student/deleteUser?id=${user.id}'">
                    Delete</button></td>
    
                </tr>   -->


					</tbody>
				</table>

				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog modal-dialog-centered">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Student
									Deletion</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">

								<h5 style="color: rgb(127, 209, 131);">Are you sure want to
									delete !</h5>
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
	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
	<script src="https://cdn.datatables.net/2.0.1/js/dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/2.0.1/js/dataTables.bootstrap5.js"></script>

</body>

</html>

