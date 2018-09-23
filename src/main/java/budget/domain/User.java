package budget.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import budget.validator.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Email	
	private String email;
	@Size(min = 5)
	@NotNull
	@NotBlank
	private String password;
	@Size(min=5, max=15)
	@NotNull
	private String nickname;

	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List<Balance> balance;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.REMOVE)
	private List <PutInOut> putsInOut;
}
