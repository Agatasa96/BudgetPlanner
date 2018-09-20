package budget.controller;

import java.time.LocalDate;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.domain.User;
import budget.dto.UserDto;
import budget.dto.BalanceDto;

import budget.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userDto")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/signUp")
	public String signUp(Model model) {
		return "home/homePage";
	}

	@PostMapping("/signUp")
	public String signUp(@Valid @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "home/homePage";
		} else {
			UserDto savedDto = userService.signUp(userDto);
			userDto = savedDto;
			model.addAttribute("userDto", userDto);
			return "form/newMonthForm";
		}
	}

	@GetMapping("/logIn")
	public String logIn(Model model) {
		return "home/homePage";
	}

	@PostMapping("/logIn")
	public String logIn(@ModelAttribute("userDto") UserDto userDto, Model model) {
		UserDto userDto2 = userService.logIn(userDto);
		if (Objects.nonNull(userDto2)) {
			model.addAttribute("userName", userDto2.getNickname());
			if (LocalDate.now().getDayOfMonth() == 1) {
				userDto = userDto2;
				model.addAttribute("userDto", userDto);
				return "form/newMonthForm";
			} else {
				userDto = userDto2;
				model.addAttribute("userDto", userDto);
				return "main/mainPage";
			}

		} else {
			return "home/homePage";
		}

	}

	@ModelAttribute("balanceDto")
	public BalanceDto getNewBalanceDto(BalanceDto balanceDto) {
		return new BalanceDto();
	}

}
