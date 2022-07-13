package digi.gdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.PrivateVehicle;

public interface PrivateVehicleRepository extends JpaRepository<PrivateVehicle, Integer> {

	Optional<PrivateVehicle> findByLicensePlate(String licensePlate);
}
