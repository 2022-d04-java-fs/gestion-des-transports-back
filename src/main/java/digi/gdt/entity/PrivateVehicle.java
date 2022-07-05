package digi.gdt.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PrivateVehicle extends AbstractVehicle {

	@ManyToOne
	@NotNull
	private User owner;

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public PrivateVehicle() {

	}

}
