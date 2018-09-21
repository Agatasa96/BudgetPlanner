package budget.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import budget.validator.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	private Long id;
	@EmailValidator
	@NotNull
	private String email;
	@Size(min = 5)
	@NotNull
	@NotBlank
	private String password;
	@Size(min = 5, max = 15)
	@NotNull
	private String nickname;
}