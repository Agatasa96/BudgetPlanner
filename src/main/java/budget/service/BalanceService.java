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

	public BalanceDto addToBalance(BalanceDto balanceDto) {
		if(Objects.isNull(balanceDto.getSaveUp())) {
			balanceDto.setSaveUp(0.0);
		}
		if(Objects.isNull(balanceDto.getPutInMonthly())) {
			balanceDto.setPutInMonthly(0.0);
		}
		if (balanceDto.getPutInMonthly() >= balanceDto.getSaveUp()) {
			Balance balanceExsist = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
			Double saveUp = 0.0;
			if (Objects.isNull(balanceExsist)) {
				balanceDto.setTotalBalance(countTotalBalance(balanceDto));
				balanceDto.setSaveBalance(countSaveUpBalance(balanceDto, saveUp));
				balanceDto.setTotalSaved(balanceDto.getSaveUp());
				return save(balanceDto);
			} else {
				balanceDto.setTotalBalance(countTotalBalance(balanceDto));
				Double totalSaved = countSaved(balanceDto);
				balanceDto.setTotalSaved(totalSaved);
				saveUp = totalSaved + balanceDto.getSaveUp();

				balanceDto.setSaveBalance(countSaveUpBalance(balanceDto, saveUp));
				return save(balanceDto);

			}
		} else {
			JOptionPane.showMessageDialog(null, "Pay in must be greater than save up");
			return null;
		}

	}

	private Double countSaved(BalanceDto balanceDto) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
		if (Objects.isNull(balance)) {
			Double total = balance.getTotalSaved() + balanceDto.getSaveUp();
			return total;
		} else {
			return null;
		}
	}

	private BalanceDto save(BalanceDto balanceDto) {

		Balance balance = balanceRepository.save(toDomain(balanceDto));

		if (Objects.nonNull(balance)) {
			JOptionPane.showMessageDialog(null, "Added to budget");
			return toDto(balance);
		}
		JOptionPane.showMessageDialog(null, "Cannot add to budget");
		return balanceDto;

	}

	private Double countSaveUpBalance(BalanceDto balanceDto, Double saveUp) {
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(balanceDto.getUserDto().getId());
		Double saveUpBalance = 0.0;
		if (Objects.isNull(balance)) {
			saveUpBalance = balanceDto.getPutInMonthly() - balanceDto.getSaveUp();
		} else {
			saveUpBalance = balance.getTotalBalance() - saveUp;
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

	public List<Object[]> getHistory(Long id) {
		return balanceRepository.getBalanceHistory(id);

	}

	public List<Object[]> getHistoryByDate(Long id, String date) {
		try {
			LocalDate sdf = LocalDate.parse(date);
			return balanceRepository.getBalanceHistoryByDate(id, sdf);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}

	}

	public List<Object[]> getHistoryByDay(Long id, String month, String day) {
		try {

			String startStr = LocalDate.now().getYear() + "-" + month + "-" + day + "T00:00:00";
			String endStr = LocalDate.now().getYear() + "-" + month + "-" + day + "T23:59:59";
			LocalDateTime start = LocalDateTime.parse(startStr);
			LocalDateTime end = LocalDateTime.parse(endStr);

			return balanceRepository.getBalanceHistoryByDay(id, start, end);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Add correct date");
			return null;
		}

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
		balance.setToSaveUp(null);
		balance.setUser(user);
		balance.setTotalSaved(balanceDto.getTotalSaved());
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
		balanceDto.setSaveUpDto(null);
		balanceDto.setTotalSaved(balance.getTotalSaved());
		return balanceDto;
	}

}
