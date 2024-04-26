package kotli.tutorial.springboot.repository


import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BankRepositoryImplTest {

    private val repo = BankRepositoryImpl();

    @Test
    fun `should provide a collection of banks`() {

        val banks = repo.getBanks()
        Assertions.assertThat(banks).isNotEmpty
        Assertions.assertThat(banks.size).isGreaterThanOrEqualTo(3)
    }

    @Test
    fun `should provide some mock data`() {

        val banks = repo.getBanks()
        Assertions.assertThat(banks).allMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).anyMatch{ it.trust != 0.0 }
        Assertions.assertThat(banks).anyMatch { it.transactionFee != 0 }
    }
}