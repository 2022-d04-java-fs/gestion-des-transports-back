package digi.gdt.dto;

import digi.gdt.entity.User;

public class UserDto {
  private String lastname;
  private String firstname;

  public UserDto() {
  }

  public UserDto(String lastname, String firstname) {
    this.lastname = lastname;
    this.firstname = firstname;
  }

  public static UserDto from(User user) {
    return new UserDto(user.getLastname(), user.getLastname());
  }

  public String getLastname() {
    return this.lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getFirstname() {
    return this.firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

}
