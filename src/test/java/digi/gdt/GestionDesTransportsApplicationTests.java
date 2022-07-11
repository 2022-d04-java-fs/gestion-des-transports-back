package digi.gdt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import digi.gdt.controller.CarpoolController;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.User;
import digi.gdt.service.CarpoolService;

/**
 * Tests du CarpoolController
 */
@WebMvcTest(controllers = CarpoolController.class)
class GestionDesTransportsApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CarpoolController carpoolCtrl;

	@MockBean
	CarpoolService carpoolSrv;

	/**
	 * On vérifie que le context (créé avec @WebMvcTest) a bien été chargé
	 * 
	 * @throws Exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertNotNull(carpoolCtrl);
	}

	/**
	 * Mock du back et test de la liste de carpools vide
	 * 
	 * @throws Exception
	 */
	@Test
	void emptyCarpoolsList() throws Exception {
		List<Carpool> carpools = new ArrayList<>();
		Mockito.when(carpoolSrv.findAll()).thenReturn(carpools);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0]").doesNotExist());
	}

	/**
	 * Mock du back et test d'une liste avec 1 carpool
	 * 
	 * @throws Exception
	 */
	@Test
	void carpoolsList() throws Exception {
		User u1 = new User();
		u1.setEmail("test@test.fr");
		u1.setFirstname("John");
		u1.setLastname("Doe");
		u1.setPassword("Test123!");

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

		List<Carpool> carpools = new ArrayList<>();
		carpools.add(c1);

		// on mocke le back et on lui passe la liste de carpools qu'on vient de créer
		// pour vérifier que les infos renvoyées sont celles données
		Mockito.when(carpoolSrv.findAll()).thenReturn(carpools);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].dateHeure").value("2016-03-04T11:30:40"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].arrivalAddress").value("25 Rue Malbec 33800 Bordeaux"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].departureAddress").value("Rue Lecourbe 75015 Paris"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.brand").value("brand"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.model").value("model"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.lastname").value("Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.firstname").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].availableSeats").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$[1]").doesNotExist());
	}
}
