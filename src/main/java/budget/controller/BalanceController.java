package budget.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.domain.Balance;
import budget.dto.BalanceDto;

import budget.dto.UserDto;
import budget.service.BalanceService;

@Controller
@RequestMapping("/balance")
@SessionAttributes({ "userDto", "savedBalance", "start" })
public class BalanceController {

	private final BalanceService balanceService;

	public BalanceController(BalanceService balanceService) {

		this.balanceService = balanceService;
	}

	@RequestMapping
	public String balance(@SessionAttribute("userDto") UserDto userDto, Model model) {
		model.addAttribute("userDto", userDto);
		return "main/balancePage";
	}

	@PostMapping("/newMonth")
	public String update(@Valid @ModelAttribute BalanceDto balanceDto, BindingResult bindingResult, Model model,
			@SessionAttribute("userDto") UserDto userDto) {
		if (bindingResult.hasErrors()) {
			return "form/newMonthForm";
		} else {

			balanceDto.setUserDto(userDto);
			BalanceDto savedBalanceDto = balanceService.save(balanceDto);
			model.addAttribute("savedBalance", savedBalanceDto);

			return "main/balancePage";
		}

	}

	@GetMapping("/history")
	public String getBalanceHistory(@SessionAttribute("userDto") UserDto userDto, Model model) {
		List<Object[]> balances = balanceService.getHistory(userDto.getId());
		model.addAttribute("balanceHistory", balances);
		Integer start = 0;
		model.addAttribute("start", start);
		return "main/balanceHistory";
	}

	@PostMapping("/historyByDate")
	public String getBalanceHistoryByDate(@SessionAttribute("userDto") UserDto userDto, Model model,
			@ModelAttribute("date") String date) {

		LocalDate sdf = LocalDate.parse(date);

		List<Object[]> balances = balanceService.getHistoryByDate(userDto.getId(), sdf);
		model.addAttribute("balanceHistory", balances);
		Integer start = 0;
		model.addAttribute("start", start);

		return "main/balanceHistory";
	}

	@GetMapping("/historyNext")
	public String getBalanceHistoryNext(@SessionAttribute("userDto") UserDto userDto, Model model,
			@SessionAttribute("start") Integer start) {
		List<Object[]> balances = balanceService.getHistory(userDto.getId());
		model.addAttribute("balanceHistory", balances);
		start += 5;

		if (start >= balances.size()) {
			model.addAttribute("start", start - 5);
		} else {

			model.addAttribute("start", start);
		}

		return "main/balanceHistory";
	}

	@GetMapping("/historyPrev")
	public String getBalanceHistoryPrev(@SessionAttribute("userDto") UserDto userDto, Model model,
			@SessionAttribute("start") Integer start) {
		List<Object[]> balances = balanceService.getHistory(userDto.getId());
		model.addAttribute("balanceHistory", balances);
		start -= 5;
		if (start >= 0) {

			model.addAttribute("start", start);

		} else {
			model.addAttribute("start", 0);
		}
		return "main/balanceHistory";
	}

}
