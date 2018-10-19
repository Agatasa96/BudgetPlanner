<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/balancePageStyle.css"
	type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/buttonStyle.css" type="text/css">

<title>Delete user</title>
</head>
<body>
	<div>
		<h1>Do you want to delete user: ${userDto.nickname }?</h1>
	</div>

	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">

			<p>
				<a href="/BudgetPlanner/user/deleteYes">Yes</a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/user/getUser">No</a>
			</p>
		</div>

	</div>
</body>
</html>