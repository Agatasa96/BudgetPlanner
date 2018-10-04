package budget.controller;

import java.util.Objects;

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
import budget.dto.SaveUpDto;
import budget.dto.UserDto;
import budget.service.SaveUpService;

@Controller
@RequestMapping("/saveUp")
@SessionAttributes({ "userDto", "savedBalance", "start", "balanceHistory" })

public class SaveUpController {

	private final SaveUpService saveUpService;

	public SaveUpController(SaveUpService saveUpService) {

		this.saveUpService = saveUpService;
	}

	/*
	 * @RequestMapping public String saveUp(@SessionAttribute("savedBalance")
	 * BalanceDto balance){ return "main/saveUpPage"; }
	 */

	@GetMapping("/add")
	public String addSaveUp(@SessionAttribute("savedBalance") BalanceDto balance, Model model) {
		model.addAttribute("saveUpDto", new SaveUpDto());
		return "main/saveUpPage";
	}

	@PostMapping("/add")
	public String addSaveUp(@Valid @ModelAttribute("saveUpDto") SaveUpDto saveUpDto, BindingResult bindingResult,
			@SessionAttribute("userDto") UserDto userDto) {

		if (bindingResult.hasErrors()) {
			return "main/saveUpPage";
		} else {
			saveUpDto.setUserDto(userDto);
			SaveUpDto saveUpDto2 = saveUpService.saveUp(saveUpDto);
			if (Objects.nonNull(saveUpDto2)) {
				return "main/balancePage";
			} else {
				return "main/saveUpPage";

			}
		}

	}

	@ModelAttribute("saveUpDto")
	public SaveUpDto saveUpDto() {
		return new SaveUpDto();
	}

}
