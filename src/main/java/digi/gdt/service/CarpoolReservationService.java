package digi.gdt.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.CarpoolReservationDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.CarpoolReservationStatusEnum;
import digi.gdt.entity.User;
import digi.gdt.exception.BadRequestException;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.CarpoolReservationRepository;
import digi.gdt.repository.UserRepository;

@Service
public class CarpoolReservationService {

	private CarpoolReservationRepository carpoolResaRepo;
	private UserRepository userRepo;
	private CarpoolRepository carpoolRepo;

	public CarpoolReservationService(CarpoolReservationRepository carpoolResaRepo, UserRepository userRepo,
			CarpoolRepository carpoolRepo) {
		this.carpoolResaRepo = carpoolResaRepo;
		this.userRepo = userRepo;
		this.carpoolResaRepo = carpoolResaRepo;
	}

	public CarpoolReservationDto createCarpoolReservation(Integer user_id, Integer carpool_id) {
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

		CarpoolReservation newReservation = new CarpoolReservation();
		newReservation.setCarpool(carpool);
		newReservation.setPassenger(user);
		newReservation.setReservationStatus(CarpoolReservationStatusEnum.OK);

		carpoolResaRepo.save(newReservation);
		return CarpoolReservationDto.from(newReservation);
	}

	public CarpoolReservationDto cancelCarpoolReservation(Integer user_id, Integer carpool_id) {
		Optional<User> foundUser = this.userRepo.findById(user_id);
		if (foundUser.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + user_id + " non trouvé");
		}

		Optional<Carpool> foundCarpool = this.carpoolRepo.findById(carpool_id);
		if (foundCarpool.isEmpty()) {
			throw new NotFoundException("Covoiturage avec l'id " + carpool_id + " non trouvé");
		}
		Optional<CarpoolReservation> foundCarpoolReservation = this.carpoolResaRepo
				.findByCarpoolAndPassenger(foundCarpool.get(), foundUser.get());
		if (foundCarpoolReservation.isEmpty()) {
			throw new NotFoundException(
					"Réservation du covoiturage " + carpool_id + " par l'utilisateur " + user_id + " non trouvé");
		}
		CarpoolReservation carpoolResa = foundCarpoolReservation.get();
		if (carpoolResa.getReservationStatus() == CarpoolReservationStatusEnum.CANCELLED) {
			throw new BadRequestException("la réservation est déjà annulée");
		}
		carpoolResa.setReservationStatus(CarpoolReservationStatusEnum.CANCELLED);
		Carpool carpool = foundCarpool.get();
		carpool.setAvailableSeats(carpool.getAvailableSeats() + 1);
		return CarpoolReservationDto.from(carpoolResa);
	}

}
