package digi.gdt.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import digi.gdt.dto.CreateUserDto;
import digi.gdt.dto.UserCredentialsDto;
import digi.gdt.entity.Role;
import digi.gdt.entity.RoleEnum;
import digi.gdt.entity.Users;
import digi.gdt.exception.BadRequestException;
import digi.gdt.repository.UserRepository;

@Service
public class UserService {
	private UserRepository userRepo;

	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepo, PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public List<Users> findAll() {
		return this.userRepo.findAll();
	}

	@Transactional
	public Users createUser(CreateUserDto createUserDto) {
		if (this.userRepo.existsByEmail(createUserDto.getEmail())) {
			throw new BadRequestException("There is an account with that email adress: " + createUserDto.getEmail());
		}

		Users newUser = new Users();
		newUser.setEmail(createUserDto.getEmail());
		newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
		newUser.setLastname(createUserDto.getLastname());
		newUser.setFirstname(createUserDto.getFirstname());
		newUser.setIdentificationNumber(createUserDto.getLastname() + createUserDto.getFirstname());

		Set<Role> set = new HashSet<>();
		Role r1 = new Role();
		r1.setId(1);
		r1.setName(RoleEnum.COLLAB);
		set.add(r1);
		newUser.setRoles(set);

		return userRepo.save(newUser);
	}

	@Transactional
	public Users login(UserCredentialsDto userCredentialsDto) {
		if (!this.userRepo.existsByEmail(userCredentialsDto.getEmail())) {
			throw new BadRequestException(
					"There is no account with that email adress: " + userCredentialsDto.getEmail());
		}
		Users foundedUser = this.userRepo.findByEmail(userCredentialsDto.getEmail());
		if (!passwordEncoder.matches(userCredentialsDto.getPassword(), foundedUser.getPassword())) {
			throw new BadRequestException("Invalid password");
		}
		return foundedUser;
	}

	public Optional<Users> findById(Integer user_id) {
		return this.userRepo.findById(user_id);
	}
}
