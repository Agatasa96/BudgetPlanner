package budget.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
	private Long id;

	@NotNull
	@Size(min = 3, max = 15)
	private String itemName;
	@NotNull
	@NumberFormat
	private Double price;

	private ShoppingListDto shoppingListDto;

}
