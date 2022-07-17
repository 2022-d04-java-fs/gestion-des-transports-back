package digi.gdt.dto;

import digi.gdt.entity.PrivateVehicle;

public class AddPrivateVehicleDto {

	private String licensePlate;
	private String brand;
	private String model;

	public AddPrivateVehicleDto(String licensePlate, String brand, String model) {
		super();
		this.licensePlate = licensePlate;
		this.brand = brand;
		this.model = model;
	}

	public static AddPrivateVehicleDto from(PrivateVehicle vehicle) {
		return new AddPrivateVehicleDto(vehicle.getLicensePlate(), vehicle.getBrand(), vehicle.getModel());
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
