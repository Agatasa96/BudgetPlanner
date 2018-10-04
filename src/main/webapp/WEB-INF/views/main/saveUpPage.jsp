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

<title>Save up</title>
</head>
<body>
	<h1>It's time to save up!</h1>
	<h3>You save up ${savedBalance.saveUp } so far</h3>
	<h3>Total balance: ${savedBalance.totalBalance}</h3>

	<div class="container">
		<form:form modelAttribute="saveUpDto" method="post"
			action="/BudgetPlanner/saveUp/add">
			<p>Insert sum to you want to save up</p>
			<form:input path="toSaveUp" placeholder="Insert sum " />
			</br>
			<form:errors path="toSaveUp" cssStyle="color:red"></form:errors>
			</br>

			<button class="form-btn sx log-in" type="submit" id="log">Done</button>

		</form:form>

	</div>
</body>
</html>