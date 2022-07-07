package digi.gdt.dto;

import java.util.HashSet;
import java.util.Set;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.User;

//  {
//  "lastname": ,
//  "firstname": ,
//  "carpools" : {
//      "dateHeure" : ,
//      "departureAddress" : ,
//      "arrivalAddress" : ,
//      "vehicle" : privateVehicleDto,
//      "driver" : userDto,
//      "availableSeats": ,
//  }}
public class CreateCarpoolReservationDto extends UserDto {
  private Set<CarpoolDto> carpoolReservations = new HashSet<>();

  public CreateCarpoolReservationDto(String lastname, String firstname, Set<Carpool> set) {
    super(lastname, firstname);
    set.forEach(carpool -> {
      this.carpoolReservations.add(CarpoolDto.from(carpool));
    });
  }

  public static CreateCarpoolReservationDto from(User user) {
    return new CreateCarpoolReservationDto(user.getLastname(), user.getLastname(), user.getCarpoolReservations());
  }

  public Set<CarpoolDto> getCarpoolReservations() {
    return this.carpoolReservations;
  }

  public void setCarpoolReservations(Set<CarpoolDto> carpoolReservations) {
    this.carpoolReservations = carpoolReservations;
  }

}