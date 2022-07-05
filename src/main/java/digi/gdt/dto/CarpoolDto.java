package digi.gdt.dto;

import java.time.LocalDateTime;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.User;

public class CarpoolDto {
  private LocalDateTime dateHeure;
  private String departureAddress;
  private String arrivalAddress;
  private PrivateVehicleDto vehicle;
  private UserDto driver;
  private Integer availableSeats;

  public CarpoolDto() {
  }

  public CarpoolDto(LocalDateTime dateHeure, String departureAddress, String arrivalAddress,
      PrivateVehicle vehicle,
      User user, Integer availableSeats) {
    this.dateHeure = dateHeure;
    this.departureAddress = departureAddress;
    this.arrivalAddress = arrivalAddress;
    this.vehicle = PrivateVehicleDto.from(vehicle);
    this.driver = UserDto.from(user);
    this.availableSeats = availableSeats;
  }

  public static CarpoolDto from(Carpool carpool) {
    return new CarpoolDto(carpool.getDate(), carpool.getDepartureAddress(), carpool.getArrivalAddress(),
        carpool.getVehicle(), carpool.getCreator(), carpool.getAvailableSeats());
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
