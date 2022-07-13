package digi.gdt.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;

@Entity
public class VehicleReservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	@FutureOrPresent
	private LocalDateTime startDate;
	@NotNull
	@Future
	private LocalDateTime returnDate;

	@ManyToOne
	private Users driver;

	@ManyToOne
	@NotNull
	private Users client;

	@ManyToOne
	@NotNull
	private CompanyVehicle vehicle;

	public VehicleReservation() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDateTime returnDate) {
		this.returnDate = returnDate;
	}

	public Users getDriver() {
		return driver;
	}

	public void setDriver(Users driver) {
		this.driver = driver;
	}

	public Users getClient() {
		return client;
	}

	public void setClient(Users client) {
		this.client = client;
	}

	public CompanyVehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(CompanyVehicle vehicle) {
		this.vehicle = vehicle;
	}

}
