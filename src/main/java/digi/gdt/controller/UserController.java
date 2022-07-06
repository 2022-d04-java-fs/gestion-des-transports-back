package digi.gdt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.entity.User;
import digi.gdt.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

  private UserService userSrv;

  public UserController(UserService userSrv) {
    this.userSrv = userSrv;
  }

  // @PostMapping("{user_id}/carpoolreservation/{carpool_id}")
  // public ResponseEntity<?> createCarpoolReservation(@PathVariable Integer
  // user_id, @PathVariable Integer carpool_id) {
  // User user = userSrv.createCarpoolReservation(user_id, carpool_id);
  // return ResponseEntity.ok(user);
  // }
}
