package budget.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import budget.domain.User;
import budget.repository.UserRepository;

public class UserConverter implements Converter<String, User>{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User convert(String source) {
		User user = userRepo.findOne(Long.parseLong(source)); 
		// TODO Auto-generated method stub
		return user;
	}

}
