package digi.gdt.service;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.CreateCarpoolReservationDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.User;
import digi.gdt.exception.BadRequestException;
import digi.gdt.exception.NotFoundException;
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

	public Optional<User> findById(Integer user_id) {
		// TODO Auto-generated method stub
		return this.userRepo.findById(user_id);
	}
}
