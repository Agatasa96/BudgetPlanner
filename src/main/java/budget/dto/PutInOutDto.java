package budget.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;


import budget.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PutInOutDto {

	@Id
	@GeneratedValue
	private Long id;
	
	@NumberFormat
	private Double putIn;
	@NumberFormat
	private Double putOut;
	
	@ManyToOne
	private UserDto userDto;
}
