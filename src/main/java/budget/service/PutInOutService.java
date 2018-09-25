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
		if (putInOutDto.getPutIn() == null) {
			putInOutDto.setPutIn(0.0);
		}
		if (putInOutDto.getPutOut() == null) {
			putInOutDto.setPutOut(0.0);
		}
		putInOutDto.setSure(false);
		PutInOut putInOut = putInOutRepository.save(toDomain(putInOutDto));
		if (Objects.nonNull(putInOut)) {

			JOptionPane.showMessageDialog(null, "Added to budget");
			return toDto(putInOut);

		} else {
			JOptionPane.showMessageDialog(null, "Cannot add");
			return putInOutDto;
		}

	}

	public Balance countTotalBalance(Long id) {

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
		balance2.setDate(LocalDate.now());
		balance2.setTotalBalance(totalBalance);
		balance2.setSaveUp(balance.getSaveUp());
		Double saveUpBalance = totalBalance - balance.getSaveUp();
		balance2.setSaveBalance(saveUpBalance);
		User user = userRepository.findOne(balance.getUser().getId());
		balance2.setUser(user);
		balance2.setPutInOut(putInOut);
		Balance savedBalance = balanceRepository.save(balance2);
		return savedBalance;
	}

	private PutInOut toDomain(PutInOutDto putInOutDto) {
		PutInOut putInOut = new PutInOut();
		putInOut.setId(putInOutDto.getId());
		putInOut.setPutIn(putInOutDto.getPutIn());
		putInOut.setPutOut(putInOutDto.getPutOut());
		putInOut.setDate(LocalDate.now());
		User user = userRepository.findOne(putInOutDto.getUserDto().getId());
		putInOut.setUser(user);
		return putInOut;
	}

	private PutInOutDto toDto(PutInOut putInOut) {
		PutInOutDto putInOutDto = new PutInOutDto();
		putInOutDto.setId(putInOut.getId());
		putInOutDto.setPutIn(putInOut.getPutIn());
		putInOutDto.setDate(LocalDate.now());
		putInOutDto.setDate(putInOut.getDate());
		putInOutDto.setPutOut(putInOut.getPutOut());

		return putInOutDto;
	}
}
