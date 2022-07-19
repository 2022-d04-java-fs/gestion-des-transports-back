
package digi.gdt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;
import digi.gdt.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserController userCtrl;

	@MockBean
	UserService userSrv;

	private List<Carpool> carpoolsBeforeEach = new ArrayList<>();
	private List<Users> usersBeforeEach = new ArrayList<>();

	@BeforeEach
	void addCarpool() {
		Users u1 = new Users();
		u1.setEmail("test@test.fr");
		u1.setFirstname("John");
		u1.setLastname("Doe");
		u1.setPassword("Test123!");
		u1.setId(1);

		PrivateVehicle v1 = new PrivateVehicle();
		v1.setBrand("brand");
		v1.setModel("model");
		v1.setLicensePlate("XX-123-XX");
		v1.setOwner(u1);

		Carpool c1 = new Carpool();
		c1.setArrivalAddress("25 Rue Malbec 33800 Bordeaux");
		c1.setDepartureAddress("Rue Lecourbe 75015 Paris");
		c1.setAvailableSeats(3);
		String str = "2016-03-04 11:30:40";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		c1.setDate(dateTime);
		c1.setCreator(u1);
		c1.setVehicle(v1);

		carpoolsBeforeEach.add(c1);

		usersBeforeEach.add(u1);
	}

	@Test
	void contextLoads() throws Exception {
		assertNotNull(userCtrl);
		assertNotNull(userSrv);
	}

	/**
	 * TEST : findAll()+
	 */
	@Test
	void getUsersfromEmptyList() throws Exception {

		List<Users> users = new ArrayList<>();
		// user_id d'un Users connu
		Integer user_id = 1;
		Mockito.when(userSrv.findAll()).thenReturn(users);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user_id + "/reservations"))// .andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * Vérifie que pour un utilsateur trouvé, il donne la bonne liste des carpools
	 * 
	 * @throws Exception
	 */
	/**
	 * @Test void getCarpoolListfromUser() throws Exception { // user_id d'un Users
	 *       connu Integer user_id = 1; Optional<Users> user_result =
	 *       Optional.of(usersBeforeEach.get(user_id - 1)); // Comme les listes
	 *       démarrent à 0, // on retire 1 à id ici
	 *       Mockito.when(userSrv.findById(user_id)).thenReturn(user_result);
	 *       this.mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user_id +
	 *       "/reservations"))
	 *       .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
	 *       .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].dateHeure").value("2016-03-04T11:30:40"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].arrivalAddress").value("25
	 *       Rue Malbec 33800 Bordeaux"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].departureAddress").value("Rue
	 *       Lecourbe 75015 Paris"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.brand").value("brand"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.model").value("model"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.lastname").value("Doe"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.firstname").value("John"))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$[0].availableSeats").value(3))
	 *       .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1)); }
	 *
	 */
	/**
	 * Vérifie que pour un utilsateur trouvé, il donne la liste des carpools même si
	 * elle est vide
	 * 
	 * @throws Exception
	 */
	/*
	 * @Test void getEmptyCarpoolListfromUser() throws Exception { // user_id d'un
	 * user connu Integer user_id = 1; Optional<Users> user_result =
	 * Optional.of(usersBeforeEach.get(user_id - 1)); // Comme les listes démarrent
	 * à 0, // // on retire 1 à id ici user_result.get().setCarpoolReservations(new
	 * HashSet<Carpool>());
	 * Mockito.when(userSrv.findById(user_id)).thenReturn(user_result);
	 * this.mockMvc.perform(MockMvcRequestBuilders.get("/users/" + user_id +
	 * "/reservations"))
	 * .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status(
	 * ).isOk())
	 * .andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(
	 * MediaType.APPLICATION_JSON))
	 * .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0)); }
	 */
}
