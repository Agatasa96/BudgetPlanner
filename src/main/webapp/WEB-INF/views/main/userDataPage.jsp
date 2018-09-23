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
	href="/BudgetPlanner/resources/style/tableStyle.css" type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/buttonStyle.css" type="text/css">

<title>User data</title>
</head>
<body>
	<h1>User data:</h1>

	<!-- Table -->

	<div class="container">
		<table>
			<thead>
				<tr>
					<th>Email</th>
					<th>Password</th>
					<th>Nickname</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>${userDto.email }</td>
					<td>${userDto.password }</td>
					<td>${userDto.nickname }</td>
				</tr>
			</tbody>
		</table>
	</div>
	<!-- Buttons -->

	<div class="cont">
		<div class="button_main">
		
			<p><a href="/BudgetPlanner/user/editNickname">Edit nickname</a></p>
		</div>
		<div class="button_main">
			<p>Edit password</p>
		</div>

	</div>
</body>
</html>