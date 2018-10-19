package budget.dto;

import java.time.LocalDateTime;
import org.springframework.format.annotation.NumberFormat;

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
	@NumberFormat

	private Double putInMonthly;
	@NumberFormat

	private Double saveUp;
	private LocalDateTime date;
	private UserDto userDto;
	private PutInOutDto putInOutDto;
	private SaveUpDto saveUpDto;
	private Double totalSaved;
}