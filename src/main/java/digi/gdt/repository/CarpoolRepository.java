package digi.gdt.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Carpool;

public interface CarpoolRepository extends JpaRepository<Carpool, Integer> {

  Optional<Carpool> findById(Integer id);

  List<Carpool> findByDepartureAddress(String departureAddress);

  List<Carpool> findByDepartureAddressAndArrivalAddress(String departureAddress, String arrivalAddress);

  List<Carpool> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
      String arrivalAddress,
      LocalDateTime date);

}
