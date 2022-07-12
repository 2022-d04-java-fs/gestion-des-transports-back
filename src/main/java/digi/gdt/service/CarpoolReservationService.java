package digi.gdt.service;

import java.util.List;
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
		this.carpoolRepo = carpoolRepo;
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
		carpoolRepo.save(carpool);
		User user = foundUser.get();

		CarpoolReservation newReservation = new CarpoolReservation();
		newReservation.setCarpool(carpool);
		newReservation.setPassenger(user);
		newReservation.setReservationStatus(CarpoolReservationStatusEnum.OK);

		carpoolResaRepo.save(newReservation);
		return CarpoolReservationDto.from(newReservation);
	}

	public CarpoolReservationDto cancelCarpoolReservation(Integer reservation_id) {
		Optional<CarpoolReservation> foundCarpoolReservation = this.carpoolResaRepo.findById(reservation_id);
		if (foundCarpoolReservation.isEmpty()) {
			throw new NotFoundException("Réservation de covoiturage " + reservation_id + " non trouvée");
		}
		CarpoolReservation carpoolResa = foundCarpoolReservation.get();
		if (carpoolResa.getReservationStatus() == CarpoolReservationStatusEnum.CANCELLED) {
			throw new BadRequestException("la réservation est déjà annulée");
		}

		carpoolResa.setReservationStatus(CarpoolReservationStatusEnum.CANCELLED);
		carpoolResaRepo.save(carpoolResa);
		Carpool carpool = carpoolResa.getCarpool();
		carpool.setAvailableSeats(carpool.getAvailableSeats() + 1);
		carpoolRepo.save(carpool);
		return CarpoolReservationDto.from(carpoolResa);
	}

	public List<CarpoolReservationDto> getCarpoolReservationsByUserId(Integer user_id) {
		Optional<User> foundUser = userRepo.findById(user_id);
		if (foundUser.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + user_id + " non trouvé");
		}
		return carpoolResaRepo.findByPassenger(foundUser.get()).stream().map(resa -> CarpoolReservationDto.from(resa))
				.toList();
	}
}
