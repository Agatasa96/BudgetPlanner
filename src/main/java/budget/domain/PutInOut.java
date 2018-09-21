package budget.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PutInOut {

	@Id
	@GeneratedValue
	private Long id;
	
	@NumberFormat
	private Double putIn;
	@NumberFormat
	private Double putOut;
	
	@ManyToOne
	private User user;
}
