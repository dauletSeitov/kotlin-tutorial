package kotli.tutorial.springboot.controller

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.web.bind.annotation.ExceptionHandler

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBank {
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

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetBanks {
        @Test
        fun `should return the bank with the given account number`() {
            val accountNumber = "1234"
            mockMvc.get("/api/bank/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        status { isOk() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.trust") { value(13.14) }
                        jsonPath("$.transactionFee") { value(17) }
                    }
        }

        @Test
        fun `should return not found if the account number does not exist`() {
            val accountNumber = "does_not_exist"
            mockMvc.get("/api/bank/$accountNumber")
                    .andDo { print() }
                    .andExpect {
                        status { isNotFound() }
                    }
        }
    }
}