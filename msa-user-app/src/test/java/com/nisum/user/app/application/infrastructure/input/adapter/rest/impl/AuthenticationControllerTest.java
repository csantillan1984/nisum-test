package com.nisum.user.app.application.infrastructure.input.adapter.rest.impl;

import com.nisum.user.app.application.service.JwtAdapter;
import com.nisum.user.app.infrastructure.input.adapter.rest.impl.AuthenticationController;
import com.nisum.user.app.mock.MockData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
@ActiveProfiles("test")
class AuthenticationControllerTest {

  private MockMvc mockMvc;
  @MockBean private JwtAdapter jwtAdapter;
  @Autowired private ApplicationContext context;

  @BeforeEach
  void init() {
    AuthenticationController controller = context.getBean(AuthenticationController.class);
    this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
  }

  @Test
  void givenUserThenReturnToken() throws Exception{
    mockMvc.perform(MockMvcRequestBuilders.get(MockData.URL_AUTH)
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .param("user","nisum")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk());
  }
}
