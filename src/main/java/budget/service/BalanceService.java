package budget.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import budget.domain.Balance;

import budget.domain.User;
import budget.dto.BalanceDto;
import budget.dto.UserDto;
import budget.repository.BalanceRepository;

import budget.repository.UserRepository;

@Service
public class BalanceService {

	private final BalanceRepository balanceRepository;
	private final UserRepository userRepository;

	public BalanceService(BalanceRepository balanceRepository, UserRepository userRepository) {
		this.balanceRepository = balanceRepository;
		this.userRepository = userRepository;
	}

	public BalanceDto lastBalance(UserDto userDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(userDto.getId());
		return toDto(balance);
	}

	public BalanceDto save(BalanceDto balanceDto) {
		balanceDto.setTotalBalance(countTotalBalance(balanceDto));
		balanceDto.setSaveBalance(countSaveUpBalance(balanceDto));

		Balance balance = balanceRepository.save(toDomain(balanceDto));
		if (Objects.nonNull(balance)) {
			JOptionPane.showMessageDialog(null, "Added to budget");
			return toDto(balance);
		}
		JOptionPane.showMessageDialog(null, "Cannot add to budget");
		return balanceDto;
	}

	private Double countSaveUpBalance(BalanceDto balanceDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
		Double saveUpBalance = 0.0;
		if (Objects.isNull(balance)) {
			saveUpBalance = balanceDto.getPutInMonthly() - balanceDto.getSaveUp();
		} else {
			saveUpBalance = balance.getTotalBalance() - balanceDto.getSaveUp();
		}
		return saveUpBalance;
	}

	private Double countTotalBalance(BalanceDto balanceDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
		Double totalBalance = 0.0;

		if (Objects.isNull(balance)) {
			totalBalance = balanceDto.getPutInMonthly();
		} else {
			totalBalance = balance.getTotalBalance() + balanceDto.getPutInMonthly();
		}

		return totalBalance;
	}

	private Balance toDomain(BalanceDto balanceDto) {
		Balance balance = new Balance();
		balance.setId(balanceDto.getId());
		balance.setDate(LocalDateTime.now());
		balance.setAfterShoppingBalance(balanceDto.getAfterShoppingBalance());
		balance.setSaveBalance(balanceDto.getSaveBalance());
		balance.setPutInMonthly(balanceDto.getPutInMonthly());
		balance.setSaveUp(balanceDto.getSaveUp());
		balance.setTotalBalance(balanceDto.getTotalBalance());
		User user = userRepository.findOne(balanceDto.getUserDto().getId());
		balance.setPutInOut(null);
		balance.setUser(user);
		return balance;
	}

	private BalanceDto toDto(Balance balance) {
		BalanceDto balanceDto = new BalanceDto();

		balanceDto.setId(balance.getId());
		balanceDto.setDate(LocalDateTime.now());
		balanceDto.setAfterShoppingBalance(balance.getAfterShoppingBalance());
		balanceDto.setSaveBalance(balance.getSaveBalance());
		balanceDto.setPutInMonthly(balance.getPutInMonthly());
		balanceDto.setSaveUp(balance.getSaveUp());
		balanceDto.setTotalBalance(balance.getTotalBalance());
		balanceDto.setPutInOutDto(null);
		return balanceDto;
	}

	public List<Object[]> getHistory(Long id) {
		return balanceRepository.getBalanceHistory(id);
		
	}
	
	public List<Object[]> getHistoryByDate(Long id, LocalDate date) {
		return balanceRepository.getBalanceHistoryByDate(id, date);
		
	}

}
