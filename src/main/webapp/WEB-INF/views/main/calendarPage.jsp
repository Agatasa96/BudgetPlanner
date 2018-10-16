<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/calendarStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/calendar.js"></script>
<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/buttonStyle.css" type="text/css">

<link rel="stylesheet"
	href="/BudgetPlanner/resources/style/menuStyle.css" type="text/css">
<script src="/BudgetPlanner/resources/js/menu.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<title>Calendar</title>
</head>
<body>

	<script>
		//wypelnenie tabeli miesiaca naglowkami

		function day_title(day_name) {
			document.write("<div class='c-cal__col'>" + day_name + "</div>");
		}
		//wypelnenie tabeli miesiaca liczbami

		function fill_table(month, month_length, indexMonth) {
			day = 1;
			// tabela nowego mies
			document.write("<div class='c-main c-main-" + indexMonth + "'>");

			// naglowki kolumn
			document.write("<div class='c-cal__row'>");
			day_title("Sun");
			day_title("Mon");
			day_title("Tue");
			day_title("Wed");
			day_title("Thu");
			day_title("Fri");
			day_title("Sat");
			document.write("</div>");

			// podkladka przed 1 dniem mies
			document.write("<div class='c-cal__row'>");
			for (var i = 1; i < start_day; i++) {
				if (start_day > 7) {
				} else {
					document.write("<div class='c-cal__cel'></div>");
				}
			}

			//wypelnienie pierwszego tyg
			for (var i = start_day; i < 8; i++) {

				document
						.write("<div data-day='2018-" +
          indexMonth +
          "-0" +
          day +
          "'class='c-cal__cel'><p><a href=\"\/BudgetPlanner/calendar/"
								+ indexMonth
								+ "/0"
								+ day
								+ "\"<\/>"
								+ day
								+ "</a></p></div>");
				day++;
			}
			document.write("</div>");

			// wypelnienie pozostalych tyg
			while (day <= month_length) {
				document.write("<div class='c-cal__row'>");
				for (var i = 1; i <= 7 && day <= month_length; i++) {
					if (day >= 1 && day <= 9) {
						document
								.write("<div data-day='2018-" +
              indexMonth +
              "-0" +
              day +
              "'class='c-cal__cel'><p><a href=\"\/BudgetPlanner/calendar/"
										+ indexMonth + "/" + day + "\"<\/>"

										+ day + "</p></div>");
						day++;
					} else {
						document
								.write("<div data-day='2018-" +
              indexMonth +
              "-" +
              day +
              "' class='c-cal__cel'><p><a href=\"\/BudgetPlanner/calendar/"
										+ indexMonth + "/" + day + "\"<\/>"

										+ day + "</p></div>");
						day++;
					}
				}
				document.write("</div>");
				// pierwszy dzien kolejnego mies
				start_day = i;
			}

			document.write("</div>");
		}
	</script>
	<header>
		<div class="wrapper">
			<div class="c-monthyear">
				<div class="c-month">
					<span id="prev" class="prev fa fa-angle-left button_main"
						aria-hidden="true">PREV</span>
					<div id="c-paginator">
						<span class="c-paginator__month">JANUARY</span> <span
							class="c-paginator__month">FEBRUARY</span> <span
							class="c-paginator__month">MARCH</span> <span
							class="c-paginator__month">APRIL</span> <span
							class="c-paginator__month">MAY</span> <span
							class="c-paginator__month">JUNE</span> <span
							class="c-paginator__month">JULY</span> <span
							class="c-paginator__month">AUGUST</span> <span
							class="c-paginator__month">SEPTEMBER</span> <span
							class="c-paginator__month">OCTOBER</span> <span
							class="c-paginator__month">NOVEMBER</span> <span
							class="c-paginator__month">DECEMBER</span>
					</div>
					<span id="next" class="next fa fa-angle-right button_main"
						aria-hidden="true">NEXT</span>
				</div>
				<span class="c-paginator__year">2018</span>
			</div>

		</div>

	</header>

	<div class="c-calendar">
		<div class="c-calendar__style c-aside">

			<div class="c-aside__day">
				<span class="c-aside__num"></span> <span class="c-aside__month"></span>
			</div>
			<div class="c-aside__eventList"></div>

			<div>
				<div class="button_main">
					<p>Click on day to go to balance history</p>
				</div>


			</div>

		</div>


		<div class="c-cal__container c-calendar__style">
			<script>
				// obecny rok
				year = 2018;

				// 1 dzien 1 tyg nowego roku
				today = new Date("January 1, " + year);
				start_day = today.getDay() + 1;
				fill_table("January", 31, "01");
				fill_table("February", 28, "02");
				fill_table("March", 31, "03");
				fill_table("April", 30, "04");
				fill_table("May", 31, "05");
				fill_table("June", 30, "06");
				fill_table("July", 31, "07");
				fill_table("August", 31, "08");
				fill_table("September", 30, "09");
				fill_table("October", 31, "10");
				fill_table("November", 30, "11");
				fill_table("December", 31, "12");
			</script>
		</div>
	</div>
<!-- Menu boczne --> 

	<div class="open">
		<span class="cls"></span> <span>
			<ul class="sub-menu ">
				<li><a href="/BudgetPlanner/main"> Home</a></li>
				<li><a href="/BudgetPlanner/balance"> Balance</a></li>
				<li><a href="/BudgetPlanner/putInOut/form"> Pay in/out cash</a></li>
				<li><a href="/BudgetPlanner/saveUp/add"> Save-up </a></li>
				<li><a href="/BudgetPlanner/">Log out</a></li>
			</ul>
		</span> <span class="cls"></span>
	</div>
 

</body>
</html>