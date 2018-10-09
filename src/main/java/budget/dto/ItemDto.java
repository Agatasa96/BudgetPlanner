package budget.dto;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	
	private ShoppingListDto shoppingListDto;
	
}
