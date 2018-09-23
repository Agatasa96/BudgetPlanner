package budget.controller;

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
@SessionAttributes({"userDto","savedBalance"})
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
	/*@GetMapping("/putInOut")
	public String putInOut(Model model) {
		model.addAttribute("balanceDto", new BalanceDto());
		return "form/putInOutForm";
	}
	@PostMapping("/putInOut")
	public String putInOut(@Valid @ModelAttribute BalanceDto balanceDto, BindingResult bindingResult, @SessionAttribute("userDto") UserDto userDto, Model model) {
		if(bindingResult.hasErrors()) {
			return "form/putInOutForm";
		}
		else {
			balanceDto.setUserDto(userDto);
			BalanceDto savedBalanceDto = balanceService.save(balanceDto);
			model.addAttribute("savedBalance", savedBalanceDto);
			return "main/balancePage";
		}
	}*/
	

}
