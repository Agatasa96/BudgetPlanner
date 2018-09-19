package budget.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import budget.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewMonth {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double putIn;
	private Double toSave;
	LocalDate date = LocalDate.now();
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	
	
	
}
