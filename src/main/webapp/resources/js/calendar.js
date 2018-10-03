document.addEventListener("DOMContentLoaded", function() {

	// global variables
	var monthEl = $(".c-main");
	var dataCel = $(".c-cal__cel");
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1;
	var day = dateObj.getUTCDate();
	var year = dateObj.getUTCFullYear();
	var monthText = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var indexMonth = month;
	var todayBtn = $(".c-today__btn");
	
	today = year + "-" + month + "-" + day;

	

	// ------ functions control -------

	// button of the current day
	todayBtn.on("click", function() {
		if (month < indexMonth) {
			var step = indexMonth % month;
			movePrev(step, true);
		} else if (month > indexMonth) {
			var step = month - indexMonth;
			moveNext(step, true);
		}
	});

	// higlight the cel of current day
	dataCel.each(function() {
		if ($(this).data("day") === today) {
			$(this).addClass("isToday");
			fillEventSidebar($(this));
		}
	});

	
	// fill sidebar event info
	function fillEventSidebar(self) {

	}
	;
	dataCel.on("mouseover", function() {
		var thisEl = $(this);
		var thisDay = $(this).attr("data-day").slice(8);
		var thisMonth = $(this).attr("data-day").slice(5, 7);
		fillEventSidebar($(this));
		 console.log(this);
		$(".c-aside__num").text(thisDay);
		$(".c-aside__month").text(monthText[thisMonth - 1]);

		dataCel.removeClass("isSelected");
		thisEl.addClass("isSelected");

	});

	// function for move the months
	function moveNext(fakeClick, indexNext) {
		for (var i = 0; i < fakeClick; i++) {
			$(".c-main").css({
				left : "-=100%"
			});
			$(".c-paginator__month").css({
				left : "-=100%"
			});
			switch (true) {
			case indexNext:
				indexMonth += 1;
				break;
			}
		}
	}
	function movePrev(fakeClick, indexPrev) {
		for (var i = 0; i < fakeClick; i++) {
			$(".c-main").css({
				left : "+=100%"
			});
			$(".c-paginator__month").css({
				left : "+=100%"
			});
			switch (true) {
			case indexPrev:
				indexMonth -= 1;
				break;
			}
		}
	}

	// months paginator
	function buttonsPaginator(buttonId, mainClass, monthClass, next, prev) {
		switch (true) {
		case next:
			$(buttonId).on("click", function() {
				if (indexMonth >= 2) {
					$(mainClass).css({
						left : "+=100%"
					});
					$(monthClass).css({
						left : "+=100%"
					});
					indexMonth -= 1;
				}
				return indexMonth;
			});
			break;
		case prev:
			$(buttonId).on("click", function() {
				if (indexMonth <= 11) {
					$(mainClass).css({
						left : "-=100%"
					});
					$(monthClass).css({
						left : "-=100%"
					});
					indexMonth += 1;
				}
				return indexMonth;
			});
			break;
		}
	}

	buttonsPaginator("#next", monthEl, ".c-paginator__month", false, true);
	buttonsPaginator("#prev", monthEl, ".c-paginator__month", true, false);

	// launch function to set the current month
	moveNext(indexMonth - 1, false);

	// fill the sidebar with current day
	$(".c-aside__num").text(day);
	$(".c-aside__month").text(monthText[month - 1]);

});
