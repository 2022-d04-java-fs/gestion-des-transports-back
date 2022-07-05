package digi.gdt.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class CompanyVehicle extends AbstractVehicle {
	@NotNull
	@Enumerated(EnumType.STRING)
	private VehicleCategoryEnum category;
	@NotNull
	@Enumerated(EnumType.STRING)
	private VehicleStatusEnum status;

	private String photo;
	@NotNull
	@Positive
	private Integer seats;

	@ManyToMany
	private Set<Coordinates> coordinates;

	public CompanyVehicle() {
		this.coordinates = new HashSet<>();
	}

	public Set<Coordinates> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Set<Coordinates> coordinates) {
		this.coordinates = coordinates;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public VehicleCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(VehicleCategoryEnum category) {
		this.category = category;
	}

	public VehicleStatusEnum getStatus() {
		return status;
	}

	public void setStatus(VehicleStatusEnum status) {
		this.status = status;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
