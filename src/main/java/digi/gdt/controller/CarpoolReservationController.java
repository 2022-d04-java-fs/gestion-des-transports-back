package digi.gdt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CarpoolReservationDto;
import digi.gdt.service.CarpoolReservationService;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "*")
public class CarpoolReservationController {

	private CarpoolReservationService carpoolResaServ;

	public CarpoolReservationController(CarpoolReservationService carpoolResaServ) {
		this.carpoolResaServ = carpoolResaServ;
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
		CarpoolReservationDto carpoolResa = carpoolResaServ.createCarpoolReservation(user_id, carpool_id);
		return ResponseEntity.ok(carpoolResa);
	}

	/**
	 * *PATCH* - Un user donné annule sa réservation à un covoiturage donné Le
	 * nombre de places disponible pour ce covoiturage est incrémenté de 1. 'PATCH
	 * http://localhost:8080/api/users/{user_id}/carpools/{carpool_id}'
	 * 
	 * 404 - user, covoiturage ou réservation non trouvée 400 - la réservation est
	 * déjà annulée 200 - réservation annulée !
	 * 
	 * @param user_id
	 * @param carpool_id
	 * @return ResponseEntity<?>
	 */
	@PatchMapping("{user_id}/carpools/{carpool_id}")
	public ResponseEntity<?> cancelCarpoolReservation(@PathVariable Integer user_id, @PathVariable Integer carpool_id) {
		CarpoolReservationDto carpoolResa = carpoolResaServ.cancelCarpoolReservation(user_id, carpool_id);
		return ResponseEntity.ok(carpoolResa);
	}

}
