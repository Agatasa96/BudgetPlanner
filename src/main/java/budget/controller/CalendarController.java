package budget.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.dto.UserDto;
import budget.service.BalanceService;

@Controller
@RequestMapping("/calendar")
@SessionAttributes({ "userDto", "savedBalance", "start", "balanceHistory" })

public class CalendarController {

	private final BalanceService balanceService;

	public CalendarController(BalanceService balanceService) {

		this.balanceService = balanceService;
	}

	@RequestMapping
	public String calendar() {
		return "main/calendarPage";
	}

	@GetMapping("/{month}/{day}")
	public String historyByDay(@PathVariable String month, @PathVariable String day,
			@SessionAttribute("userDto") UserDto userDto, Model model) {

		List<Object[]> balances = balanceService.getHistoryByDay(userDto.getId(), month, day);

		model.addAttribute("balanceHistory", balances);
		Integer start = 0;
		model.addAttribute("start", start);
		return "main/balanceHistory";
	}
}
