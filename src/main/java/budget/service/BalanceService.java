package budget.service;

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

	public BalanceDto save(BalanceDto balanceDto) {
		Balance balance = balanceRepository.save(toDomain(balanceDto));
		if (Objects.nonNull(balance)) {
			JOptionPane.showMessageDialog(null, "Added to budget");
			return toDto(balance);
		}
		JOptionPane.showMessageDialog(null, "Cannot add to budget");
		return balanceDto;
	}

	private Balance toDomain(BalanceDto balanceDto) {
		Balance balance = new Balance();
		balance.setId(balanceDto.getId());
		balance.setDate(balanceDto.getDate());
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
		balanceDto.setDate(balance.getDate());
		balanceDto.setAfterShoppingBalance(balance.getAfterShoppingBalance());
		balanceDto.setSaveBalance(balance.getSaveBalance());
		balanceDto.setPutIn(balance.getPutIn());
		balanceDto.setSaveUp(balance.getSaveUp());
		balanceDto.setTotalBalance(balance.getTotalBalance());

		return balanceDto;
	}

}
