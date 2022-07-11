package digi.gdt.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import digi.gdt.entity.Carpool;
import digi.gdt.repository.CarpoolRepository;

@Service
public class CarpoolService {
  private CarpoolRepository carpoolRepo;

  public CarpoolService(CarpoolRepository carpoolRepo) {
    this.carpoolRepo = carpoolRepo;
  }

  public List<Carpool> findAll() {
    return this.carpoolRepo.findAll();
  }

  public List<Carpool> findByDepartureAddress(String departureAddress) {
    return this.carpoolRepo.findByDepartureAddress(departureAddress);
  }

  public List<Carpool> findByDepartureAddressAndArrivalAddress(String departureAddress,
      String arrivalAddress) {
    return this.carpoolRepo.findByDepartureAddressAndArrivalAddress(departureAddress, arrivalAddress);
  }

  public List<Carpool> findByDepartureAddressAndArrivalAddressAndDateGreaterThan(String departureAddress,
      String arrivalAddress, LocalDateTime date) {
    return this.carpoolRepo.findByDepartureAddressAndArrivalAddressAndDateGreaterThan(departureAddress, arrivalAddress,
        date);
  }

}
