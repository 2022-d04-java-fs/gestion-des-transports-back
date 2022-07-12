package digi.gdt.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import digi.gdt.dto.CreateCarpoolReservationDto;
import digi.gdt.dto.CreateUserDto;
import digi.gdt.dto.UserCredentialsDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.Role;
import digi.gdt.entity.RoleEnum;
import digi.gdt.entity.User;
import digi.gdt.exception.BadRequestException;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepo;
  private CarpoolRepository carpoolRepo;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepo, CarpoolRepository carpoolRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.carpoolRepo = carpoolRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @Transactional
  public CreateCarpoolReservationDto createCarpoolReservation(Integer user_id, Integer carpool_id) {
    Optional<User> foundUser = this.userRepo.findById(user_id);
    if (foundUser.isEmpty()) {
      throw new NotFoundException("Utilisateur avec l'id " + user_id + " non trouvé");
    }

    Optional<Carpool> foundCarpool = this.carpoolRepo.findById(carpool_id);
    if (foundCarpool.isEmpty()) {
      throw new NotFoundException("Covoiturage avec l'id " + carpool_id + " non trouvé");
    }

    Carpool carpool = foundCarpool.get();
    if (carpool.getAvailableSeats() <= 0) {
      throw new BadRequestException("Plus de place disponible dans ce covoiturage");
    }
    carpool.setAvailableSeats(carpool.getAvailableSeats() - 1);

    User user = foundUser.get();

    Set<Carpool> userCarpools = user.getCarpoolReservations();

    userCarpools.add(carpool);
    user.setCarpoolReservations(userCarpools);

    userRepo.save(user);
    return CreateCarpoolReservationDto.from(user);
  }

  @Transactional
  public User createUser(CreateUserDto createUserDto) {
    if (this.userRepo.existsByEmail(createUserDto.getEmail())) {
      throw new BadRequestException("There is an account with that email adress: " + createUserDto.getEmail());
    }

    User newUser = new User();
    newUser.setEmail(createUserDto.getEmail());
    newUser.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
    newUser.setLastname(createUserDto.getLastname());
    newUser.setFirstname(createUserDto.getFirstname());
    newUser.setIdentificationNumber(createUserDto.getLastname() + createUserDto.getFirstname());

    Set<Role> set = new HashSet<>();
    Role r1 = new Role();
    r1.setId(3);
    r1.setName(RoleEnum.COLLAB);
    set.add(r1);
    newUser.setRoles(set);

    return userRepo.save(newUser);
  }

  @Transactional
  public User login(UserCredentialsDto userCredentialsDto) {
    if (!this.userRepo.existsByEmail(userCredentialsDto.getEmail())) {
      throw new BadRequestException("There is no account with that email adress: " + userCredentialsDto.getEmail());
    }
    User foundedUser = this.userRepo.findByEmail(userCredentialsDto.getEmail());
    if (!passwordEncoder.matches(userCredentialsDto.getPassword(), foundedUser.getPassword())) {
      throw new BadRequestException("Invalid password");
    }
    return foundedUser;
  }
}
