package digi.gdt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import digi.gdt.controller.CarpoolController;
import digi.gdt.controller.UserController;
import digi.gdt.service.CarpoolService;

@SpringBootTest
@AutoConfigureMockMvc
class GestionDesTransportsApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private CarpoolController carpoolCtrl;

	@Autowired
	private UserController userCtrl;

	@MockBean
	CarpoolService carpoolSrv;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(carpoolCtrl);
		assertNotNull(userCtrl);
	}

	@Test
	void carpoolsList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/carpools"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void carpoolReservationForUserList() throws Exception {
		// user_id d'un user connu
		Integer user_id = 1;
		// carpool_id d'un covoiturage connu et encore disponible
		Integer carpool_id = 5;
		mockMvc.perform(MockMvcRequestBuilders.post("/users/" + user_id + "/carpools/" + carpool_id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void carpoolReservationUserNotFoundList() throws Exception {
		// user_id d'un user inconnu
		Integer user_id = 10;
		// carpool_id d'un covoiturage connu et encore disponible
		Integer carpool_id = 4;
		mockMvc.perform(MockMvcRequestBuilders.post("/users/" + user_id + "/carpools/" + carpool_id))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

	@Test
	void carpoolReservationCarpoolNotFoundList() throws Exception {
		// user_id d'un user connu
		Integer user_id = 1;
		// carpool_id d'un covoiturage connu et encore disponible
		Integer carpool_id = 40;
		mockMvc.perform(MockMvcRequestBuilders.post("/users/" + user_id + "/carpools/" + carpool_id))
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

}
