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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import digi.gdt.dto.CarpoolDto;
import digi.gdt.entity.Carpool;
import digi.gdt.entity.CarpoolStatusEnum;
import digi.gdt.entity.PrivateVehicle;
import digi.gdt.entity.Users;
import digi.gdt.repository.CarpoolRepository;
import digi.gdt.service.CarpoolService;

/**
 * Tests du CarpoolController
 */
@WebMvcTest(controllers = CarpoolController.class)
class CarpoolControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CarpoolController carpoolCtrl;

	@MockBean
	CarpoolService carpoolSrv;

	@MockBean
	CarpoolRepository carpoolRepo;

	private List<Carpool> carpoolsBeforeEach = new ArrayList<>();

	@BeforeEach
	void addCarpool() {
		Users u1 = new Users();
		u1.setEmail("test@test.fr");
		u1.setFirstname("John");
		u1.setLastname("Doe");
		u1.setPassword("Test123!");
		u1.setId(1);

		Users u2 = new Users();
		u2.setEmail("test2@test.fr");
		u2.setFirstname("John");
		u2.setLastname("Smith");
		u2.setPassword("Test123!");
		u2.setId(2);

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

		Carpool c2 = new Carpool();
		c1.setArrivalAddress("25 Rue Malbec 33800 Bordeaux");
		c1.setDepartureAddress("Rue Lecourbe 75015 Paris");
		c1.setAvailableSeats(3);
		String str2 = "2016-03-04 11:30:40";
		LocalDateTime dateTime2 = LocalDateTime.parse(str2, formatter);
		c2.setDate(dateTime2);
		c2.setCreator(u2);
		c2.setVehicle(v1);

		carpoolsBeforeEach.add(c1);
		carpoolsBeforeEach.add(c2);
	}

	/**
	 * On vérifie que le context (créé avec @WebMvcTest) a bien été chargé
	 * 
	 * @throws Exception
	 */
	@Test
	void contextLoads() throws Exception {
		assertNotNull(carpoolCtrl);
		assertNotNull(carpoolSrv);
		assertNotNull(carpoolRepo);
	}

	/**
	 * TEST : findAll() Mock du back et test de la liste de carpools vide
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllTestWithEmptyCarpoolsList() throws Exception {
		List<Carpool> carpools = new ArrayList<>();
		Mockito.when(carpoolSrv.findAll()).thenReturn(carpools);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools"))// .andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(0));
	}

	/**
	 * Mock du back et test d'une liste avec 1 carpool
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllTestWithNotEmptyCarpoolsList() throws Exception {
		// on mocke le back et on lui passe la liste de carpools qu'on vient de créer
		// pour vérifier que les infos renvoyées sont celles données
		Mockito.when(carpoolSrv.findAll()).thenReturn(carpoolsBeforeEach);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools")) // .andDo(MockMvcResultHandlers.print())
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
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
	}

	/**
	 * TEST : listAllByDepartureAddress(@RequestParam String departureAddress) Mock
	 * du back et test avec une departureAddress existante
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllByDepartureAddressTestWithFoundedDepartureAddress() throws Exception {
		String departureAddress = "Rue Lecourbe 75015 Paris";

		Mockito.when(carpoolSrv.findByDepartureAddress(departureAddress)).thenReturn(carpoolsBeforeEach);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools").param("departureAddress", departureAddress))
				// .andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2));
	}

	/**
	 * Mock du back et test avec une departureAddress non existante
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllByDepartureAddressTestWithNotFoundDepartureAddress() throws Exception {
		String departureAddress = "Avenue de la liberte 59000 Lille";
		List<Carpool> resultCarpool = new ArrayList<Carpool>();
		Mockito.when(carpoolSrv.findByDepartureAddress(departureAddress)).thenReturn(resultCarpool);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools").param("departureAddress", departureAddress))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.content().string("Aucun covoiturage trouvé"));
	}

	/**
	 * TEST : listAllByUserId(@RequestParam Integer user_id) Mock du back et test
	 * avec un id valide
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllTestWithId() throws Exception {
		int id = 1;
		List<Carpool> list_result = new ArrayList<Carpool>();
		list_result.add(carpoolsBeforeEach.get(0));
		Mockito.when(carpoolSrv.listAllCarpoolByUserId(id)).thenReturn(list_result);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools").param("user_id", "" + id))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].dateHeure").value("2016-03-04T11:30:40"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].arrivalAddress").value("25 Rue Malbec 33800 Bordeaux"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].departureAddress").value("Rue Lecourbe 75015 Paris"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.brand").value("brand"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].vehicle.model").value("model"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.lastname").value("Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].driver.firstname").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].availableSeats").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
		;

	}

	/**
	 * Mock du back et test avec un id invalide
	 * 
	 * @throws Exception
	 */
	@Test
	void listAllTestWithBadId() throws Exception {
		int id = 2;
		List<Carpool> list_result = new ArrayList<Carpool>();
		Mockito.when(carpoolSrv.listAllCarpoolByUserId(id)).thenReturn(list_result);
		this.mockMvc.perform(MockMvcRequestBuilders.get("/carpools").param("user_id", "" + id))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
		;

	}

	/**
	 * TEST : cancelCarpool(@RequestParam Integer carpool_id) Mock du back et test
	 * avec un id valide
	 */

	@Test
	void testCancelCarpoolValid() throws Exception {
		int id = 0;
		CarpoolDto carpool = CarpoolDto.from(carpoolsBeforeEach.get(id));
		carpool.setStatus(CarpoolStatusEnum.CANCELLED);
		Mockito.when(carpoolSrv.cancelCarpool(id)).thenReturn(carpool);
		this.mockMvc.perform(MockMvcRequestBuilders.patch("/carpools").param("carpool_id", "" + id))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
				.andExpect(MockMvcResultMatchers.jsonPath(".dateHeure").value("2016-03-04T11:30:40"))
				.andExpect(MockMvcResultMatchers.jsonPath(".arrivalAddress").value("25 Rue Malbec 33800 Bordeaux"))
				.andExpect(MockMvcResultMatchers.jsonPath(".departureAddress").value("Rue Lecourbe 75015 Paris"))
				.andExpect(MockMvcResultMatchers.jsonPath(".vehicle.brand").value("brand"))
				.andExpect(MockMvcResultMatchers.jsonPath(".vehicle.model").value("model"))
				.andExpect(MockMvcResultMatchers.jsonPath(".driver.lastname").value("Doe"))
				.andExpect(MockMvcResultMatchers.jsonPath(".driver.firstname").value("John"))
				.andExpect(MockMvcResultMatchers.jsonPath(".availableSeats").value(3))
				.andExpect(MockMvcResultMatchers.jsonPath(".status").value("CANCELLED"));
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

}
