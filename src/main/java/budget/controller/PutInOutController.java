package budget.controller;

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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.domain.Balance;
import budget.domain.PutInOut;
import budget.dto.BalanceDto;
import budget.dto.PutInOutDto;
import budget.dto.UserDto;
import budget.service.BalanceService;
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
	public String putIn(@Valid @ModelAttribute("putInOut") PutInOutDto putInOutDto, BindingResult bindingResult,
			@SessionAttribute("userDto") UserDto userDto, Model model) {
		if (bindingResult.hasErrors()) {
			return "form/putInOutForm";
		} else {
			putInOutDto.setUserDto(userDto);
			String putInOutDto2 = putInOutService.save(putInOutDto);
			if (putInOutDto2.equals("added")) {
				BalanceDto savedBalance = putInOutService.countTotalBalance(userDto.getId());
				
				model.addAttribute("savedBalance", savedBalance);
			} else if (putInOutDto2.equals("useSaved")) {
				model.addAttribute("putInOutSaved", putInOutDto);
				return "alert/useSavedPage";
			}

			return "main/balancePage";
		}

	}

	@GetMapping("/dontUseSaved")
	public String dontUseSaved() {
		JOptionPane.showMessageDialog(null, "Cannot pay out");

		return "main/balancePage";
	}

	@GetMapping("/useSaved")
	public String useSaved(@SessionAttribute("putInOutSaved") PutInOutDto putInOutDto,
			@SessionAttribute("userDto") UserDto userDto, Model model) {

		BalanceDto savedBalance = putInOutService.useSaved(putInOutDto, userDto.getId());
		if (Objects.nonNull(savedBalance)) {
			model.addAttribute("savedBalance", savedBalance);
		}

		return "main/balancePage";

	}

	@ModelAttribute("putInOut")
	public PutInOutDto getNewPutnOutDto(PutInOutDto putInOutDto) {
		return new PutInOutDto();
	}

}
