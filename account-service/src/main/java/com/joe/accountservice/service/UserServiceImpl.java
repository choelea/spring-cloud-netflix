package com.joe.accountservice.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.joe.accountservice.model.Role;
import com.joe.accountservice.model.User;
import com.joe.accountservice.repository.RoleRepository;
import com.joe.accountservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User createBuyer(User user) {
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.getByCode("BUYER"));
		user.setRoles(roles);
        
		user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
	}

	@Override
	public User get(String username, String password) {
		User user = userRepository.findByUsername(username);		
		if(user!=null && passwordEncoder.matches(password, user.getPassword())){
			return user;
		}
		return null;
	}

	@Override
	public List<User> find() {
		return userRepository.findAll();
	}
}
