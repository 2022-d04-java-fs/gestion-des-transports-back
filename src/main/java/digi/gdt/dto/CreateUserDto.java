package digi.gdt.dto;

import digi.gdt.entity.User;

public class CreateUserDto {
  private String email;
  private String password;
  private String lastname;
  private String firstname;

  public CreateUserDto(String email, String password, String lastname, String firstname) {
    this.email = email;
    this.password = password;
    this.lastname = lastname;
    this.firstname = firstname;
  }

  public static CreateUserDto from(User user) {
    return new CreateUserDto(user.getEmail(), user.getPassword(), user.getFirstname(), user.getLastname());
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  // TODO à supprimer !!!!
  @Override
  public String toString() {
    return "{" +
        " email='" + getEmail() + "'" +
        ", password='" + getPassword() + "'" +
        ", lastname='" + getLastname() + "'" +
        ", firstname='" + getFirstname() + "'" +
        "}";
  }

}
