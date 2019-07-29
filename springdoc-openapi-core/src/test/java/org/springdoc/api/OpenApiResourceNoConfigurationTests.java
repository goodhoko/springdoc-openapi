package org.springdoc.api;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springdoc.SpringDocSpringDocTestApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringDocSpringDocTestApplication.class)
@ActiveProfiles("test")
@TestPropertySource(properties = "springdoc.api-docs.path=/api-docs")
@AutoConfigureMockMvc
public class OpenApiResourceNoConfigurationTests {

  @Autowired
  private MockMvc mockMvc;

  /**
   * should return
   * {"openapi":"3.0.1","info":{"title":"OpenAPI definition","version":"v0"},"paths":{},"components":{}}
   */
  @Test
  public void givenNoConfiguration_whenGetApiJson_returnsDefaultEmptyDocs() throws Exception {
    mockMvc
        .perform(get("/api-docs"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.openapi", is("3.0.1")))
        .andExpect(jsonPath("$.info.title", is("OpenAPI definition")))
        .andExpect(jsonPath("$.info.version", is("v0")))
        .andExpect(jsonPath("$.paths").isEmpty())
        .andExpect(jsonPath("$.components").isEmpty());
  }
}
