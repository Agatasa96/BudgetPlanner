package budget.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.dto.UserDto;

@Controller
@RequestMapping("/")
@SessionAttributes("userDto")

public class HomePageController {

	@RequestMapping
	public String homePage() {
		return "home/homePage";
	}

	@RequestMapping("main")
	public String mainPage() {
		return "main/mainPage";
	}

	@ModelAttribute("userDto")
	public UserDto getUserDto(UserDto userDto) {
		return new UserDto();
	}
}
