package digi.gdt.dto;

import java.time.LocalDateTime;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;

public class CarpoolDto {
  private Integer carpool_id;
  private LocalDateTime dateHeure;
  private String departureAddress;
  private String arrivalAddress;
  private PrivateVehicleDto vehicle;
  private UserDto driver;
  private Integer availableSeats;

  public CarpoolDto() {
  }

  public CarpoolDto(Integer carpool_id, LocalDateTime dateHeure, String departureAddress, String arrivalAddress,
      PrivateVehicle vehicle,
      Users user, Integer availableSeats) {
    this.carpool_id = carpool_id;
    this.dateHeure = dateHeure;
    this.departureAddress = departureAddress;
    this.arrivalAddress = arrivalAddress;
    this.vehicle = PrivateVehicleDto.from(vehicle);
    this.driver = UserDto.from(user);
    this.availableSeats = availableSeats;
  }

  public static CarpoolDto from(Carpool carpool) {
    return new CarpoolDto(carpool.getId(), carpool.getDate(), carpool.getDepartureAddress(),
        carpool.getArrivalAddress(),
        carpool.getVehicle(), carpool.getCreator(), carpool.getAvailableSeats());
  }

  public Integer getCarpool_id() {
    return this.carpool_id;
  }

  public void setCarpool_id(Integer carpool_id) {
    this.carpool_id = carpool_id;
  }

  public LocalDateTime getDateHeure() {
    return this.dateHeure;
  }

  public void setDateHeure(LocalDateTime dateHeure) {
    this.dateHeure = dateHeure;
  }

  public String getDepartureAddress() {
    return this.departureAddress;
  }

  public void setDepartureAddress(String departureAddress) {
    this.departureAddress = departureAddress;
  }

  public String getArrivalAddress() {
    return this.arrivalAddress;
  }

  public void setArrivalAddress(String arrivalAddress) {
    this.arrivalAddress = arrivalAddress;
  }

  public PrivateVehicleDto getVehicle() {
    return this.vehicle;
  }

  public void setVehicle(PrivateVehicleDto vehicle) {
    this.vehicle = vehicle;
  }

  public UserDto getDriver() {
    return this.driver;
  }

  public void setDriver(UserDto driver) {
    this.driver = driver;
  }

  public Integer getAvailableSeats() {
    return this.availableSeats;
  }

  public void setAvailableSeats(Integer availableSeats) {
    this.availableSeats = availableSeats;
  }

}
