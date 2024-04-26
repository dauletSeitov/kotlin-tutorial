package kotli.tutorial.springboot.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotli.tutorial.springboot.repository.BankRepositoryImpl
import org.junit.jupiter.api.Test


class BankServiceTest {

    val repo: BankRepositoryImpl = mockk(relaxed=true)
    val service = BankService(repo)

    @Test
    fun `should call its data source to retrieve banks`() {

//        every { repo.getBanks() } returns listOf()
        val banks = service.getBanks()

        verify(exactly = 1) { repo.getBanks() }
    }
}