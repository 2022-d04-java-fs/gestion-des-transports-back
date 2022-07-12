package digi.gdt.entity;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull
	private String identificationNumber;
	@NotNull
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String lastname;
	@NotNull
	private String firstname;

	private BigInteger telephone;

	private String license;

	private String photo;

	@ManyToMany
	@Size(min = 1, max = 3)
	private Set<Role> roles;

	@ManyToMany
	private Set<Carpool> carpoolReservations;

	public User() {
		this.roles = new HashSet<>();
		this.carpoolReservations = new HashSet<>();
	}

	public User(@NotNull String email, @NotNull String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Carpool> getCarpoolReservations() {
		return carpoolReservations;
	}

	public void setCarpoolReservations(Set<Carpool> carpoolReservations) {
		this.carpoolReservations = carpoolReservations;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public BigInteger getTelephone() {
		return telephone;
	}

	public void setTelephone(BigInteger telephone) {
		this.telephone = telephone;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "{" +
				" id='" + getId() + "'" +
				", identificationNumber='" + getIdentificationNumber() + "'" +
				", email='" + getEmail() + "'" +
				", password='" + getPassword() + "'" +
				", lastname='" + getLastname() + "'" +
				", firstname='" + getFirstname() + "'" +
				", telephone='" + getTelephone() + "'" +
				", license='" + getLicense() + "'" +
				", photo='" + getPhoto() + "'" +
				", roles='" + getRoles() + "'" +
				", carpoolReservations='" + getCarpoolReservations() + "'" +
				"}";
	}

}
