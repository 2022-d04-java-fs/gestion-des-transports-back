package digi.gdt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.CarpoolReservationDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.CarpoolStatusEnum;
import digi.gdt.entity.Users;
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
	private EmailServiceImpl emailSrv;

	public CarpoolReservationService(CarpoolReservationRepository carpoolResaRepo, UserRepository userRepo,
			CarpoolRepository carpoolRepo, EmailServiceImpl emailSrv) {
		this.carpoolResaRepo = carpoolResaRepo;
		this.userRepo = userRepo;
		this.carpoolRepo = carpoolRepo;
		this.emailSrv = emailSrv;
	}

	public CarpoolReservationDto createCarpoolReservation(Integer user_id, Integer carpool_id) {
		Optional<Users> foundUser = this.userRepo.findById(user_id);
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
		Users user = foundUser.get();

		CarpoolReservation newReservation = new CarpoolReservation();
		newReservation.setCarpool(carpool);
		newReservation.setPassenger(user);
		newReservation.setReservationStatus(CarpoolStatusEnum.OK);

		carpoolResaRepo.save(newReservation);
		return CarpoolReservationDto.from(newReservation);
	}

	public CarpoolReservationDto cancelCarpoolReservation(Integer reservation_id) {
		Optional<CarpoolReservation> foundCarpoolReservation = this.carpoolResaRepo.findById(reservation_id);
		if (foundCarpoolReservation.isEmpty()) {
			throw new NotFoundException("Réservation de covoiturage " + reservation_id + " non trouvée");
		}
		CarpoolReservation carpoolResa = foundCarpoolReservation.get();
		if (carpoolResa.getReservationStatus() == CarpoolStatusEnum.CANCELLED) {
			throw new BadRequestException("la réservation est déjà annulée");
		}

		carpoolResa.setReservationStatus(CarpoolStatusEnum.CANCELLED);
		carpoolResaRepo.save(carpoolResa);
		Carpool carpool = carpoolResa.getCarpool();
		carpool.setAvailableSeats(carpool.getAvailableSeats() + 1);
		carpoolRepo.save(carpool);
		this.emailSrv.sendSimpleMessage(carpoolResa.getPassenger().getEmail(), "Annulation de votre réservation", "Votre réservation pour le covoiturage "+ carpool.getDepartureAddress() + " --> " + carpool.getArrivalAddress() + " du " + carpool.getDate().toString() + " a bien été annulée.");
		this.emailSrv.sendSimpleMessage(carpool.getCreator().getEmail(), "Un passager a annulé sa réservation", carpoolResa.getPassenger().getFirstname() + " " + carpoolResa.getPassenger().getLastname() + " vient d'annuler sa réservation pour votre covoiturage  "+ carpool.getDepartureAddress() + " --> " + carpool.getArrivalAddress() + " du " + carpool.getDate().toString());
		return CarpoolReservationDto.from(carpoolResa);
	}

	public List<CarpoolReservationDto> getCarpoolReservationsByUserId(Integer user_id) {
		Optional<Users> foundUser = userRepo.findById(user_id);
		if (foundUser.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + user_id + " non trouvé");
		}
		return carpoolResaRepo.findAllByPassenger(foundUser.get()).stream().map(resa -> CarpoolReservationDto.from(resa))
				.toList();
	}
}
