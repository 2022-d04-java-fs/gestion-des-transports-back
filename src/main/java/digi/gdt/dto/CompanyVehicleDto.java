package digi.gdt.dto;

public class CompanyVehicleDto {
  private String model;
  private String brand;

  public CompanyVehicleDto() {
  }

  public CompanyVehicleDto(String model, String brand) {
    this.model = model;
    this.brand = brand;
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
