package digi.gdt.dto;

import java.util.HashSet;
import java.util.Set;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.Users;

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

  public CreateCarpoolReservationDto(Integer user_id, String lastname, String firstname, Set<Carpool> set) {
    super(user_id, lastname, firstname);
    set.forEach(carpool -> {
      this.carpoolReservations.add(CarpoolDto.from(carpool));
    });
  }

  public static CreateCarpoolReservationDto from(Users user) {
    return new CreateCarpoolReservationDto(user.getId(), user.getLastname(), user.getLastname(),
        user.getCarpoolReservations());
  }

  public Set<CarpoolDto> getCarpoolReservations() {
    return this.carpoolReservations;
  }

  public void setCarpoolReservations(Set<CarpoolDto> carpoolReservations) {
    this.carpoolReservations = carpoolReservations;
  }

}
