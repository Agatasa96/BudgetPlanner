package budget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import budget.domain.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long>{

}
