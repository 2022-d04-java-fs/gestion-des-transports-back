package digi.gdt.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

import digi.gdt.entity.Carpool;

public class AddCarpoolDto {

	private Integer creatorId;
	private String departureAddress;
	private String arrivalAddress;
	private BigDecimal distance;
	private BigInteger duration;
	private AddPrivateVehicleDto vehicle;
	private Integer availableSeats;
	private String date;

	public AddCarpoolDto(Integer creatorId, String departureAddress, String arrivalAddress, BigDecimal distance,
			BigInteger duration, AddPrivateVehicleDto vehicle, Integer availableSeats, String date) {
		super();
		this.departureAddress = departureAddress;
		this.arrivalAddress = arrivalAddress;
		this.distance = distance;
		this.duration = duration;
		this.vehicle = vehicle;
		this.availableSeats = availableSeats;
		this.date = date;
		this.creatorId = creatorId;
	}

	public static AddCarpoolDto from(Carpool carpool) {
		return new AddCarpoolDto(carpool.getCreator().getId(), carpool.getDepartureAddress(),
				carpool.getArrivalAddress(), carpool.getDistance(), carpool.getDuration(),
				AddPrivateVehicleDto.from(carpool.getVehicle()), carpool.getAvailableSeats(),
				carpool.getDate().toString());
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer ownerId) {
		this.creatorId = ownerId;
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

	public BigDecimal getDistance() {
		return distance;
	}

	public void setDistance(BigDecimal distance) {
		this.distance = distance;
	}

	public BigInteger getDuration() {
		return duration;
	}

	public void setDuration(BigInteger duration) {
		this.duration = duration;
	}

	public AddPrivateVehicleDto getVehicle() {
		return vehicle;
	}

	public void setVehicle(AddPrivateVehicleDto vehicle) {
		this.vehicle = vehicle;
	}

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
