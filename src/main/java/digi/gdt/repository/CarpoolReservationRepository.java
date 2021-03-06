package digi.gdt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.Users;

public interface CarpoolReservationRepository extends JpaRepository<CarpoolReservation, Integer> {

	List<CarpoolReservation> findAllByPassenger(Users user);

	List<CarpoolReservation> findAllByCarpool(Carpool carpool);

}
