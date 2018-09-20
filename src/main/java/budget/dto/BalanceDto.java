package budget.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double totalBalance;
	private Double saveBalance;
	private Double afterShoppingBalance;
	private Double putIn;
	private Double saveUp;
	private LocalDate date;
	private UserDto userDto;
}