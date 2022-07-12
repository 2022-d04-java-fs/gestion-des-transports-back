package digi.gdt.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PrivateVehicle extends AbstractVehicle {

	@ManyToOne
	@NotNull
	private Users owner;

	public Users getOwner() {
		return owner;
	}

	public void setOwner(Users owner) {
		this.owner = owner;
	}

	public PrivateVehicle() {

	}

}
