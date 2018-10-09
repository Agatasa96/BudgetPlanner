package budget.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import budget.dto.ShoppingListDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingList {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=3, max=10)
	private String name;
	
	@OneToMany(cascade=CascadeType.REMOVE)
	private List<Item> items;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Transient
	public static ShoppingListDto toDto(ShoppingList shoppingList) {
		ShoppingListDto shoppingListDto = new ShoppingListDto();
		shoppingListDto.setId(shoppingList.getId());
		shoppingListDto.setName(shoppingList.getName());
		return shoppingListDto;
	}
}
