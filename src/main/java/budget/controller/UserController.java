package budget.controller;

import java.time.LocalDate;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;
import javax.validation.constraints.Size;

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
import budget.service.BalanceService;
import budget.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes({ "savedBalance", "userDto" })
public class UserController {

	private final UserService userService;
	private final BalanceService balanceService;

	public UserController(UserService userService, BalanceService balanceService) {
		this.userService = userService;
		this.balanceService = balanceService;
	}

	@GetMapping("/signUp")
	public String signUp(Model model) {
		model.addAttribute("userDto", new UserDto());
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
		model.addAttribute("userDto", new UserDto());
		return "home/homePage";
	}

	@PostMapping("/logIn")
	public String logIn(@ModelAttribute("userDto") UserDto userDto, Model model) {
		UserDto userDto2 = userService.logIn(userDto);
		if (Objects.nonNull(userDto2)) {
			BalanceDto balanceDto = balanceService.lastBalance(userDto2);
			if (LocalDate.now().getDayOfMonth() == 1 && balanceDto.getDate().getDayOfMonth() !=1) {
				userDto = userDto2;
				model.addAttribute("userDto", userDto);
				return "form/newMonthForm";
			} else {
				userDto = userDto2;
				model.addAttribute("userDto", userDto);
				//BalanceDto balanceDto = balanceService.lastBalance(userDto);
				model.addAttribute("savedBalance", balanceDto);
				return "main/mainPage";
			}

		} else {
			return "home/homePage";
		}

	}

	@GetMapping("/getUser")
	public String getUser(@ModelAttribute("userDto") UserDto userDto) {
		UserDto userDto2 = userService.getUserData(userDto);
		if (Objects.nonNull(userDto2)) {
			return "main/userDataPage";
		}
		return "main/mainPage";
	}

	@GetMapping("/editNickname")
	public String editNickname(@ModelAttribute("userDto") UserDto userDto, Model model) {
		model.addAttribute("editUserDto", userDto);
		return "/form/editUserForm";
	}

	@PostMapping("/editNickname")
	public String editNickname(@ModelAttribute("editUserDto") UserDto editUserDto,
			@SessionAttribute("userDto") UserDto userDto, Model model) {
		UserDto userDto2 = userService.editNickname(editUserDto, userDto);

		if (Objects.isNull(userDto2)) {
			return "/form/editUserForm";
		} else {
			
			userDto = userDto2;
			model.addAttribute("userDto", userDto);
			return "/main/userDataPage";
		}

	}

	@GetMapping("/editPassword")
	public String editPassword(@ModelAttribute("userDto") UserDto userDto, Model model) {
		model.addAttribute("editUserDto", userDto);
		return "/form/editUserForm";
	}

	@PostMapping("/editPassword")
	public String editPassword(@ModelAttribute("editUserDto") UserDto editUserDto,
			@SessionAttribute("userDto") UserDto userDto, Model model) {
		UserDto userDto2 = userService.editPassword(editUserDto, userDto);

		if (Objects.isNull(userDto2)) {
			return "/form/editUserForm";
		} else {
			// UserDto userDto2 = userService.editNickname(editNickname, userDto);
			userDto = userDto2;
			model.addAttribute("userDto", userDto);
			return "/main/userDataPage";
		}

	}

	@GetMapping("/deleteUser")
	public String deleteUser(@ModelAttribute("userDto") UserDto userDto, Model model) {
		model.addAttribute("deleteUserDto", userDto);
		return "/alert/deleteUser";
	}

	@GetMapping("/deleteYes")
	public String deleteUserYes(@ModelAttribute("userDto") UserDto userDto) {
		String deleted = userService.delete(userDto);
		if (deleted.equals("y")) {
			return "redirect:/";
		} else {
			return "/main/userDataPage";
		}

	}

	@ModelAttribute("balanceDto")
	public BalanceDto getNewBalanceDto(BalanceDto balanceDto) {
		return new BalanceDto();
	}

}
