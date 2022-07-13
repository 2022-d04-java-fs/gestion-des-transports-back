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
import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.repository.PrivateVehicleRepository;
import digi.gdt.repository.UserRepository;

@Service
public class CarpoolService {
	private CarpoolRepository carpoolRepo;
	private UserRepository userRepo;
	private PrivateVehicleRepository privateVehicleRepo;

	public CarpoolService(CarpoolRepository carpoolRepo, UserRepository userRepo,
			PrivateVehicleRepository privateVehicleRepo) {
		this.carpoolRepo = carpoolRepo;
		this.userRepo = userRepo;
		this.privateVehicleRepo = privateVehicleRepo;
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
		newCarpool.setCreator(newVehicle.getOwner());
		newCarpool.setArrivalAddress(ArrivalAddress);
		newCarpool.setDepartureAddress(departureAddress);
		newCarpool.setDistance(distance);
		newCarpool.setDuration(duration);
		newCarpool.setAvailableSeats(availableSeats);
		newCarpool.setVehicle(newVehicle);
		newCarpool.setDate(date);

		this.carpoolRepo.save(newCarpool);

		return AddCarpoolDto.from(newCarpool);
	}

}
