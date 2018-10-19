package budget.controller;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import budget.domain.ShoppingList;
import budget.dto.BalanceDto;
import budget.dto.ItemDto;
import budget.dto.PutInOutDto;
import budget.dto.ShoppingListDto;
import budget.dto.UserDto;
import budget.service.BalanceService;
import budget.service.ItemService;
import budget.service.PutInOutService;
import budget.service.ShoppingListService;

@Controller
@SessionAttributes({ "userDto", "savedList", "listId", "savedBalance", "putInOutShopping" })
@RequestMapping("/shoppingList")
public class ShoppingListController {

	private final ShoppingListService shoppingListService;
	private final ItemService itemService;
	private final BalanceService balanceService;
	private final PutInOutService putInOutService;

	public ShoppingListController(ShoppingListService shoppingListService, ItemService itemService,
			BalanceService balanceService, PutInOutService putInOutService) {
		this.shoppingListService = shoppingListService;
		this.itemService = itemService;
		this.balanceService = balanceService;
		this.putInOutService = putInOutService;
	}

	@GetMapping
	public String goToShoppingList(@SessionAttribute("userDto") UserDto userDto, Model model,
			@SessionAttribute("savedBalance") BalanceDto balanceDto) {
		List<ShoppingListDto> allLists = shoppingListService.getAllLists(userDto.getId());
		model.addAttribute("savedList", allLists);
		model.addAttribute("savedBalance", balanceDto);
		return "main/shoppingListPage";
	}

	@GetMapping("/showItems/{id}")
	public String save(Model model, @SessionAttribute("userDto") UserDto userDto,
			@PathVariable("id") Long shoppingListId) {

		List<Object[]> itemsList = shoppingListService.getItemsList(userDto.getId(), shoppingListId);

		model.addAttribute("itemsList", itemsList);
		return "main/shoppingListPage";
	}

	@GetMapping("/addList")
	public String addList(Model model) {
		model.addAttribute("list", new ShoppingListDto());
		return "form/shoppingListForm";
	}

	@PostMapping("/addList")
	public String addList(@Valid @ModelAttribute("list") ShoppingListDto shoppingListDto, BindingResult bindingResult,
			@SessionAttribute("userDto") UserDto userDto, Model model) {
		if (bindingResult.hasErrors()) {
			return "form/shoppingListForm";
		} else {
			shoppingListDto.setUserDto(userDto);
			ShoppingListDto savedList = shoppingListService.addList(shoppingListDto);
			if (Objects.nonNull(savedList)) {
				List<ShoppingListDto> allLists = shoppingListService.getAllLists(userDto.getId());
				model.addAttribute("savedList", allLists);
				return "main/shoppingListPage";
			}

			return "main/shoppingListPage";
		}

	}

	@GetMapping("/addItem/{id}")
	public String addItem(Model model, @PathVariable("id") Long id, @SessionAttribute("userDto") UserDto userDto) {
		model.addAttribute("item", new ItemDto());
		model.addAttribute("listId", id);
		List<ItemDto> itemList = itemService.findItems(id);
		model.addAttribute("itemList", itemList);

		return "form/addItem";
	}

	@PostMapping("/addItem")
	public String addItem(Model model, @Valid @ModelAttribute("item") ItemDto itemDto,
			@SessionAttribute("listId") Long id, @SessionAttribute("userDto") UserDto userDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form/addItem";
		} else {
			ShoppingListDto shoppingListDto = shoppingListService.getOne(id);
			if (Objects.nonNull(shoppingListDto)) {
				itemDto.setShoppingListDto(shoppingListDto);
				model.addAttribute("shopingList", shoppingListDto);
			}

			itemService.saveItem(itemDto, id);
			List<ItemDto> itemList = itemService.findItems(id);

			if (Objects.nonNull(itemList)) {
				model.addAttribute("itemList", itemList);
			}

			return "form/addItem";
		}

	}

	@GetMapping("/countBalance/{id}")
	public String countBalance(@PathVariable("id") Long id, @SessionAttribute("savedBalance") BalanceDto balanceDto,
			Model model) {
		ShoppingListDto shoppingListDto = shoppingListService.getOne(id);
		BalanceDto countedBalanceDto = balanceService.countBalance(shoppingListDto, balanceDto);
		model.addAttribute("savedBalance", countedBalanceDto);
		return "main/shoppingListPage";
	}

	@GetMapping("/resetBalance")
	public String resetBalance(@SessionAttribute("userDto") UserDto userDto, Model model) {
		BalanceDto balanceDto = balanceService.lastBalance(userDto);
		model.addAttribute("savedBalance", balanceDto);
		return "main/shoppingListPage";
	}

	@GetMapping("/editList/{id}")
	public String editList(@PathVariable("id") Long id, Model model) {
		model.addAttribute("listId", id);
		model.addAttribute("editList", new ShoppingList());
		return "form/editListForm";
	}

	@PostMapping("/editList")
	public String editList(@SessionAttribute("listId") Long id,
			@Valid @ModelAttribute("editList") ShoppingListDto editedList, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form/editListForm";
		} else {
			ShoppingListDto dto = shoppingListService.editList(id, editedList);
			if (Objects.nonNull(dto)) {
				return "redirect:/shoppingList";
			} else {
				return "form/editListForm";
			}
		}

	}

	@GetMapping("/deleteList/{id}")
	public String deleteList(@PathVariable("id") Long id) {
		shoppingListService.deleteList(id);
		return "redirect:/shoppingList";
	}

	@GetMapping("/buyList/{id}")
	public String buyList(@PathVariable("id") Long id, Model model, @SessionAttribute("userDto") UserDto userDto) {
		ShoppingListDto shoppingListDto = shoppingListService.getOne(id);
		PutInOutDto inOutDto = new PutInOutDto();
		inOutDto.setDate(LocalDateTime.now());
		inOutDto.setUserDto(userDto);
		inOutDto.setPutOut(shoppingListDto.getTotalPrice());
		model.addAttribute("listId", id);
		String putInOutDto2 = putInOutService.save(inOutDto);
		if (Objects.nonNull(putInOutDto2)) {
			if (putInOutDto2.equals("added")) {
				BalanceDto savedBalance = putInOutService.countTotalBalance(userDto.getId());
				shoppingListService.deleteList(id);
				model.addAttribute("savedBalance", savedBalance);
			} else if (putInOutDto2.equals("useSaved")) {
				model.addAttribute("putInOutShopping", inOutDto);
				return "alert/useSavedPageShopping";
			}
		}

		return "redirect:/shoppingList";
	}

	@GetMapping("/dontUseSaved")
	public String dontUseSaved() {
		JOptionPane.showMessageDialog(null, "Cannot buy");

		return "main/balancePage";
	}

	@GetMapping("/useSaved")
	public String useSaved(@SessionAttribute("putInOutShopping") PutInOutDto putInOutDto,
			@SessionAttribute("userDto") UserDto userDto, Model model, @SessionAttribute("listId") Long id) {

		BalanceDto savedBalance = putInOutService.useSaved(putInOutDto, userDto.getId());
		if (Objects.nonNull(savedBalance)) {
			model.addAttribute("savedBalance", savedBalance);
			shoppingListService.deleteList(id);
		}

		return "main/balancePage";

	}

}
