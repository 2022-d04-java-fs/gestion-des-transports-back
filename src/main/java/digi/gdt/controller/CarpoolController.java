package digi.gdt.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.AddCarpoolDto;
import digi.gdt.dto.AddPrivateVehicleDto;
import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.service.CarpoolService;



@RestController
@RequestMapping("carpools")
@CrossOrigin(origins = "*")
public class CarpoolController {

  private CarpoolService carpoolSrv;

  public CarpoolController(CarpoolService carpoolSrv) {
    this.carpoolSrv = carpoolSrv;
  }

  /**
   * *GET* - Liste de tous les covoiturages 'GET
   * http://localhost:8080/api/carpools'
   * 
   * @return List<CarpoolDto>
   */
  @GetMapping
  public List<CarpoolDto> listAll() {
    return this.carpoolSrv.findAll().stream().map(CarpoolDto::from).toList();
  }

  /**
   * *POST* - Créer une nouvelle annonce de covoiturage 'POST
   * http://localhost:8080/api/carpools'
   * 
   * 400 - Bad Request 200 - Objet carpool crée
   * 
   * @return ResponseEntity<?>
   */
  @PostMapping
  public ResponseEntity<?> addCarpool(@RequestBody AddCarpoolDto carpool) {
    AddPrivateVehicleDto vehicle = carpool.getVehicle();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    LocalDateTime date = LocalDateTime.parse(carpool.getDate(), formatter);

    AddCarpoolDto newCarpool = this.carpoolSrv.createCarpool(date, carpool.getDepartureAddress(),
        carpool.getArrivalAddress(), carpool.getDistance(), carpool.getDuration(), carpool.getAvailableSeats(),
        carpool.getCreatorId(), vehicle);

    return ResponseEntity.ok(newCarpool);
  }

  /**
   * *GET* - Liste des covoiturages en fonction de leur ville de départ 'GET
   * http://localhost:8080/api/carpools?departureAddress='
   * 
   * 404 - covoiturage non trouvé 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress" })
  public ResponseEntity<?> listAllByDepartureAddress(@RequestParam String departureAddress) {
    List<Carpool> carpoolsList = this.carpoolSrv.findByDepartureAddress(departureAddress);
    if (carpoolsList.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun covoiturage trouvé");
    } else {
      List<CarpoolDto> carpools = carpoolsList.stream().map(CarpoolDto::from).toList();
      return ResponseEntity.ok(carpools);
    }
  }

  /**
   * *GET* - Liste des covoiturages en fonction de leur ville de départ et de leur
   * ville d'arrivée 'GET
   * http://localhost:8080/api/carpools?departureAddress=&arrivalAddress='
   * 
   * 404 - covoiturage non trouvé 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress", "arrivalAddress" })
  public ResponseEntity<?> listAllByDepartureAddressAndArrivalAddress(@RequestParam String departureAddress,
      @RequestParam String arrivalAddress) {
    List<Carpool> carpoolsList = this.carpoolSrv.findByDepartureAddressAndArrivalAddress(departureAddress,
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
   * ville d'arrivée et à une date postérieure à celle mentionnée 'GET
   * http://localhost:8080/api/carpools?departureAddress=&arrivalAddress=&date='
   * 
   * 404 - covoiturage non trouvé 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "departureAddress", "arrivalAddress", "date" })
  public ResponseEntity<?> listAllByDepartureAddressAndArrivalAddressAndDateGreaterThan(
      @RequestParam String departureAddress, @RequestParam String arrivalAddress, @RequestParam String date) {
    LocalDateTime dateTime = LocalDateTime.parse(date);
    List<Carpool> carpoolsList = this.carpoolSrv
        .findByDepartureAddressAndArrivalAddressAndDateGreaterThan(departureAddress, arrivalAddress, dateTime);
    if (carpoolsList.size() == 0) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun covoiturage trouvé");
    } else {
      List<CarpoolDto> carpools = carpoolsList.stream().map(CarpoolDto::from).toList();
      return ResponseEntity.ok(carpools);
    }
  }


  /**
   * *GET* - Liste des covoiturages postés par un utilisateur 'GET
   * http://localhost:8080/api/carpools?user_id='
   * 
   * 404 - utilisateur non trouvé 200 - liste des covoiturages trouvés
   * 
   * @return ResponseEntity<?>
   */
  @GetMapping(params = { "user_id" })
  public ResponseEntity<?> listAllByUserId(@RequestParam Integer user_id) {
    List<Carpool> carpoolList = this.carpoolSrv.listAllCarpoolByUserId(user_id);
    return ResponseEntity.ok(carpoolList.stream().map(c -> CarpoolDto.from(c)));
  }
  
  /**
  * *PATCH* - Annule le covoiturage et toutes les réservations associées 'PATCH
  * http://localhost:8080/api/carpools?carpool_id='
  * 
  * 404 - carpool non trouvé 200 - carpool
  * 
  * @return ResponseEntity<?>
  */
  @PatchMapping(params = {"carpool_id"})
  public ResponseEntity<?> cancelCarpool(@RequestParam Integer carpool_id){
    CarpoolDto carpool = carpoolSrv.cancelCarpool(carpool_id);
    return ResponseEntity.ok(carpool);
  }
}
