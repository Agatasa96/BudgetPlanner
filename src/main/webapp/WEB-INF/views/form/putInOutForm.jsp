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
	
	<!-- Menu boczne -->
	
	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="" title="about"> Calendar</a></li>
				<li><a href="/BudgetPlanner/balance" >Balance</a></li>
				<li><a href="#about" title="about"> Save-up </a></li>
				<li><a href="#about" title="about"> Shopping list</a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>

</body>
</html>