package budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budget.domain.Balance;
import budget.domain.PutInOut;

@Repository
public interface PutInOutRepository extends JpaRepository<PutInOut, Long> {
	PutInOut findFirstByUserIdOrderByIdDesc(Long id);
}
