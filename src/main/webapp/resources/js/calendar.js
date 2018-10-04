document.addEventListener("DOMContentLoaded", function() {

	// globalne
	var monthEl = $(".c-main");
	var dataCel = $(".c-cal__cel");
	var dateObj = new Date();
	var month = dateObj.getUTCMonth() + 1;
	var day = dateObj.getDate();
	var year = dateObj.getUTCFullYear();
	var monthText = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var indexMonth = month;
	var todayBtn = $(".c-today__btn");

	today = year + "-" + month + "-" + day;

	// wyswietlanie daty mouseover
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

	// przesuwanie mies
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

	// ustawianie daty w oknie bocznym
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

	// ustawianie odpowiednich mies przy przewijaniu
	moveNext(indexMonth - 1, false);

	// wyswietlanie daty w oknie bocznym
	$(".c-aside__num").text(day);
	$(".c-aside__month").text(monthText[month - 1]);

});
