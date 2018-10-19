package budget.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDto {
	private Long id;

	@NotNull
	@Size(min = 3, max = 10)
	private String name;

	private List<ItemDto> items;

	private UserDto userDto;

	private Double totalPrice;
}
