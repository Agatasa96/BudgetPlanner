package budget.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import budget.domain.Item;
import budget.domain.ShoppingList;
import budget.domain.User;
import budget.dto.ItemDto;
import budget.dto.ShoppingListDto;
import budget.repository.ShoppingListRepository;
import budget.repository.UserRepository;

@Service
public class ShoppingListService {

	private final ShoppingListRepository shoppingListRepository;
	private final UserRepository userRepository;
	
	
	public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository) {
		this.shoppingListRepository = shoppingListRepository;
		this.userRepository = userRepository;
	}


	public void save(ShoppingListDto list) {
		shoppingListRepository.save(toDomain(list));
	}

	private ShoppingList toDomain(ShoppingListDto dto) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setId(dto.getId());
		
		List<ItemDto> dtoItems = dto.getItems();
		List<Item> items = new ArrayList<>();
		for(ItemDto i:dtoItems) {
			items.add(toDomainItem(i));
		}
		shoppingList.setItems(items);
		User user = userRepository.findOne(dto.getUserDto().getId());
		shoppingList.setUser(user);
		return shoppingList;
	}
	
	 
	 public Item toDomainItem(ItemDto itemDto) {
			Item item = new Item();
			item.setId(itemDto.getId());
			item.setName(itemDto.getName());
			item.setPrice(itemDto.getPrice());
			return item;
		}

}
