package budget.service;

import java.util.Objects;

import org.springframework.stereotype.Service;

import budget.domain.Item;
import budget.dto.ItemDto;
import budget.repository.ItemRepository;

@Service
public class ItemService {

	private final ItemRepository itemRepository;
	
	public ItemService(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}



	public ItemDto saveItem(ItemDto itemDto) {
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
		return item;
	}
	
	private ItemDto toDto(Item item) {
		ItemDto dto =new ItemDto();
		dto.setId(item.getId());
		dto.setName(item.getName());
		dto.setPrice(item.getPrice());
		return dto;
	}
}
