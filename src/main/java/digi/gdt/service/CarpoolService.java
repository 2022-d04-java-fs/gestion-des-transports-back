package digi.gdt.service;

import java.util.List;
import java.util.Optional;

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

  // public Optional<List<Carpool>> findByDepartureAddress(String
  // departureAddress) {
  // return this.carpoolRepo.findByDepartureAddress(departureAddress);
  // }
}
