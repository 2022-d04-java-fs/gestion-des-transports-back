package digi.gdt.dto;

import digi.gdt.entity.CarpoolReservation;
import digi.gdt.entity.CarpoolReservationStatusEnum;

//{
//	"user": UserDto,
//	"carpool": CarpoolDto
//}

public class CarpoolReservationDto {

	private UserDto user;
	private CarpoolDto carpool;
	private CarpoolReservationStatusEnum status;

	public CarpoolReservationDto(UserDto user, CarpoolDto carpool, CarpoolReservationStatusEnum status) {
		this.user = user;
		this.carpool = carpool;
		this.status = status;
	}

	public static CarpoolReservationDto from(CarpoolReservation carpoolReservation) {
		return new CarpoolReservationDto(UserDto.from(carpoolReservation.getPassenger()),
				CarpoolDto.from(carpoolReservation.getCarpool()), carpoolReservation.getReservationStatus());
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CarpoolDto getCarpool() {
		return carpool;
	}

	public void setCarpool(CarpoolDto carpool) {
		this.carpool = carpool;
	}

	public CarpoolReservationStatusEnum getStatus() {
		return status;
	}

	public void setStatus(CarpoolReservationStatusEnum status) {
		this.status = status;
	}

}
