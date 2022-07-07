package digi.gdt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CreateCarpoolReservationDto;
import digi.gdt.entity.User;
import digi.gdt.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

  private UserService userSrv;

  public UserController(UserService userSrv) {
    this.userSrv = userSrv;
  }

  /**
   * *POST* - Un user donné réserve un covoiturage donné
   * Le nombre de places disponible pour ce covoiturage est décrémenté de 1.
   * 
   * 404 - user ou covoiturage non trouvé
   * 400 - plus de place disponible
   * 200 - covoiturage réservé !
   * 
   * @param user_id
   * @param carpool_id
   * @return ResponseEntity<?>
   */
  @PostMapping("{user_id}/carpools/{carpool_id}")
  public ResponseEntity<?> createCarpoolReservation(@PathVariable Integer user_id, @PathVariable Integer carpool_id) {
    CreateCarpoolReservationDto user = userSrv.createCarpoolReservation(user_id, carpool_id);
    return ResponseEntity.ok(user);
  }
}
