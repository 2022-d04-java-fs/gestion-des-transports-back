package digi.gdt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import digi.gdt.service.UserService;

/**
 * Tests du UserController
 */
@WebMvcTest(controllers = UserController.class)
public class UserControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserController userCtrl;

  @MockBean
  UserService userSrv;

  /**
   * On vérifie que le context (créé avec @WebMvcTest) a bien été chargé
   * 
   * @throws Exception
   */
  @Test
  void contextLoads() throws Exception {
    assertNotNull(userCtrl);
  }
}
