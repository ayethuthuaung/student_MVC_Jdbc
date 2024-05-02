<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="util.common"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="<%=common.url%>assets/js/jquery.js"></script>
<script src="<%=common.url%>assets/js/ajax.js"></script>
<script src="<%=common.url%>assets/js/ajaxUtils.js"></script>
<script src="<%=common.url%>assets/js/script.js"></script>
<title>User Login</title>
<style>
body {
	overflow-y: hidden;
}

.container {
	border: solid 1px #8d8d8d;
	padding: 20px;
	border-radius: 20px;
	background-color: #f6fffd;
	width: 350px;
	margin: 150px auto;
}

.container .heading {
	font-size: 1.3rem;

	font-weight: bolder;
}

.login-form {
	max-width: 320px;
	display: flex;
	flex-direction: column;
	row-gap: 20px;
	
}

.login-form .btn-container {
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
	gap: 50px;
}

.login-form .btn {
	padding: 10px 20px;
	font-size: 1rem;
	text-transform: uppercase;
	letter-spacing: 3px;
	border-radius: 10px;
	border: solid 1px #1034aa;
	border-bottom: solid 1px #90c2ff;
	background: linear-gradient(135deg, #0034de, #006eff);
	color: white;
	font-weight: bolder;
	transition: all 0.2s ease;
	box-shadow: 0px 2px 3px #000d3848, inset 0px 4px 5px #0070f0, inset 0px
		-4px 5px #002cbb;
}

.login-form .btn:active {
	box-shadow: inset 0px 4px 5px #0070f0, inset 0px -4px 5px #002cbb;
	transform: scale(0.995);
}

.input-field {
	position: relative;
}

.input-field label {
	position: absolute;
	color: #8d8d8d;
	pointer-events: none;
	background-color: transparent;
	left: 15px;
	top: -5px;
	transform: translateY(0.6rem);
	transition: all 0.3s ease;
}

.input-field input {
	padding: 10px 15px;
	font-size: 1rem;
	border-radius: 8px;
	border: solid 1px #8d8d8d;
	letter-spacing: 1px;
	width: 100%;
}

.input-field input:focus, .input-field input:valid {
	outline: none;
	border: solid 1px #0034de;
}

.input-field input:focus ~ label, .input-field input:valid ~ label {
	transform: translateY(-51%) translateX(-10px) scale(0.8);
	background-color: #fff;
	padding: 0px 5px;
	color: #0034de;
	font-weight: bold;
	letter-spacing: 1px;
	border: none;
	border-radius: 100px;
}

.login-form .passicon {
	cursor: pointer;
	font-size: 1.3rem;
	position: absolute;
	top: 6px;
	right: 8px;
}

.login-form .close {
	display: none;
}
</style>
</head>
<body>
	<div class="container">
		<h1>Hello</h1>
		<form class="login-form" method="post">
			<div class="heading">Login</div>
			<p id="loginError" style="color: red; margin-bottom: 0px;"></p>
			<div class="input-field">

				<input required="required" autocomplete="off" type="email" name="email"
					id="userEmail" /> <label for="email">Email</label>
					<p id="loginEmailError" style="color: red; margin-bottom: 0px;"></p>
			</div>

			<div class="input-field">
				<input required="required" autocomplete="off" type="password"
					name="password" id="userPassword" /> <label for="password">Password</label>
					<p id="loginPasswordError" style="color: red; margin-bottom: 0px;"></p>
			</div>
			<!-- <div class="remember-forgot-box">
				<label for="remember"> <input type="checkbox" id="remember">
					Remember me
				</label> <a href="#">Forgot Password?</a>
			</div> -->

			<div class="btn-container">
				<button class="btn" id="login-btn" type="submit">login</button>
				<span class="register">
				Don't have an account? <a href="/Student_MVC_JDBC/register">Register</a>
			</span>

			</div>
			


		</form>
	</div>

</body>
</html>

