package budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.dto.UserDto;

@Controller
@RequestMapping("/")
@SessionAttributes({ "userDto", "savedBalance" })

public class HomePageController {

	@RequestMapping
	public String homePage(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "home/homePage";
	}

	@RequestMapping("main")
	public String mainPage() {
		return "main/mainPage";
	}


}
