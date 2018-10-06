<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit save up</title>
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/formStyle.css" type="text/css">

<script src="/BudgetPlanner/resources/js/form.js"></script>
</head>
<body>
<div>
		<h1>Edit save up</h1>

	
		<div class="container">
			<form:form modelAttribute="editBalance" method="post"
				action="/BudgetPlanner/saveUp/editBalance">
				<p>Insert sum to save up</p>
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