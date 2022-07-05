package digi.gdt.dto;

import digi.gdt.entity.PrivateVehicle;

public class PrivateVehicleDto {
  private String model;
  private String brand;

  public PrivateVehicleDto() {
  }

  public PrivateVehicleDto(String model, String brand) {
    this.model = model;
    this.brand = brand;
  }

  public static PrivateVehicleDto from(PrivateVehicle vehicle) {
    return new PrivateVehicleDto(vehicle.getModel(), vehicle.getBrand());
  }

  public String getModel() {
    return this.model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getBrand() {
    return this.brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

}
