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
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

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

			<p>
				<a href="/BudgetPlanner/user/editNickname">Edit user data</a>
			</p>
		</div>
		<div class="button_main">
			<p>
				<a href="/BudgetPlanner/user/deleteUser">Delete my account</a>
			</p>
		</div>

	</div>

	<!-- Menu boczne -->
	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">

				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>


</body>
</html>