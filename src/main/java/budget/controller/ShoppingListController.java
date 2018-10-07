package budget.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import budget.domain.Item;
import budget.domain.ShoppingList;
import budget.dto.ItemDto;
import budget.dto.ShoppingListDto;
import budget.dto.UserDto;
import budget.service.ItemService;
import budget.service.ShoppingListService;

@Controller
@SessionAttributes({ "userDto", "savedBalance", "start", "balanceHistory","savedList" })
@RequestMapping("/shoppingList")
public class ShoppingListController {

	private final ShoppingListService shoppingListService;
	private final ItemService itemService;

	public ShoppingListController(ShoppingListService shoppingListService, ItemService itemService) {
		this.shoppingListService = shoppingListService;
		this.itemService = itemService;
	}

	@RequestMapping
	public String goToShoppingList( ) {
		
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
			List<List<ShoppingList>> allLists = shoppingListService.getAllLists(userDto.getId());
			model.addAttribute("savedList", allLists);
			return "main/shoppingListPage";
		}

	}

	/*
	 * @GetMapping("/addItem") public String addShoppingList(Model model) {
	 * model.addAttribute("item", new Item()); model.addAttribute("list", new
	 * ShoppingList()); return "form/addShoppingList"; }
	 * 
	 * @PostMapping("/addItem")
	 * 
	 * @ResponseBody public String addShoppingList(@ModelAttribute("item") ItemDto
	 * itemDto, @SessionAttribute("list") ShoppingListDto shoppingListDto, Model
	 * model) { ItemDto savedItem = itemService.saveItem(itemDto); List<ItemDto>
	 * list = new ArrayList<>(); list.add(savedItem);
	 * shoppingListDto.setItems(list); model.addAttribute("list", shoppingListDto);
	 * return "form/addShoppingList"; }
	 * 
	 * @GetMapping("/addList")
	 * 
	 * @ResponseBody public String addShoppingList(Model
	 * model, @SessionAttribute("userDto") UserDto
	 * userDto, @SessionAttribute("list") ShoppingListDto list) {
	 * list.setUserDto(userDto); shoppingListService.save(list); return "saved"; }
	 */
}
