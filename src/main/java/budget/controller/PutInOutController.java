package budget.controller;

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
import budget.domain.PutInOut;
import budget.dto.BalanceDto;
import budget.dto.PutInOutDto;
import budget.dto.UserDto;
import budget.service.PutInOutService;

@Controller
@RequestMapping("putInOut")
@SessionAttributes({ "userDto", "savedBalance", "putInOutSaved" })
public class PutInOutController {

	private final PutInOutService putInOutService;

	public PutInOutController(PutInOutService putInOutService) {

		this.putInOutService = putInOutService;
	}

	@GetMapping("/form")
	public String putInOut(Model model) {
		model.addAttribute("putInOut", new PutInOutDto());

		return "form/putInOutForm";
	}

	@PostMapping("/form")
	public String putInOut(@Valid @ModelAttribute("putInOut") PutInOutDto putInOutDto, BindingResult bindingResult,
			@SessionAttribute("userDto") UserDto userDto, Model model) {
		if (bindingResult.hasErrors()) {
			return "form/putInOutForm";
		} else {
			putInOutDto.setUserDto(userDto);
			putInOutService.save(putInOutDto);

			Balance savedBalance = putInOutService.countTotalBalance(userDto.getId());
			model.addAttribute("savedBalance", savedBalance);

			return "main/balancePage";
		}

	}

	@ModelAttribute("putInOut")
	public PutInOutDto getNewPutnOutDto(PutInOutDto putInOutDto) {
		return new PutInOutDto();
	}

}
