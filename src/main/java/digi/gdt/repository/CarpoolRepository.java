package digi.gdt.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.Users;

public interface CarpoolRepository extends JpaRepository<Carpool, Integer> {

  List<Carpool> findByDepartureAddress(String departureAddress);

  List<Carpool> findByDepartureAddressAndArrivalAddress(String departureAddress, String arrivalAddress);

  List<Carpool> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
      String arrivalAddress,
      LocalDateTime date);

  List<Carpool> findByCreator(Users user);


}
