package budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import budget.domain.NewMonth;
import budget.dto.NewMonthDto;

public interface NewMonthRepository extends JpaRepository<NewMonth, Long> {

	NewMonth findFirstByUserIdOrderByDateDesc(Long id);
}
