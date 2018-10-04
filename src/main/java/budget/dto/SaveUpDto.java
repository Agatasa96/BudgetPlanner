package budget.dto;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;

import budget.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveUpDto {

	private Long id;

	@NotNull
	@NumberFormat
	private Double toSaveUp;

	private LocalDateTime date;

	private UserDto userDto;
}
