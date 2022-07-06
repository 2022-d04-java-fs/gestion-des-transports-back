package digi.gdt.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.User;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepo;
  private CarpoolRepository carpoolRepo;

  public UserService(UserRepository userRepo, CarpoolRepository carpoolRepo) {
    this.userRepo = userRepo;
    this.carpoolRepo = carpoolRepo;
  }

  // TODO : userDto pour éviter la boucle -> erreur jackson
  // public User createCarpoolReservation(Integer user_id, Integer carpool_id) {
  // Optional<User> foundUser = this.userRepo.findById(user_id);
  // System.out.println(foundUser.get());
  // if (foundUser.isEmpty()) {
  // System.out.println("utilisateur non trouvé");
  // }

  // Optional<Carpool> foundCarpool = this.carpoolRepo.findById(carpool_id);
  // System.out.println(foundCarpool.get());
  // if (foundCarpool.isEmpty()) {
  // System.out.println("covoiturage non trouvé");
  // }

  // User user = foundUser.get();
  // Set<Carpool> userCarpools = user.getCarpoolReservations();
  // Carpool carpool = foundCarpool.get();
  // userCarpools.add(carpool);
  // user.setCarpoolReservations(userCarpools);

  // return userRepo.save(user);
  // }
}
