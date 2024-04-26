package kotli.tutorial.springboot.repository

import kotli.tutorial.springboot.model.Bank
import org.springframework.stereotype.Repository

@Repository
class BankRepositoryImpl : BankRepository {
    val banks = listOf(
            Bank("1234", 13.14, 17),
            Bank("1010", 17.0, 0),
            Bank("5678", 0.0, 100)
    )
    override fun getBanks(): Collection<Bank> {
        return banks
    }

}