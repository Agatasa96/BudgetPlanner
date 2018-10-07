package budget.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import budget.domain.Item;
import budget.domain.ShoppingList;
import budget.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDto {
	private Long id;
	
	@NotNull
	@Size(min=3, max=10)
	private String name;
	
	private List<ItemDto> items;

	private UserDto userDto;
}
