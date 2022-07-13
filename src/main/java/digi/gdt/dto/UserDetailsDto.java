package digi.gdt.dto;

import java.util.Set;

import digi.gdt.entity.Role;
import digi.gdt.entity.Users;

public class UserDetailsDto {
	private Integer user_id;
	private String lastname;
	private String firstname;
	private Set<Role> roles;

	public UserDetailsDto(Integer user_id, String lastname, String firstname, Set<Role> roles) {
		super();
		this.user_id = user_id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.roles = roles;
	}

	public static UserDetailsDto from(Users user) {
		return new UserDetailsDto(user.getId(), user.getLastname(), user.getFirstname(), user.getRoles());
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	

	

}
