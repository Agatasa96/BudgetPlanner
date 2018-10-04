package budget.service;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import budget.domain.Balance;
import budget.domain.SaveUp;
import budget.domain.User;
import budget.dto.BalanceDto;
import budget.dto.SaveUpDto;
import budget.repository.BalanceRepository;
import budget.repository.SaveUpRepository;
import budget.repository.UserRepository;

@Service
public class SaveUpService {

	private final BalanceRepository balanceRepository;
	private final SaveUpRepository saveUpRepository;
	private final UserRepository userRepository;

	public SaveUpService(BalanceRepository balanceRepository, SaveUpRepository saveUpRepository,
			UserRepository userRepository) {

		this.balanceRepository = balanceRepository;
		this.saveUpRepository = saveUpRepository;
		this.userRepository = userRepository;
	}

	public SaveUpDto saveUp(SaveUpDto saveUpDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(saveUpDto.getUserDto().getId());

		if (saveUpDto.getToSaveUp() > balance.getSaveBalance()) {
			JOptionPane.showMessageDialog(null, "Not enough funds");
			return null;
		}

		else {
			SaveUp saveUp = saveUpRepository.save(toDomain(saveUpDto));
			JOptionPane.showMessageDialog(null, "Saved");
			return toDto(saveUp);
		}
	}
	
	/*private BalanceDto countBalance(BalanceDto balanceDto, Long id) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(id);
		if(Objects.nonNull(balance)) {
			Double saveUp = balance.getSaveUp(); 
			//Balance balance2 = new Balance();
		}
	}*/

	private SaveUpDto toDto(SaveUp saveUp) {
		SaveUpDto saveUpDto = new SaveUpDto();
		saveUpDto.setId(saveUp.getId());
		saveUpDto.setDate(LocalDateTime.now());
		saveUpDto.setToSaveUp(saveUp.getToSaveUp());
		return saveUpDto;
	}

	private SaveUp toDomain(SaveUpDto saveUpDto) {
		SaveUp saveUp = new SaveUp();
		saveUp.setId(saveUpDto.getId());
		saveUp.setDate(LocalDateTime.now());
		saveUp.setToSaveUp(saveUpDto.getToSaveUp());
		User user = userRepository.findOne(saveUpDto.getUserDto().getId());
		saveUp.setUser(user);
		return saveUp;
	}
}
