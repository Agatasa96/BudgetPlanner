package budget.service;

import java.util.Objects;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Service;

import budget.domain.NewMonth;
import budget.domain.User;
import budget.dto.NewMonthDto;
import budget.repository.NewMonthRepository;
import budget.repository.UserRepository;

@Service
public class NewMonthService {

	private final NewMonthRepository newMonthRepository;
	

	public NewMonthService(NewMonthRepository newMonthRepository) {
		this.newMonthRepository = newMonthRepository;
	
	}

	public NewMonthDto save(NewMonthDto newMonthDto) {
		newMonthRepository.save(toDomain(newMonthDto));
		JOptionPane.showMessageDialog(null, "Added to budget");

		return newMonthDto;
	}

	public NewMonthDto addNewMonth(Long id) {
		return toDto(newMonthRepository.findFirstByUserIdOrderByDateDesc(id));

	}

	private NewMonth toDomain(NewMonthDto newMonthDto) {
		NewMonth newMonth = new NewMonth();
		newMonth.setId(newMonthDto.getId());
		newMonth.setPutIn(newMonthDto.getPutIn());
		newMonth.setToSave(newMonthDto.getToSave());
		newMonth.setDate(newMonthDto.getDate());
		

		return newMonth;
	}

	private NewMonthDto toDto(NewMonth newMonth) {
		NewMonthDto newMonthDto = new NewMonthDto();
		newMonthDto.setId(newMonth.getId());
		newMonthDto.setPutIn(newMonth.getPutIn());
		newMonthDto.setToSave(newMonth.getToSave());
		newMonthDto.setDate(newMonth.getDate());
		
		return newMonthDto;
	}

}
