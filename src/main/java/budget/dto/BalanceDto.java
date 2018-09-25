package budget.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.NumberFormat;

import budget.domain.PutInOut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {

	private Long id;
	private Double totalBalance;
	private Double saveBalance;
	private Double afterShoppingBalance;
	@NumberFormat
	private Double putIn;
	@NumberFormat
	private Double saveUp;
	private LocalDate date;
	private UserDto userDto;
	private PutInOut putInOut;
}