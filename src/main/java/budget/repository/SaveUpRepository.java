package budget.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import budget.domain.SaveUp;


@Repository
public interface SaveUpRepository extends JpaRepository<SaveUp, Long>{
	SaveUp findFirstByUserIdOrderByIdDesc(Long id);
	
	@Query(value = "SELECT Balance.user_id, Balance.date,  Balance.saveUp, SaveUp.toSaveUp, Balance.totalSaved\n" + 
			"FROM Balance \n" + 
			"left join SaveUp on Balance.toSaveUp_id = SaveUp.id\n" + 
			"having user_id= ?1 and (saveUp is not null or toSaveUp is not null) order by date desc", nativeQuery=true)
			List<Object[]> getSaveUpHistory(Long id);
}
