<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/mainPageStyle.css" type="text/css">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


<title>BUDGET PLANNER</title>
</head>
<body>


	<div>
		<h1>Hello ${userDto.nickname}!</h1>
	</div>


	<div class="main">
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781740.png">
			<div class="lay">
				<h3>Calendar</h3>
				<p>
					<a href="#">CLICK</a>
				</p>
			</div>
		</div>
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781714.png">
			<div class="lay">
				<h3>Balance</h3>
				<p>
					<a href="/BudgetPlanner/balance">CLICK</a>
				</p>
			</div>
		</div>
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781754.png">
			<div class="lay">
				<h3>Pay in/out cash</h3>
				<p>
					<a href="/BudgetPlanner/putInOut/form">CLICK</a>
				</p>
			</div>
		</div>
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781764.png">
			<div class="lay">
				<h3>Save up</h3>
				<p>
					<a href="#">CLICK</a>
				</p>
			</div>
		</div>
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781720.png">
			<div class="lay">
				<h3>Shopping list</h3>
				<p>
					<a href="#">CLICK</a>
				</p>
			</div>
		</div>
		<div class="hovereffect-4">
			<img alt="" class="img-responsive"
				src="https://image.flaticon.com/icons/png/512/781/781737.png">
			<div class="lay">
				<h3>Log out</h3>
				<p>
					<a href="/BudgetPlanner/">CLICK</a>
				</p>
			</div>
		</div>
	</div>

	<!-- Menu boczne -->

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="#about" title="about"> Help</a></li>
				<li><a href="/BudgetPlanner/" >Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>


</body>
</html>