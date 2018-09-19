package budget.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double totalBalance;
	private Double saveBalance;
	private Double afterShoppingBalance;
	private Double saveUp;
	private Date date;
}
