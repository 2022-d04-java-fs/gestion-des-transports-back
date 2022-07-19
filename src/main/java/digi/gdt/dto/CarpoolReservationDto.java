package digi.gdt.dto;

import java.time.LocalDateTime;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.CarpoolStatusEnum;
import digi.gdt.entity.Users;

//{
//	"user": UserDto,
//	"carpool": CarpoolDto
//}

public class CarpoolReservationDto {

	private Integer reservation_id;
	private Integer carpool_id;
	private LocalDateTime dateHeure;
	private String departureAddress;
	private String arrivalAddress;
	private UserDto driver;
	private PrivateVehicleDto vehicle;
	private Integer availableSeats;
	private CarpoolStatusEnum status;

	public CarpoolReservationDto(Integer reservation_id, Integer carpool_id, LocalDateTime dateHeure,
			String departureAddress, String arrivalAddress, UserDto driver, PrivateVehicleDto vehicle,
			Integer availableSeats, CarpoolStatusEnum status) {
		super();
		this.reservation_id = reservation_id;
		this.carpool_id = carpool_id;
		this.dateHeure = dateHeure;
		this.departureAddress = departureAddress;
		this.arrivalAddress = arrivalAddress;
		this.driver = driver;
		this.vehicle = vehicle;
		this.availableSeats = availableSeats;
		this.status = status;
	}

	public static CarpoolReservationDto from(CarpoolReservation resa) {
		Carpool carpool = resa.getCarpool();
		Users user = carpool.getCreator();
		return new CarpoolReservationDto(resa.getId(), carpool.getId(), carpool.getDate(),
				carpool.getDepartureAddress(), carpool.getArrivalAddress(), UserDto.from(user),
				PrivateVehicleDto.from(carpool.getVehicle()), carpool.getAvailableSeats(), resa.getReservationStatus());
	}

	public Integer getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(Integer reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Integer getCarpool_id() {
		return carpool_id;
	}

	public void setCarpool_id(Integer carpool_id) {
		this.carpool_id = carpool_id;
	}

	public LocalDateTime getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(LocalDateTime dateHeure) {
		this.dateHeure = dateHeure;
	}

	public String getDepartureAddress() {
		return departureAddress;
	}

	public void setDepartureAddress(String departureAddress) {
		this.departureAddress = departureAddress;
	}

	public String getArrivalAddress() {
		return arrivalAddress;
	}

	public void setArrivalAddress(String arrivalAddress) {
		this.arrivalAddress = arrivalAddress;
	}

	public UserDto getDriver() {
		return driver;
	}

	public void setDriver(UserDto driver) {
		this.driver = driver;
	}

	public PrivateVehicleDto getVehicle() {
		return vehicle;
	}

	public void setVehicle(PrivateVehicleDto vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public CarpoolStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CarpoolStatusEnum status) {
		this.status = status;
	}

}
