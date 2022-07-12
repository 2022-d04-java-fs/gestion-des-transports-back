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
public class CreateCarpoolReservationDtoOLD extends UserDto {
  private Set<CarpoolDto> carpoolReservations = new HashSet<>();

  public CreateCarpoolReservationDtoOLD(Integer user_id, String lastname, String firstname, Set<Carpool> set) {
    super(user_id, lastname, firstname);
    set.forEach(carpool -> {
      this.carpoolReservations.add(CarpoolDto.from(carpool));
    });
  }

  public static CreateCarpoolReservationDtoOLD from(User user) {
    return new CreateCarpoolReservationDtoOLD(user.getId(), user.getLastname(), user.getLastname(),
        user.getCarpoolReservations());
  }

  public Set<CarpoolDto> getCarpoolReservations() {
    return this.carpoolReservations;
  }

  public void setCarpoolReservations(Set<CarpoolDto> carpoolReservations) {
    this.carpoolReservations = carpoolReservations;
  }

}
