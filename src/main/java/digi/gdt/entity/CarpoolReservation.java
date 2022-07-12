package digi.gdt.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class CarpoolReservation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@ManyToOne
	private Carpool carpool;

	@NotNull
	@ManyToOne
	private User passenger;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CarpoolReservationStatusEnum reservationStatus;

	public CarpoolReservation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Carpool getCarpool() {
		return carpool;
	}

	public void setCarpool(Carpool carpool) {
		this.carpool = carpool;
	}

	public User getPassenger() {
		return passenger;
	}

	public void setPassenger(User passenger) {
		this.passenger = passenger;
	}

	public CarpoolReservationStatusEnum getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(CarpoolReservationStatusEnum reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

}