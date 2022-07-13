package digi.gdt.dto;

import digi.gdt.entity.Users;

public class UserDto {
  private Integer user_id;
  private String lastname;
  private String firstname;

  public UserDto(Integer user_id, String lastname, String firstname) {
    this.user_id = user_id;
    this.lastname = lastname;
    this.firstname = firstname;
  }

  public static UserDto from(Users user) {
    return new UserDto(user.getId(), user.getLastname(), user.getFirstname());
  }

  public Integer getId() {
    return this.user_id;
  }

  public void setId(Integer user_id) {
    this.user_id = user_id;
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
