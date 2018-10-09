package budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budget.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	List<Item> findAllByShoppingListId(Long id);
}
