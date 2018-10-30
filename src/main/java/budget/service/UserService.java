package budget.service;

import java.util.Base64;
import java.util.Objects;
import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;
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
			String pass = Base64.getEncoder().encodeToString(userDto.getPassword().getBytes());
			if (user.getPassword().equals(pass)) {
				return toDto(user);
			} else {
				JOptionPane.showMessageDialog(null, "Wrong email or password");
				return null;
			}
		}

	}

	public UserDto getUserData(UserDto userDto) {
		User user = userRepository.findOne(userDto.getId());
		if (Objects.isNull(user)) {
			JOptionPane.showMessageDialog(null, "Cannot load user");
			return null;
		} else {
			return toDto(user);
		}

	}

	public UserDto editNickname(UserDto editNickname, UserDto userDto) {
		User foundUser = userRepository.findOne(userDto.getId());
		if (Objects.nonNull(foundUser)) {
			if (editNickname.getNickname().length() >= 5 && editNickname.getNickname().length() <= 15) {
				foundUser.setNickname(editNickname.getNickname());
				userRepository.save(foundUser);
				return toDto(foundUser);
			} else {
				JOptionPane.showMessageDialog(null, "Nickname length must be beetween 5 and 15!");
				return null;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cannot edit nickname");
			return null;
		}

	}

	public UserDto editPassword(UserDto editUserDto, UserDto userDto) {
		User foundUser = userRepository.findOne(userDto.getId());
		if (Objects.nonNull(foundUser)) {
			if (editUserDto.getPassword().trim().length() >= 5) {
				String pass = Base64.getEncoder().encodeToString(editUserDto.getPassword().getBytes());
				
				foundUser.setPassword(pass);
				userRepository.save(foundUser);
				return toDto(foundUser);
			} else {
				JOptionPane.showMessageDialog(null, "Password length must be beetween 5 and 15!");
				return null;
			}

		} else {
			JOptionPane.showMessageDialog(null, "Cannot edit password");
			return null;
		}

	}

	public String delete(UserDto userDto) {
		User foundUser = userRepository.findOne(userDto.getId());
		if (Objects.nonNull(foundUser)) {

			userRepository.delete(foundUser);
			JOptionPane.showMessageDialog(null, "User deleted");
			return "y";

		} else {
			JOptionPane.showMessageDialog(null, "Cannot delete user");
			return "n";
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
		user.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
		user.setEmail(userDto.getEmail());
		user.setNickname(userDto.getNickname());
		return user;
	}

}
