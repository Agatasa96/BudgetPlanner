package budget.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.dto.BalanceDto;

import budget.dto.UserDto;
import budget.service.BalanceService;

@Controller
@RequestMapping("/balance")
@SessionAttributes("userDto")
public class BalanceController {

	private final BalanceService balanceService;

	public BalanceController(BalanceService balanceService) {

		this.balanceService = balanceService;
	}

	@PostMapping("/newMonth")
	public String update(@Valid @ModelAttribute BalanceDto balanceDto, BindingResult bindingResult, Model model,
			@SessionAttribute("userDto") UserDto userDto) {
		if (bindingResult.hasErrors()) {
			return "form/newMonthForm";
		} else {

			balanceDto.setUserDto(userDto);
			balanceService.save(balanceDto);

			return "main/balancePage";
		}

	}
}
