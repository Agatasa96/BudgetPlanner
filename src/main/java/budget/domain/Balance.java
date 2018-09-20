package budget.domain;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Double totalBalance;
	private Double saveBalance;
	private Double afterShoppingBalance;
	@NumberFormat
	private Double putIn;
	@NumberFormat
	private Double saveUp;
	private LocalDate date;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
}
