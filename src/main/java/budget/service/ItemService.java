package budget.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

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

		countTotalPrice(id, itemDto);
		if (Objects.nonNull(item)) {
			JOptionPane.showMessageDialog(null, "Added");

			return toDto(item);
		}
		return itemDto;
	}

	private void countTotalPrice(Long id, ItemDto itemDto) {
		ShoppingList shoppingList = shoppingListRepository.findOne(id);
		Double totalPrice = shoppingList.getTotalPrice();
		if (Objects.nonNull(totalPrice)) {
			totalPrice += itemDto.getPrice();
		} else {

			totalPrice = itemDto.getPrice();
		}

		shoppingList.setTotalPrice(totalPrice);
		shoppingListRepository.save(shoppingList);

	}

	public List<ItemDto> findItems(Long id) {
		return itemRepository.findAllByShoppingListId(id).stream().filter(Objects::nonNull).map(Item::toDto)
				.collect(Collectors.toList());
	}

	private Item toDomain(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setItemName(itemDto.getItemName());
		item.setPrice(itemDto.getPrice());
		ShoppingList list = shoppingListRepository.findOne(itemDto.getShoppingListDto().getId());
		item.setShoppingList(list);
		return item;
	}

	private ItemDto toDto(Item item) {
		ItemDto dto = new ItemDto();
		dto.setId(item.getId());
		dto.setItemName(item.getItemName());
		dto.setPrice(item.getPrice());
		return dto;
	}

}
