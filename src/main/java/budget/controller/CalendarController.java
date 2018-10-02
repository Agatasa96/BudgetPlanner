package budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

	@RequestMapping
	public String calendar() {
		return "main/calendarPage";
	}
}
