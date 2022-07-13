package digi.gdt.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import digi.gdt.dto.AddPrivateVehicleDto;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;
import digi.gdt.exception.NotFoundException;
import digi.gdt.repository.PrivateVehicleRepository;
import digi.gdt.repository.UserRepository;

@Service
public class PrivateVehicleService {

	private PrivateVehicleRepository privateVehicleRepo;
	private UserRepository userRepo;

	public PrivateVehicleService(PrivateVehicleRepository privateVehicleRepo, UserRepository userRepo) {
		this.privateVehicleRepo = privateVehicleRepo;
		this.userRepo = userRepo;
	}

	@Transactional
	public AddPrivateVehicleDto createPrivateVehicle(Integer user_id, String licensePlate, String brand, String model) {
		Optional<PrivateVehicle> existingVehicle = this.privateVehicleRepo.findByLicensePlate(licensePlate);
		if (existingVehicle.isPresent()) {
			return AddPrivateVehicleDto.from(existingVehicle.get());
		}
		Optional<Users> foundUser = this.userRepo.findById(user_id);
		if (foundUser.isEmpty()) {
			throw new NotFoundException("Utilisateur avec l'id " + user_id + " non trouvé");
		}
		PrivateVehicle newVehicle = new PrivateVehicle();
		newVehicle.setLicensePlate(licensePlate);
		newVehicle.setBrand(brand);
		newVehicle.setModel(model);
		newVehicle.setOwner(foundUser.get());
		this.privateVehicleRepo.save(newVehicle);
		return AddPrivateVehicleDto.from(newVehicle);
	}

}
