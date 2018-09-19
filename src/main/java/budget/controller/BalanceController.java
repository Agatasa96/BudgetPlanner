package budget.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.dto.NewMonthDto;
import budget.dto.UserDto;
import budget.service.NewMonthService;

@Controller
@RequestMapping("/balance")
@SessionAttributes("userDto")
public class BalanceController {

	private final NewMonthService newMonthService;

	public BalanceController(NewMonthService newMonthService) {
		this.newMonthService = newMonthService;
	}
	
	@RequestMapping
	public String balancePage(@ModelAttribute("userDto") UserDto userDto,Model model) {
		model.addAttribute("month", LocalDate.now().getMonth());
		NewMonthDto monthDto = newMonthService.monthlySaveUp(userDto.getId());
		model.addAttribute("newMonthSum", monthDto);
		return "main/balancePage";
	}

}
