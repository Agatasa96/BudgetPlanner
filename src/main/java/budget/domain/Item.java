package budget.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

import budget.dto.ItemDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=3, max = 15)
	private String name;
	@NotNull
	@NumberFormat
	private Double price;
	
	@ManyToOne
	@JoinColumn(name="shoppingList_id")
	private ShoppingList shoppingList;
	
	@Transient
	public static ItemDto toDto(Item item) {
		ItemDto dto =new ItemDto();
		dto.setId(item.getId());
		dto.setName(item.getName());
		dto.setPrice(item.getPrice());
		
		return dto;
	}
}
