package budget.controller;

import java.util.List;
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
@SessionAttributes({ "userDto", "savedBalance", "start", "balanceHistory", "saveUpHistory" })

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
			@SessionAttribute("userDto") UserDto userDto, Model model) {

		if (bindingResult.hasErrors()) {
			return "main/saveUpPage";
		} else {
			saveUpDto.setUserDto(userDto);
			String saveUpDto2 = saveUpService.saveUp(saveUpDto);
			
			if (Objects.nonNull(saveUpDto2)) {
				BalanceDto balanceDto = saveUpService.countBalance(userDto.getId());
				if(Objects.nonNull(balanceDto)) {
					model.addAttribute("savedBalance", balanceDto);
					return "main/balancePage";
				}
				else {
					return  "main/saveUpPage";
				}
				
			} else {
				return "main/saveUpPage";

			}
		}
		
	}
	
	@GetMapping("/history")
	public String getSaveUpHistory(@SessionAttribute("userDto") UserDto userDto, Model model) {
		List<Object[]> saveUpList = saveUpService.getHistory(userDto.getId());
		model.addAttribute("saveUpHistory", saveUpList);
		Integer start = 0;
		model.addAttribute("start", start);
		return "main/saveUpHistory";
	}

	@ModelAttribute("saveUpDto")
	public SaveUpDto saveUpDto() {
		return new SaveUpDto();
	}

}
