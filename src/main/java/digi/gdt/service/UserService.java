package digi.gdt.service;

import org.springframework.stereotype.Service;

import digi.gdt.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

}
