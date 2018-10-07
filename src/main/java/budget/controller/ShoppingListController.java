package budget.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@SessionAttributes({ "userDto", "savedBalance", "start", "balanceHistory", "list" })
@RequestMapping("/shoppingList")
public class ShoppingListController {

	private final ShoppingListService shoppingListService; 
	private final ItemService itemService; 
	
	public ShoppingListController(ShoppingListService shoppingListService, ItemService itemService) {
		this.shoppingListService = shoppingListService;
		this.itemService = itemService;
	}

	@RequestMapping
	public String goToShoppingList() {
		return "main/shoppingListPage";
	}
	
	@GetMapping("/addItem")
	public String addShoppingList(Model model) {
		model.addAttribute("item", new Item());
		model.addAttribute("list", new ShoppingList());
		return "form/addShoppingList";
	}
	
	@PostMapping("/addItem")
	@ResponseBody
	public String addShoppingList(@ModelAttribute("item") ItemDto itemDto, @ModelAttribute("list") ShoppingListDto shoppingListDto, Model model) {
		ItemDto savedItem = itemService.saveItem(itemDto);
	shoppingListDto.getItems().add(savedItem);
	model.addAttribute("list", shoppingListDto);
		return "form/addShoppingList";
	}
	
	@GetMapping("/addList")
	@ResponseBody
	public String addShoppingList(Model model, @SessionAttribute("userDto") UserDto userDto, @SessionAttribute("list") ShoppingListDto list) {
		list.setUserDto(userDto);
		shoppingListService.save(list);
		return "saved";
	}
	
}
