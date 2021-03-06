package budget.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

	public String saveUp(SaveUpDto saveUpDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(saveUpDto.getUserDto().getId());

		if (saveUpDto.getToSaveUp() > balance.getSaveBalance()) {
			JOptionPane.showMessageDialog(null, "Not enough funds");
			return null;
		}

		else {
			SaveUp saveUp = saveUpRepository.save(toDomain(saveUpDto));
			System.out.println(saveUp.getUser().getId());
			JOptionPane.showMessageDialog(null, "Saved");
			return "s";
		}
	}

	public BalanceDto countBalance(Long id) {

		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(id);
		if (Objects.nonNull(balance)) {
			SaveUp saveUp = saveUpRepository.findFirstByUserIdOrderByIdDesc(id);
			Double totalSaved = balance.getTotalSaved() + saveUp.getToSaveUp();
			Double savedBalance = balance.getTotalBalance() - totalSaved;
			Balance balance2 = new Balance();
			balance2.setDate(LocalDateTime.now());
			balance2.setTotalSaved(totalSaved);
			balance2.setSaveBalance(savedBalance);
			balance2.setPutInMonthly(null);
			balance2.setPutInOut(null);
			balance2.setSaveUp(null);
			balance2.setToSaveUp(saveUp);
			User user = userRepository.findOne(saveUp.getUser().getId());
			balance2.setUser(user);
			balance2.setTotalBalance(balance.getTotalBalance());
			Balance saved = balanceRepository.save(balance2);
			return toBalanceDto(saved);

		}
		return null;
	}

	public List<Object[]> getHistory(Long id) {
		return saveUpRepository.getSaveUpHistory(id);

	}

	public List<Object[]> getHistoryByDate(Long id, String date) {
		try {
			LocalDate sdf = LocalDate.parse(date);
			return saveUpRepository.getSaveUpHistoryByDate(id, sdf);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}

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

	private BalanceDto toBalanceDto(Balance balance) {
		BalanceDto balanceDto = new BalanceDto();

		balanceDto.setId(balance.getId());
		balanceDto.setDate(balance.getDate());
		balanceDto.setSaveBalance(balance.getSaveBalance());
		balanceDto.setPutInMonthly(balance.getPutInMonthly());
		balanceDto.setSaveUp(balance.getSaveUp());
		balanceDto.setTotalBalance(balance.getTotalBalance());
		balanceDto.setTotalSaved(balance.getTotalSaved());
		return balanceDto;
	}

}
