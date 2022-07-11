package digi.gdt.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.service.CarpoolService;

@RestController
@RequestMapping("carpools")
@CrossOrigin(origins = "*")
public class CarpoolController {
  private CarpoolService carpoolService;

  public CarpoolController(CarpoolService carpoolService) {
    this.carpoolService = carpoolService;
  }

  /**
   * *GET* - Liste de tous les covoiturages
   * 'GET http://localhost:8080/api/carpools'
   * 
   * @return List<CarpoolDto>
   */
  @GetMapping
  public List<CarpoolDto> listAll() {
    return this.carpoolService.findAll().stream().map(CarpoolDto::from).toList();
  }

  /**
   * *GET* - Liste des covoiturages en fonction de leur ville de départ
   * 'GET http://localhost:8080/api/carpools?departureAddress='
   * 
   * 404 - covoiturage non trouvé
   * 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress" })
  public ResponseEntity<?> listAllByDepartureAddress(@RequestParam String departureAddress) {
    List<Carpool> carpoolsList = this.carpoolService.findByDepartureAddress(departureAddress);
    if (carpoolsList.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun covoiturage trouvé");
    } else {
      List<CarpoolDto> carpools = carpoolsList.stream().map(CarpoolDto::from).toList();
      return ResponseEntity.ok(carpools);
    }
  }

  /**
   * *GET* - Liste des covoiturages en fonction de leur ville de départ et de leur
   * ville d'arrivée
   * 'GET http://localhost:8080/api/carpools?departureAddress=&arrivalAddress='
   * 
   * 404 - covoiturage non trouvé
   * 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress", "arrivalAddress" })
  public ResponseEntity<?> listAllByDepartureAddressAndArrivalAddress(@RequestParam String departureAddress,
      @RequestParam String arrivalAddress) {
    List<Carpool> carpoolsList = this.carpoolService.findByDepartureAddressAndArrivalAddress(departureAddress,
        arrivalAddress);
    if (carpoolsList.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun covoiturage trouvé");
    } else {
      List<CarpoolDto> carpools = carpoolsList.stream().map(CarpoolDto::from).toList();
      return ResponseEntity.ok(carpools);
    }
  }

  /**
   * *GET* - Liste des covoiturages en fonction de leur ville de départ, de leur
   * ville d'arrivée et à une date postérieure à celle mentionnée
   * 'GET
   * http://localhost:8080/api/carpools?departureAddress=&arrivalAddress=&date='
   * 
   * 404 - covoiturage non trouvé
   * 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress", "arrivalAddress", "date" })
  public ResponseEntity<?> listAllByDepartureAddressAndArrivalAddressAndDateGreaterThan(
      @RequestParam String departureAddress,
      @RequestParam String arrivalAddress, @RequestParam String date) {
    LocalDateTime dateTime = LocalDateTime.parse(date);
    List<Carpool> carpoolsList = this.carpoolService.findByDepartureAddressAndArrivalAddressAndDateGreaterThan(
        departureAddress,
        arrivalAddress, dateTime);
    if (carpoolsList.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun covoiturage trouvé");
    } else {
      List<CarpoolDto> carpools = carpoolsList.stream().map(CarpoolDto::from).toList();
      return ResponseEntity.ok(carpools);
    }
  }

}
