package budget.validator;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import budget.domain.User;
import budget.repository.UserRepository;

@Component
public class UserEmailValidator implements ConstraintValidator<EmailValidator, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(EmailValidator constraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = userRepository.findByEmail(value);
		return Objects.isNull(user);
	}

}