package digi.gdt.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.AddCarpoolDto;
import digi.gdt.dto.AddPrivateVehicleDto;
import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.CarpoolStatusEnum;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.CarpoolReservationRepository;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;
import digi.gdt.repository.PrivateVehicleRepository;
import digi.gdt.repository.UserRepository;

@Service
public class CarpoolService {
	private CarpoolRepository carpoolRepo;
	private CarpoolReservationRepository carpoolResaRepo;
	private UserRepository userRepo;
	private PrivateVehicleRepository privateVehicleRepo;
	private EmailServiceImpl emailServ;

	public CarpoolService(CarpoolRepository carpoolRepo, UserRepository userRepo,
			PrivateVehicleRepository privateVehicleRepo, CarpoolReservationRepository carpoolResaRepo, EmailServiceImpl emailServ) {
		this.carpoolRepo = carpoolRepo;
		this.userRepo = userRepo;
		this.privateVehicleRepo = privateVehicleRepo;
		this.carpoolResaRepo = carpoolResaRepo;
		this.emailServ = emailServ;
	}

	public List<Carpool> findAll() {
		return this.carpoolRepo.findAll();
	}

	public List<Carpool> findByDepartureAddress(String departureAddress) {
		return this.carpoolRepo.findByDepartureAddress(departureAddress);
	}

	public List<Carpool> findByDepartureAddressAndArrivalAddress(String departureAddress, String arrivalAddress) {
		return this.carpoolRepo.findByDepartureAddressAndArrivalAddress(departureAddress, arrivalAddress);
	}

	public List<Carpool> listAllCarpoolByUserId(Integer userId) {
		Optional<Users> foundUser = userRepo.findById(userId);
		if (foundUser.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'Id" + userId + "non trouvé");
		}
		Users user = foundUser.get();
		return this.carpoolRepo.findByCreator(user);
	}

	public List<Carpool> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
			String arrivalAddress, LocalDateTime date) {
		return this.carpoolRepo.findByDepartureAddressAndArrivalAddressAndDateGreaterThan(departureAddress,
				arrivalAddress, date);
	}

	@Transactional
	public AddCarpoolDto createCarpool(LocalDateTime date, String departureAddress, String ArrivalAddress,
			BigDecimal distance, BigInteger duration, Integer availableSeats, Integer creatorId,
			AddPrivateVehicleDto vehicleDto) {
		Optional<Users> creator = this.userRepo.findById(creatorId);

		if (creator.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + creatorId + " non trouvé");
		}

		PrivateVehicle newVehicle = new PrivateVehicle();
		Optional<PrivateVehicle> existingVehicle = this.privateVehicleRepo
				.findByLicensePlate(vehicleDto.getLicensePlate());

		if (existingVehicle.isPresent()) {
			newVehicle = existingVehicle.get();
		} else {
			newVehicle.setLicensePlate(vehicleDto.getLicensePlate());
			newVehicle.setBrand(vehicleDto.getBrand());
			newVehicle.setModel(vehicleDto.getModel());
			newVehicle.setOwner(creator.get());
			this.privateVehicleRepo.save(newVehicle);
		}

		Carpool newCarpool = new Carpool();
		newCarpool.setCreator(creator.get());
		newCarpool.setArrivalAddress(ArrivalAddress);
		newCarpool.setDepartureAddress(departureAddress);
		newCarpool.setDistance(distance);
		newCarpool.setDuration(duration);
		newCarpool.setAvailableSeats(availableSeats);
		newCarpool.setVehicle(newVehicle);
		newCarpool.setDate(date);
		newCarpool.setStatus(CarpoolStatusEnum.OK);

		this.carpoolRepo.save(newCarpool);

		return AddCarpoolDto.from(newCarpool);
	}

    public CarpoolDto cancelCarpool(Integer carpool_id) {
        Optional<Carpool> foundCarpool = this.carpoolRepo.findById(carpool_id);
		if (foundCarpool.isEmpty()){
			throw new NotFoundException("Carpool avec l'id " + carpool_id + " non trouvé");
		}
		Carpool carpool = foundCarpool.get();
		String emailBody = carpool.getCreator().getFirstname() + " " + carpool.getCreator().getLastname() + " vient d'annuler son annonce de covoiturage " + carpool.getDepartureAddress() + " --> " + carpool.getArrivalAddress() + " du " + carpool.getDate().toString();
		List<CarpoolReservation> reservations = this.carpoolResaRepo.findAllByCarpool(carpool);
		reservations.forEach(resa -> {
			resa.setReservationStatus(CarpoolStatusEnum.CANCELLED);
			this.carpoolResaRepo.save(resa);
			this.emailServ.sendSimpleMessage(resa.getPassenger().getEmail(), "Annulation de covoiturage", emailBody);
		});
		carpool.setStatus(CarpoolStatusEnum.CANCELLED);
		this.carpoolRepo.save(carpool);
		this.emailServ.sendSimpleMessage(carpool.getCreator().getEmail(), "Annulation de covoiturage", "Votre annonce de covoiturage "+ carpool.getDepartureAddress() + " --> " + carpool.getArrivalAddress() + " du " + carpool.getDate().toString() + " a bien été annulée.");
		return CarpoolDto.from(carpool);
    }

}
