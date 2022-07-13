package digi.gdt.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.AddCarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
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

	public Optional<List<Carpool>> findByDepartureAddress(String departureAddress) {
		return this.carpoolRepo.findByDepartureAddress(departureAddress);
	}

	public Optional<List<Carpool>> findByDepartureAddressAndArrivalAddress(String departureAddress,
			String arrivalAddress) {
		return this.carpoolRepo.findByDepartureAddressAndArrivalAddress(departureAddress, arrivalAddress);
	}

	public Optional<List<Carpool>> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
			String arrivalAddress, LocalDateTime date) {
		return this.carpoolRepo.findByDepartureAddressAndArrivalAddressAndDateGreaterThan(departureAddress,
				arrivalAddress, date);
	}

	@Transactional
	public AddCarpoolDto createCarpool(LocalDateTime date, String departureAddress, String ArrivalAddress,
			BigDecimal distance, BigInteger duration, Integer availableSeats, Integer creatorId,
			PrivateVehicle vehicle) {
		Optional<User> creator = this.userRepo.findById(creatorId);
		if (creator.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + creatorId + " non trouvé");
		}
		Carpool newCarpool = new Carpool();
		newCarpool.setCreator(creator.get());
		newCarpool.setArrivalAddress(ArrivalAddress);
		newCarpool.setDepartureAddress(departureAddress);
		newCarpool.setDistance(distance);
		newCarpool.setDuration(duration);
		newCarpool.setAvailableSeats(availableSeats);
		newCarpool.setVehicle(vehicle);
		newCarpool.setDate(date);
		this.carpoolRepo.save(newCarpool);
		return AddCarpoolDto.from(newCarpool);
	}

}
