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

<title>Hello in new month!</title>
</head>
<body>
	<div>
		<h1>Hello in new month!</h1>

		<h3>It's time to update your budget</h3>
		<div class="container">
			<form:form modelAttribute="balanceDto" method="post"
				action="/BudgetPlanner/balance/newMonth">
				<p>Insert sum to add to your balance</p>
				<form:input path="putInMonthly" placeholder="Insert sum " />
				</br>
				<form:errors path="putInMonthly" cssStyle="color:red"></form:errors>
				</br>
				<p>Insert sum which you want to save up</p>
				<form:input path="saveUp" placeholder="Insert sum " />
				</br>
				<form:errors path="saveUp" cssStyle="color:red"></form:errors>
				</br>

				<button class="form-btn sx log-in" type="submit" id="log">Done</button>

			</form:form>

		</div>

	</div>
</body>
</html>