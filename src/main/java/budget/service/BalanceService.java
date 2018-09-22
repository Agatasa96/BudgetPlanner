package budget.service;

import java.time.LocalDate;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import budget.domain.Balance;

import budget.domain.User;
import budget.dto.BalanceDto;

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

	public BalanceDto lastBalance(BalanceDto balanceDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
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
			saveUpBalance = balanceDto.getPutIn() - balanceDto.getSaveUp();
		} else {
			saveUpBalance = balance.getTotalBalance() - balanceDto.getSaveUp();
		}
		return saveUpBalance;
	}

	private Double countTotalBalance(BalanceDto balanceDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
		Double totalBalance = 0.0;

		if (Objects.isNull(balance)) {
			totalBalance = balanceDto.getPutIn();
		} else {
			totalBalance = balance.getTotalBalance() + balanceDto.getPutIn();
		}

		return totalBalance;
	}

	private Balance toDomain(BalanceDto balanceDto) {
		Balance balance = new Balance();
		balance.setId(balanceDto.getId());
		balance.setDate(LocalDate.now());
		balance.setAfterShoppingBalance(balanceDto.getAfterShoppingBalance());
		balance.setSaveBalance(balanceDto.getSaveBalance());
		balance.setPutIn(balanceDto.getPutIn());
		balance.setSaveUp(balanceDto.getSaveUp());
		balance.setTotalBalance(balanceDto.getTotalBalance());
		User user = userRepository.findOne(balanceDto.getUserDto().getId());

		balance.setUser(user);
		return balance;
	}

	private BalanceDto toDto(Balance balance) {
		BalanceDto balanceDto = new BalanceDto();

		balanceDto.setId(balance.getId());
		balanceDto.setDate(LocalDate.now());
		balanceDto.setAfterShoppingBalance(balance.getAfterShoppingBalance());
		balanceDto.setSaveBalance(balance.getSaveBalance());
		balanceDto.setPutIn(balance.getPutIn());
		balanceDto.setSaveUp(balance.getSaveUp());
		balanceDto.setTotalBalance(balance.getTotalBalance());

		return balanceDto;
	}

}
