<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "util.common"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="<%=common.url%>assets/css/test.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">

 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"/>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="<%=common.url%>assets/js/jquery.js"></script>
<script src="<%=common.url%>assets/js/ajax.js"></script>
<script src="<%=common.url%>assets/js/ajaxUtils.js"></script>
<script src="<%=common.url%>assets/js/script.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
 <div id="testheader">
<div class="container">
	<div class=row>        
		<div class="col-md-5 ">
            <a href="Student_Registeration/MNU001"><h3>Student_Registration</h3></a>
        </div>  
      
        <div class="col-md-6">

    <p id="headerUserEmail"></p>
    <p id="headerDate"></p>

</div>
        
        <div class="col-md-1" >
            <input type="button" class="btn-basic" id="logoutButton" value="Log Out" >
        </div>        
    </div>
</div>
</div>

<div class="sidenav">

        <button class="dropdown-btn hide-to-user" > Course Management <i class="fa fa-caret-down"></i></button>
     
            <div class="dropdown-container">
          <a href="/Student_MVC_JDBC/addCourse">Course Registration </a>
          <a href="/Student_MVC_JDBC/viewCourse">View Course</a>
        </div>
       
        <button class="dropdown-btn hide-to-user" > User Management <i class="fa fa-caret-down"></i></button>
         
         <div class="dropdown-container">
         
          <a href="/Student_MVC_JDBC/addAdmin">Admin Registration </a>
          <a href="/Student_MVC_JDBC/viewUser">View User</a>
        </div>
       
        <button class="dropdown-btn" > Student Management <i class="fa fa-caret-down"></i></button>
           
        <div class="dropdown-container">
          <a href="/Student_MVC_JDBC/addStudent">Student Registration </a>
          <a href="/Student_MVC_JDBC/viewStudent" class="hide-to-user">View Student</a>
          
        </div>
        
       
      </div>
     


</body>
</html>