package nl.asellion.ps.configuration.exception;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Alexander Kirillov
 */

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@Disabled
public class SpringSecurityTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .alwaysDo(print())
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Test
    public void deleteProductByIdTestWithCorrectUser() throws Exception {
        //given
        final Long id = 1L;

        //when //then
        mockMvc.perform(delete("/api/products/{id}", id).with(user("use1111")))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteProductByIdTestWithIncorrectUser() throws Exception {
        //given
        final Long id = 1L;

        //when //then
        mockMvc.perform(delete("/api/products/{id}", id).with(user("usera").roles("ADMINww")))
                .andExpect(status().isUnauthorized());

    }
}
