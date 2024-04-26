package kotli.tutorial.springboot.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc;

    @Test
    fun `should return all banks`() {
        mockMvc.get("/api/bank")
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                    jsonPath("$[0].accountNumber") { value("1234") }
                }
    }
}