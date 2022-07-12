package digi.gdt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.User;

public interface CarpoolReservationRepository extends JpaRepository<CarpoolReservation, Integer> {

	Optional<CarpoolReservation> findByCarpoolAndPassenger(Carpool carpool, User user);

}
