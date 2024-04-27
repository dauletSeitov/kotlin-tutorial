package kotli.tutorial.springboot.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import kotli.tutorial.springboot.model.Bank
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper;

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

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddBanks {
        @Test
        fun `should add new bank`() {
            val bank = Bank("acc123", 31.415, 2)
            mockMvc.post("/api/bank") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(bank)
            }
                    .andDo { print() }
                    .andExpect {
                        status { isCreated() }
                        content { contentType(MediaType.APPLICATION_JSON) }
                        jsonPath("$.accountNumber") {value("acc123")}
                        jsonPath("$.trust") {value("31.415")}
                        jsonPath("$.transactionFee") {value("2")}
                    }
        }
        @Test
        fun `should return bad request if bank already exists`() {
            val invalidBank = Bank("1234", 31.415, 2)
            mockMvc.post("/api/bank") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(invalidBank)
            }
                    .andDo { print() }
                    .andExpect {
                        status { isBadRequest() }
//                        content { contentType(MediaType.APPLICATION_JSON) }
//                        jsonPath("$.accountNumber") {value("acc123")}
//                        jsonPath("$.trust") {value("31.415")}
//                        jsonPath("$.transactionFee") {value("2")}
                    }
        }

    }
}