package digi.gdt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import digi.gdt.entity.Carpool;

public interface CarpoolRepository extends JpaRepository<Carpool, Integer> {

  // Optional<List<Carpool>> findByDepartureAddress(String departureAddress);

}
