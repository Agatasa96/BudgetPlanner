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

<title>Put in/out cash</title>
</head>
<body>
<div>
		<h1>Put in/out cash</h1>

		<h3>Update your budget</h3>
		<div class="container">
			<form:form modelAttribute="putInOut" method="post"
				action="/BudgetPlanner/putInOut/form">
				
				<form:input path="putIn" placeholder="Insert sum to put in" />
				</br>
				<form:errors path="putIn" cssStyle="color:red"></form:errors>
				</br>
				
				<form:input path="putOut" placeholder="Insert sum to put out" />
				</br>
				<form:errors path="putOut" cssStyle="color:red"></form:errors>
				</br>

				<button class="form-btn sx log-in" type="submit" id="log">Done</button>

			</form:form>

		</div>

	</div>
</body>
</html>