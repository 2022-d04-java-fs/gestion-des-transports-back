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
	private Users passenger;

	@NotNull
	@Enumerated(EnumType.STRING)
	private CarpoolStatusEnum reservationStatus;

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

	public Users getPassenger() {
		return passenger;
	}

	public void setPassenger(Users passenger) {
		this.passenger = passenger;
	}

	public CarpoolStatusEnum getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(CarpoolStatusEnum reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

}