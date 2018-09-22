package budget.service;

import java.time.LocalDate;
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

	public PutInOutDto save(PutInOutDto putInOutDto) {
		PutInOut putInOut = putInOutRepository.save(toDomain(putInOutDto));
		if (Objects.nonNull(putInOut)) {

			JOptionPane.showMessageDialog(null, "Added to budget");
			return toDto(putInOut);

		} else {
			JOptionPane.showMessageDialog(null, "Cannot add");
			return putInOutDto;
		}

	}

	public Balance countTotalBalance(PutInOutDto putInOutDto) {

		Balance balance = balanceRepository.findFirstByUserIdOrderByIdDesc(putInOutDto.getUserDto().getId());
		Double totalBalance;

		if (Objects.isNull(balance)) {
			totalBalance = 0.0;
		} else {
			totalBalance = balance.getTotalBalance() + putInOutDto.getPutIn() - putInOutDto.getPutOut();
			// totalBalance = totalBalance - putInOutDto.getPutOut();
		}

		Balance balance2 = new Balance();
		balance2.setDate(LocalDate.now()); // czy powinna, to usawic czy joinowac
		balance2.setPutIn(putInOutDto.getPutIn());
		balance2.setTotalBalance(totalBalance);
		balance2.setSaveUp(balance.getSaveUp());
		Double saveUpBalance = totalBalance - balance.getSaveUp();
		balance2.setSaveBalance(saveUpBalance);
		User user = userRepository.findOne(balance.getUser().getId());
		balance2.setUser(user);
		Balance savedBalance = balanceRepository.save(balance2);
		return savedBalance;
	}

	private PutInOut toDomain(PutInOutDto putInOutDto) {
		PutInOut putInOut = new PutInOut();
		putInOut.setId(putInOutDto.getId());
		putInOut.setPutIn(putInOutDto.getPutIn());
		putInOut.setPutOut(putInOutDto.getPutOut());
		User user = userRepository.findOne(putInOutDto.getUserDto().getId());
		putInOut.setUser(user);
		return putInOut;
	}

	private PutInOutDto toDto(PutInOut putInOut) {
		PutInOutDto putInOutDto = new PutInOutDto();
		putInOutDto.setId(putInOut.getId());
		putInOutDto.setPutIn(putInOut.getPutIn());
		putInOutDto.setPutOut(putInOut.getPutOut());

		return putInOutDto;
	}
}
