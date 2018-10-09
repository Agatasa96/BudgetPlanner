package budget.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import budget.domain.Item;
import budget.domain.ShoppingList;
import budget.dto.ItemDto;
import budget.repository.ItemRepository;
import budget.repository.ShoppingListRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	private final ShoppingListRepository shoppingListRepository;
	
	public ItemService(ItemRepository itemRepository, ShoppingListRepository shoppingListRepository) {
		this.itemRepository = itemRepository;
		this.shoppingListRepository = shoppingListRepository;
	}



	public ItemDto saveItem(ItemDto itemDto, Long id) {
		Item item = itemRepository.save(toDomain(itemDto));
		if(Objects.nonNull(item)) {
			return toDto(item);
		}
		return itemDto;
	}
	
	private Item toDomain(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		ShoppingList list = shoppingListRepository.findOne(itemDto.getShoppingListDto().getId());
		item.setShoppingList(list);
		return item;
	}
	
	private ItemDto toDto(Item item) {
		ItemDto dto =new ItemDto();
		dto.setId(item.getId());
		dto.setName(item.getName());
		dto.setPrice(item.getPrice());
		//ShoppingList list = shoppingListRepository.findOne(item.getShoppingList().getId());
		
		return dto;
	}



	public List<Item> findItems(Long id) {
		return itemRepository.findAllByShoppingListId(id);
		 
	}
}
