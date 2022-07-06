package digi.gdt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import digi.gdt.controller.CarpoolController;
import digi.gdt.service.CarpoolService;

@SpringBootTest
@AutoConfigureMockMvc
class GestionDesTransportsApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Autowired
	private CarpoolController carpoolCtrl;

	@MockBean
	CarpoolService carpoolSrv;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(carpoolCtrl);
	}

	@Test
	void list() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/carpools"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	}

}
