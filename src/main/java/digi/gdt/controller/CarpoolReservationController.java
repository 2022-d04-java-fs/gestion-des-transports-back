package digi.gdt.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import digi.gdt.dto.CarpoolReservationDto;
import digi.gdt.service.CarpoolReservationService;

@RestController
@RequestMapping("carpools/reservations")
@CrossOrigin(origins = "*")
public class CarpoolReservationController {

	private CarpoolReservationService carpoolResaServ;

	public CarpoolReservationController(CarpoolReservationService carpoolResaServ) {
		this.carpoolResaServ = carpoolResaServ;
	}

	/**
	 * *POST* - Un user donné réserve un covoiturage donné Le nombre de places
	 * disponible pour ce covoiturage est décrémenté de 1. 'POST
	 * http://localhost:8080/api/carpools/reservations/{user_id}/{carpool_id}'
	 * 
	 * 404 - user ou covoiturage non trouvé 400 - plus de place disponible 200 -
	 * covoiturage réservé !
	 * 
	 * @param user_id
	 * @param carpool_id
	 * @return ResponseEntity<?>
	 */
	@PostMapping("{user_id}/{carpool_id}")
	public ResponseEntity<?> createCarpoolReservation(@PathVariable Integer user_id, @PathVariable Integer carpool_id) {
		CarpoolReservationDto carpoolResa = carpoolResaServ.createCarpoolReservation(user_id, carpool_id);
		return ResponseEntity.ok(carpoolResa);
	}

	/**
	 * *PATCH* - Un user donné annule sa réservation à un covoiturage donné Le
	 * nombre de places disponible pour ce covoiturage est incrémenté de 1. 'PATCH
	 * http://localhost:8080/api/carpools/reservations?reservation_id'
	 * 
	 * 404 - user, covoiturage ou réservation non trouvée 400 - la réservation est
	 * déjà annulée 200 - réservation annulée !
	 * 
	 * @param reservation_id
	 * @return ResponseEntity<?>
	 */
	@PatchMapping(params = { "reservation_id" })
	public ResponseEntity<?> cancelCarpoolReservation(@RequestParam Integer reservation_id) {
		CarpoolReservationDto carpoolResa = carpoolResaServ.cancelCarpoolReservation(reservation_id);
		return ResponseEntity.ok(carpoolResa);
	}


	/**
	 * *GET* - retourne la liste de toutes les réservation de l'utilisateur
	 * http://localhost:8080/api/carpools/reservations?user_id'
	 * 
	 * 404 - user non trouvée 400 - 
	 * 
	 * @param reservation_id
	 * @return ResponseEntity<?>
	 */
	@GetMapping(params = { "user_id" })
	public ResponseEntity<?> getCarpoolReservationsByUserId(@RequestParam Integer user_id) {
		List<CarpoolReservationDto> carpoolResas = carpoolResaServ.getCarpoolReservationsByUserId(user_id);
		return ResponseEntity.ok(carpoolResas);
	}

}
