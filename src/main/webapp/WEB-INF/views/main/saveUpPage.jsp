<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/formStyle.css" type="text/css">

<script src="/BudgetPlanner/resources/js/form.js"></script>

<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<title>Save up</title>
</head>
<body>
	<h1>It's time to save up!</h1>
	<h3>Total balance: ${savedBalance.totalBalance} Total save up: ${savedBalance.totalSaved }</h3>

	<div class="container">
		<form:form modelAttribute="saveUpDto" method="post"
			action="/BudgetPlanner/saveUp/add">
			<p>Insert sum to save up</p>
			<form:input path="toSaveUp" placeholder="Insert sum " />
			</br>
			<form:errors path="toSaveUp" cssStyle="color:red"></form:errors>
			</br>
			<button class="form-btn sx log-in" type="submit" id="log">Done</button>

		</form:form>

	</div>
	
	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/calendar"> Calendar</a></li>
				<li><a href="/BudgetPlanner/putInOut/form"> Pay in/out cash</a></li>
				<li><a href="/BudgetPlanner/balance"> Balance </a></li>
				<li><a href="/BudgetPlanner/shoppingList"> Shopping list</a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>
</body>
</html>