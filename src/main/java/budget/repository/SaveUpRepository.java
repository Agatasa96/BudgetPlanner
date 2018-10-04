package budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budget.domain.PutInOut;
import budget.domain.SaveUp;
import budget.dto.SaveUpDto;

@Repository
public interface SaveUpRepository extends JpaRepository<SaveUp, Long>{
	SaveUp findFirstByUserIdOrderByIdDesc(Long id);
}
