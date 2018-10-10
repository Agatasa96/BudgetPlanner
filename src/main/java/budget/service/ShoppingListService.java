package budget.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

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

	public ShoppingListDto addList(ShoppingListDto shoppingListDto) {
		ShoppingList shoppingList = shoppingListRepository.save(toDomain(shoppingListDto));
		if (Objects.nonNull(shoppingList)) {
			JOptionPane.showMessageDialog(null, "Added list");
			return toDto(shoppingList);
		} else {
			JOptionPane.showMessageDialog(null, "Cannot add");
			return null;
		}

	}

	public List<Object[]> getItemsList(Long userId, Long shoppingListId) {
		return shoppingListRepository.itemsList(userId, shoppingListId);

	}

	public List<ShoppingListDto> getAllLists(Long id) {
		return shoppingListRepository.findAllByUserId(id).stream().filter(Objects::nonNull).map(ShoppingList::toDto)
				.collect(Collectors.toList());
	}

	public ShoppingListDto getOne(Long id) {
		ShoppingList list = shoppingListRepository.findOne(id);
		return toDto(list);
	}

	private ShoppingList toDomain(ShoppingListDto dto) {
		ShoppingList shoppingList = new ShoppingList();
		shoppingList.setId(dto.getId());
		shoppingList.setName(dto.getName());
		User user = userRepository.findOne(dto.getUserDto().getId());
		shoppingList.setUser(user);
		shoppingList.setTotalPrice(dto.getTotalPrice());
		return shoppingList;
	}

	private ShoppingListDto toDto(ShoppingList shoppingList) {
		ShoppingListDto shoppingListDto = new ShoppingListDto();
		shoppingListDto.setId(shoppingList.getId());
		shoppingListDto.setName(shoppingList.getName());
		shoppingListDto.setTotalPrice(shoppingList.getTotalPrice());
		return shoppingListDto;
	}

}
