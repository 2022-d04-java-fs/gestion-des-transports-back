package digi.gdt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CarpoolDto;
import digi.gdt.dto.CreateCarpoolReservationDto;
import digi.gdt.dto.CreateUserDto;
import digi.gdt.entity.Users;
import digi.gdt.service.UserService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userSrv;

	public UserController(UserService userSrv) {
		this.userSrv = userSrv;
	}

	/**
	 * *GET* - Liste des covoiturages en fonction de l'id de l'utilisateur
	 * http://localhost:8080/api/users/{user_id}/reservations'
	 * 
	 * 404 - covoiturage non trouvé 200 - liste des covoiturages trouvés
	 * 
	 * @return ResponseEntity<?>
	 */

	@GetMapping("{user_id}/reservations")
	public ResponseEntity<?> listAllByUserID(@PathVariable Integer user_id) {
		// On récupère l'utilsateur grâce à son ID (s'il existe)
		Optional<Users> optUser = this.userSrv.findById(user_id);
		if (optUser.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun utilisateur trouvé");
		} else {
			System.out.println(optUser.get().getId());
			List<CarpoolDto> carpools = optUser.get().getCarpoolReservations().stream().map(CarpoolDto::from).toList();
			return ResponseEntity.ok(carpools);
		}
	}

	/**
	 * *POST* - Un user donné réserve un covoiturage donné Le nombre de places
	 * disponible pour ce covoiturage est décrémenté de 1. 'POST
	 * http://localhost:8080/api/users/{user_id}/carpools/{carpool_id}'
	 * 
	 * 404 - user ou covoiturage non trouvé 400 - plus de place disponible 200 -
	 * covoiturage réservé !
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

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody CreateUserDto user) {
		Users newUser = this.userSrv.createUser(user);

		return ResponseEntity.ok(newUser);
	}

}
