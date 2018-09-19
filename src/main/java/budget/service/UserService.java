package budget.service;

import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;

import org.jboss.logging.Message;
import org.springframework.stereotype.Service;

import budget.domain.User;
import budget.dto.UserDto;
import budget.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public UserDto signUp(UserDto dto) {
		User user = userRepository.save(toDomain(dto));
		if (Objects.nonNull(user.getId())) {
			JOptionPane.showMessageDialog(null, "Done!");
			return toDto(user);

		}
		JOptionPane.showMessageDialog(null, "Cannot sign up!");
		return dto;

	}

	public UserDto logIn(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());
		if (Objects.isNull(user)) {
			JOptionPane.showMessageDialog(null, "Wrong email or password");
			return null;
		} else {
			if ((userDto.getPassword()).equals(user.getPassword())) {
				return toDto(user);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong email or password");
				return null;
			}
		}

	}

	private UserDto toDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setNickname(user.getNickname());
		return dto;
	}

	private User toDomain(UserDto userDto) {
		User user = new User();
		user.setId(userDto.getId());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setNickname(userDto.getNickname());
		return user;
	}

}
