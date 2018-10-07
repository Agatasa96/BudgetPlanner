package budget.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import budget.domain.Item;
import budget.domain.ShoppingList;
import budget.repository.ItemRepository;
import budget.repository.ShoppingListRepository;

public class ItemConverter implements Converter<String, ShoppingList>{

	@Autowired
	private ShoppingListRepository repo;
	@Override
	public ShoppingList convert(String source) {
		ShoppingList shoppingList = repo.findOne(Long.parseLong(source));
		return shoppingList;
	}

}
