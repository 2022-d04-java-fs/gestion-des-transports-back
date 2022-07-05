package digi.gdt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.service.CarpoolService;

@RestController
@RequestMapping("carpools")
public class CarpoolController {
  private CarpoolService carpoolService;

  public CarpoolController(CarpoolService carpoolService) {
    this.carpoolService = carpoolService;
  }

  /**
   * *GET* - Liste de tous les covoiturages
   * Format de la réponse : {
   * dateHeure,
   * departureAddress,
   * arrivalAddress,
   * vehicle:{model, brand},
   * driver:{lastname, firstname},
   * availableSeats
   * }
   * 
   * @return List<CarpoolDto>
   */
  @GetMapping
  public List<CarpoolDto> listAll() {
    return this.carpoolService.findAll().stream().map(CarpoolDto::from).toList();
  }

  // @GetMapping(params = { "departureAddress" })
  // public ResponseEntity<?> listAllByDepartureAddress(@PathVariable String
  // departureAddress) {
  // TODO à compléter
  // }

}
