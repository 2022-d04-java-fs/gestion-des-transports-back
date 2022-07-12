package digi.gdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.User;

public interface CarpoolReservationRepository extends JpaRepository<CarpoolReservation, Integer> {

	List<CarpoolReservation> findByPassenger(User user);

}
