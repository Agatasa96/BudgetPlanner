package budget.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double totalBalance;
	private Double saveBalance;

	@NumberFormat

	private Double putInMonthly;
	@NumberFormat

	private Double saveUp;
	private LocalDateTime date;
	private Double totalSaved;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne
	@JoinColumn(name = "putInOut_id")
	private PutInOut putInOut;

	@OneToOne
	@JoinColumn(name = "toSaveUp_id")
	private SaveUp toSaveUp;
}
