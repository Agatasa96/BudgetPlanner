package budget.repository;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import budget.domain.Balance;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
	Balance findFirstByUserIdOrderByIdDesc(Long id);
	@Query(value="SELECT Balance.user_id,Balance.totalBalance, Balance.saveBalance, Balance.afterShoppingBalance, Balance.putInMonthly, PutInOut.putOut, PutInOut.putIn, Balance.saveUp, Balance.date\n" + 
			"FROM Balance \n" + 
			"left join PutInOut on Balance.putInOut_id =  PutInOut.id \n" + 
			"having Balance.user_id= ?1 order by date desc", nativeQuery=true)
	List<Object[]> getBalanceHistory(Long id);
	@Query(value="SELECT Balance.user_id,Balance.totalBalance, Balance.saveBalance, Balance.afterShoppingBalance, Balance.putInMonthly, PutInOut.putOut, PutInOut.putIn, Balance.saveUp, Balance.date\n" + 
			"FROM Balance \n" + 
			"left join PutInOut on Balance.putInOut_id =  PutInOut.id \n" + 
			"having Balance.user_id= ?1 and date>?2 order by date desc", nativeQuery=true)
	List<Object[]> getBalanceHistoryByDate(Long id, SimpleDateFormat date);
}
