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

<title>Edit nickname</title>
</head>
<body>

	<h1>Edit your nickname</h1>
	<div class="container">
		<form:form modelAttribute="editNickname" method="post"
			action="/BudgetPlanner/user/editNickname">

			<form:input path="nickname" placeholder="Insert new nickname" />
			</br>
			<form:errors path="nickname" cssStyle="color:red"></form:errors>
			</br>


			<button class="form-btn sx log-in" type="submit" id="log">Done</button>

		</form:form>

	</div>


</body>
</html>