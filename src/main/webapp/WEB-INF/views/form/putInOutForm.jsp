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

<title>Pay in/out cash</title>
</head>
<body>

	<h1>Pay in/out cash</h1>

	<h3>Update your budget</h3>

	<div class="left">
		<img src="http://icons-for-free.com/free-icons/png/512/1871412.png"
			alt="error" />
	</div>

	<div class="container">
		<form:form class="signUp" modelAttribute="putInOut" method="post"
			action="/BudgetPlanner/putInOut/form">

			<h3>Pay in:</h3>

			<form:input path="putIn" placeholder="Insert sum to pay in" />
			</br>
			<form:errors path="putIn" cssStyle="color:red"></form:errors>
			</br>

			<button class="form-btn dx" type="submit">OK</button>
			<button class="form-btn sx log-in" type="button" id="log">Pay
				out</button>
		</form:form>
		<form:form class="signIn" modelAttribute="putInOut" method="post"
			action="/BudgetPlanner/putInOut/form">
			<h3>Pay out:</h3>

			<form:input path="putOut" placeholder="Insert sum to pay out" />
			</br>
			<form:errors path="putOut" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx back" type="button">Back</button>
			<button class="form-btn dx" type="submit">OK</button>
		</form:form>
	</div>


	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/calendar"> Calendar</a></li>
				<li><a href="/BudgetPlanner/balance">Balance</a></li>
				<li><a href="/BudgetPlanner/saveUp/add"> Save-up </a></li>
				<li><a href="/BudgetPlanner/shoppingList"> Shopping list</a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>
</body>
</html>