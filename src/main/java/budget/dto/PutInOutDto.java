package budget.dto;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;

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

	private LocalDateTime date;

	@ManyToOne
	private UserDto userDto;
}
