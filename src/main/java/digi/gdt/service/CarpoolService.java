package digi.gdt.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.User;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.UserRepository;

@Service
public class CarpoolService {
  private CarpoolRepository carpoolRepo;
  private UserRepository userRepo;
  
  public CarpoolService(CarpoolRepository carpoolRepo, UserRepository userRepo) {
    this.carpoolRepo = carpoolRepo;
    this.userRepo = userRepo;
    }

  public List<Carpool> findAll() {
    return this.carpoolRepo.findAll();
  }

  public List<Carpool> findByDepartureAddress(String departureAddress) {
    return this.carpoolRepo.findByDepartureAddress(departureAddress);
  }

  public List<Carpool> findByDepartureAddressAndArrivalAddress(String departureAddress,
      String arrivalAddress) {
    return this.carpoolRepo.findByDepartureAddressAndArrivalAddress(departureAddress, arrivalAddress);
  }

  public List<Carpool> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
      String arrivalAddress, LocalDateTime date) {
    return this.carpoolRepo.findByDepartureAddressAndArrivalAddressAndDateGreaterThan(departureAddress, arrivalAddress,
        date);
  }
  public List<Carpool> listAllCarpoolByUserId (Integer userId) {
	  Optional<User> foundUser = userRepo.findById(userId);
	  if (foundUser.isEmpty()) {
		  throw new NotFoundException("Utilisateur avec l'Id" + userId + "non trouvé");
	  }
	  User user = foundUser.get();
	  return this.carpoolRepo.findByCreator(user);
  }

}
