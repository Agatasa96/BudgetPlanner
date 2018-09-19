package budget.controller;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/balance")
public class BalanceController {

	@RequestMapping
	public String balancePage(Model model ) {
		model.addAttribute("month", LocalDate.now().getMonth());
		return "main/balancePage";
	}
	
}
