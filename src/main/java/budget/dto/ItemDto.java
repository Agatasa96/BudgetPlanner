package budget.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import budget.domain.Item;
import budget.domain.ShoppingList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
private Long id;
	
	@NotNull
	@Size(min=3, max = 15)
	private String name;
	@NotNull
	@NumberFormat
	private Double price;
	
	public Item toDomainItem(ItemDto itemDto) {
		Item item = new Item();
		item.setId(itemDto.getId());
		item.setName(itemDto.getName());
		item.setPrice(itemDto.getPrice());
		return item;
	}
}
