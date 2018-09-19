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

import budget.dto.NewMonthDto;
import budget.dto.UserDto;
import budget.service.NewMonthService;

@Controller
@RequestMapping("/newMonth")
@SessionAttributes("userDto")
public class NewMonthController {

	private final NewMonthService newMonthService;

	public NewMonthController(NewMonthService newMonthService) {
		this.newMonthService = newMonthService;
	}

	@PostMapping("/update")
	public String update(@Valid @ModelAttribute NewMonthDto newMonthDto, BindingResult bindingResult, Model model,
			@SessionAttribute("userDto") UserDto userDto) {
		if (bindingResult.hasErrors()) {
			return "form/newMonthForm";
		} else {

			newMonthDto.setUserDto(userDto);
			newMonthService.save(newMonthDto);

			NewMonthDto monthDto = newMonthService.monthlySaveUp(userDto.getId());
			model.addAttribute("newMonthSum", monthDto);
			return "main/balancePage";
		}

	}
}
