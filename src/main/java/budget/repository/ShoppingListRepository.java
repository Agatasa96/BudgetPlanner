package budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import budget.domain.ShoppingList;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {
	List<ShoppingList> findAllByUserId(Long id);

	@Query(value = "SELECT Item.itemName, Item.price, ShoppingList.name, ShoppingList.user_id, ShoppingList.id\n" + 
			"FROM Item \n" + 
			"left join ShoppingList on Item.shoppingList_id = ShoppingList.id\n" + 
			"having user_id=?1 and ShoppingList.id = ?2", nativeQuery = true)
	
	List<Object[]> itemsList(Long userId, Long shoppingListId);
}
