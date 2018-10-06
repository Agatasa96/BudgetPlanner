package budget.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import budget.domain.Balance;
import budget.domain.PutInOut;
import budget.domain.User;
import budget.dto.BalanceDto;
import budget.dto.PutInOutDto;
import budget.repository.BalanceRepository;
import budget.repository.PutInOutRepository;
import budget.repository.UserRepository;

@Service
public class PutInOutService {

	private final PutInOutRepository putInOutRepository;
	private final UserRepository userRepository;
	private final BalanceRepository balanceRepository;

	public PutInOutService(PutInOutRepository putInOutRepository, UserRepository userRepository,
			BalanceRepository balanceRepository) {
		this.putInOutRepository = putInOutRepository;
		this.userRepository = userRepository;
		this.balanceRepository = balanceRepository;
	}

	public String save(PutInOutDto putInOutDto) {
		if (putInOutDto.getPutIn() == null) {
			putInOutDto.setPutIn(0.0);
		}
		if (putInOutDto.getPutOut() == null) {
			putInOutDto.setPutOut(0.0);
		}
		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(putInOutDto.getUserDto().getId());

		if (balance.getSaveBalance() - putInOutDto.getPutOut() < 0) {
			if (balance.getTotalBalance() - putInOutDto.getPutOut() < 0) {
				JOptionPane.showMessageDialog(null, "Not enough funds");
				return null;
			} else {
				JOptionPane.showMessageDialog(null, "Not enough funds. You can use saved money.");
				return "useSaved";
			}
		} else {
			PutInOut putInOut = putInOutRepository.save(toDomain(putInOutDto));
			if (Objects.nonNull(putInOut)) {

				JOptionPane.showMessageDialog(null, "Added to budget");
				return "added";

			}
		}

		JOptionPane.showMessageDialog(null, "Cannot add");
		return null;

	}

	public BalanceDto useSaved(PutInOutDto putInOutDto, Long id) {

		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(id);
		Double subtractBalance = putInOutDto.getPutOut() - balance.getSaveBalance();
		Double saveUp = balance.getTotalSaved() - subtractBalance;
		Double totalBalance = balance.getTotalBalance() - putInOutDto.getPutOut();
		Double saveBalance = totalBalance - saveUp;

		Balance balance2 = new Balance();
		balance2.setDate(LocalDateTime.now());
		balance2.setSaveBalance(saveBalance);
		balance2.setSaveUp(null);
		balance2.setTotalSaved(saveUp);
		balance2.setTotalBalance(totalBalance);
		User user = userRepository.findOne(id);
		balance2.setUser(user);
		PutInOut putInOut = putInOutRepository.save(toDomain(putInOutDto));
		if (Objects.nonNull(putInOut)) {
			balance2.setPutInOut(putInOut);
		}

		Balance savedBalance = balanceRepository.save(balance2);
		if (Objects.nonNull(savedBalance)) {
			JOptionPane.showMessageDialog(null, "Paid out");
			return toDto(savedBalance);
		} else {
			JOptionPane.showMessageDialog(null, "Cannot pay out");
			return null;
		}

	}

	public BalanceDto countTotalBalance(Long id) {

		PutInOut putInOut = putInOutRepository.findFirstByUserIdOrderByIdDesc(id);
		PutInOutDto putInOutDto = toDto(putInOut);

		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(id);

		Double totalBalance;

		if (Objects.isNull(balance)) {
			totalBalance = 0.0;
		} else {
			totalBalance = balance.getTotalBalance() + putInOutDto.getPutIn() - putInOutDto.getPutOut();

		}

		Balance balance2 = new Balance();
		balance2.setDate(LocalDateTime.now());
		balance2.setTotalBalance(totalBalance);
		balance2.setTotalSaved(balance.getTotalSaved());
		balance2.setSaveUp(null);
		Double saveUpBalance = totalBalance - balance.getTotalSaved();
		balance2.setSaveBalance(saveUpBalance);
		User user = userRepository.findOne(balance.getUser().getId());
		balance2.setUser(user);
		balance2.setPutInOut(putInOut);
		Balance savedBalance = balanceRepository.save(balance2);
		
		return toDto(savedBalance);
	}

	private PutInOut toDomain(PutInOutDto putInOutDto) {
		PutInOut putInOut = new PutInOut();
		putInOut.setId(putInOutDto.getId());
		putInOut.setPutIn(putInOutDto.getPutIn());
		putInOut.setPutOut(putInOutDto.getPutOut());
		putInOut.setDate(LocalDateTime.now());
		User user = userRepository.findOne(putInOutDto.getUserDto().getId());
		putInOut.setUser(user);
		return putInOut;
	}

	private PutInOutDto toDto(PutInOut putInOut) {
		PutInOutDto putInOutDto = new PutInOutDto();
		putInOutDto.setId(putInOut.getId());
		putInOutDto.setPutIn(putInOut.getPutIn());
		putInOutDto.setDate(LocalDateTime.now());

		putInOutDto.setPutOut(putInOut.getPutOut());

		return putInOutDto;
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
		balanceDto.setTotalSaved(balance.getTotalSaved());
		return balanceDto;
	}

}
