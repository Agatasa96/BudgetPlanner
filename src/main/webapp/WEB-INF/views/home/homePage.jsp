<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/homePageStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/form.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>BUDGET PLANNER</title>
</head>
<body>
	<div>
		<h1>Welcome in your BUDGET PLANNER application!</h1>

	</div>
	<div class="left">
		<img src="https://image.flaticon.com/icons/svg/781/781760.svg"
			alt="error" />
	</div>

	<div class="container">
		<form:form class="signUp" modelAttribute="userDto" method="post"
			action="/BudgetPlanner/user/signUp">

			<h3>Create Your Account</h3>

			<p>
				Log in or Enter your email address</br> your password and nickname for
				join.
			</p>


			<form:input path="email" placeholder="Insert email"  />
			</br>
			<form:errors path="email" cssStyle="color:red"></form:errors>
			</br>
			<form:password path="password" placeholder="Insert password" />
			</br>
			<form:errors path="password" cssStyle="color:red"></form:errors>
			</br>
			<form:input path="nickname" placeholder="Insert nickname" />
			</br>
			<form:errors path="nickname" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx log-in" type="button" id="log">Log
				In</button>
			<button class="form-btn dx" type="submit">Sign Up</button>
		</form:form>
		<form:form class="signIn" method="post" modelAttribute="userDto"
			action="/BudgetPlanner/user/logIn">
			<h3>
				Welcome</br>Back !
			</h3>
			<p>
				Enter your email address</br> and your password.
			</p>
			<form:input path="email" placeholder="Insert email" /></br>
			<form:errors path="email" cssStyle="color:red"></form:errors></br>
			<form:password path="password" placeholder="Insert password" /></br>
			<form:errors path="password" cssStyle="color:red"></form:errors></br>
			<button class="form-btn sx back" type="button">Back</button>
			<button class="form-btn dx" type="submit">Log In</button>
		</form:form>
	</div>

</body>
</html>