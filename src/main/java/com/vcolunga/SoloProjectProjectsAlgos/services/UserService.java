package com.vcolunga.SoloProjectProjectsAlgos.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.vcolunga.SoloProjectProjectsAlgos.models.LoginUser;
import com.vcolunga.SoloProjectProjectsAlgos.models.User;
import com.vcolunga.SoloProjectProjectsAlgos.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.isPresent() ? optionalUser.get() : null;
	}
	
	public User register(User newUser, BindingResult result) {
		//Optional to find if email is in use by a user
		Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());
		
		//if in use, reject.
		if (optionalUser.isPresent()) {
			result.rejectValue("email", "Exists", "A user with that email already exists!");
		}
		
		//reject if Confirm does not match Password
		if (!newUser.getPassword().equals(newUser.getConfirm())) {
			result.rejectValue("confirm", "Matches", "Confirm Paddword must match Password!");
		}
		
		//return null if it has been rejected
		if(result.hasErrors()) {
			return null;
		}
		
		//hash password
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		
		//set password to hashed pass
		newUser.setPassword(hashed);
		
		//save new User if all validations are passed
		return userRepository.save(newUser);
		
	}
	
	public User login(LoginUser login, BindingResult result) {
		Optional<User> optionalUser = userRepository.findByEmail(login.getEmail());
		
		if(optionalUser.isEmpty()) {
			result.rejectValue("email", "invalidCredentials", "The email or password you entered was incorrect");
			return null;
		}
		
		if (!BCrypt.checkpw(login.getPassword(), optionalUser.get().getPassword())) {
			result.rejectValue("password", "invalidCredentials", "The email or password you entered was incorrect");
			return null;
		}
		
		return optionalUser.get();
	}
}
