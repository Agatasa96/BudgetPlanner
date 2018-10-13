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
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<title>Edit nickname</title>
</head>
<body>
	<div>
		<h1>Edit user data:</h1>

	</div>
	<div class="left">
		<img src="http://icons-for-free.com/free-icons/png/512/1889196.png"
			alt="error" />
	</div>

<!-- Formularz -->
	<div class="container">
		<form:form class="signUp" modelAttribute="editUserDto" method="post"
			action="/BudgetPlanner/user/editNickname">

			<h3>Edit nickname:</h3>

			<form:input path="nickname" placeholder="Insert nickname" />
			</br>
			<form:errors path="nickname" cssStyle="color:red"></form:errors>
			</br>

			<button class="form-btn dx" type="submit">Edit</button>
			<button class="form-btn sx log-in" type="button" id="log">Edit
				password</button>
		</form:form>
		<form:form class="signIn" method="post" modelAttribute="editUserDto"
			action="/BudgetPlanner/user/editPassword">
			<h3>Edit password:</h3>

			<form:password path="password" placeholder="Insert password" />
			</br>
			<form:errors path="password" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx back" type="button">Back</button>
			<button class="form-btn dx" type="submit">Edit</button>
		</form:form>
	</div>
	
	<!-- Menu boczne -->
	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
			<li><a href="/BudgetPlanner/user/getUser" >My account</a></li>
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/" >Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>
	

</body>
</html>