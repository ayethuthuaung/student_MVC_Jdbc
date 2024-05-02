<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>         
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


    <!-- <div id="testsidebar">Hello World </div> -->
    <div class="container">
    
    <div id="sub_content">
	<form action="addCourse" method="post">
        <h2 class="col-md-6 offset-md-2 mb-5 mt-4">Update Course</h2>
        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="code" class="col-md-2 col-form-label"> Code</label>
            <div class="col-md-4">
                <input type="text" class="form-control" name="courseCode" id="editedCourseCode" readonly="readonly">
                
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-2"></div>
            <label for="name" class="col-md-2 col-form-label">Name</label>
            <div class="col-md-4">
                <input type="text" class="form-control" name="name" id="editedCourseName" >
                <p id="editedCourseNameError" style="color: red; padding: 0px;"></p>
            </div>
        </div>
      
       
        <div class="row mb-4">
            <div class="col-md-4"></div>

            <div class="col-md-6">
               

                <button type="submit" class="btn btn-secondary col-md-2"  id="editCourseBtn">Edit</button>
                

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
            
          //Ajax
    		document.getElementById('name').addEventListener('keyup', async function(event) {
    		    console.log("Hi");
    		    let name = event.target.value;

    		    try {
    		        const response = await fetch('/Student/checkCourseName?name=' + name, {
    		            method: 'POST'
    		        });

    		        if (response.ok) {
    	                const errorText = await response.text();
    	                console.log("ok");
    	                console.log(errorText);
    	                document.getElementById('nameError').innerText = errorText;
    	            } else {
    	            	 console.log("Not OK");
    	                document.getElementById('nameError').innerText = "";
    	            }
    		    } catch (error) {
    		        console.error("Error:", error);
    		        // Handle network errors
    		    }
    		});
 </script>
</body>

</html>