package kotli.tutorial.springboot.repository

import kotli.tutorial.springboot.model.Bank
import org.springframework.stereotype.Repository

@Repository
class BankRepositoryImpl : BankRepository {
    val banks = mutableListOf(
            Bank("1234", 13.14, 17),
            Bank("1010", 17.0, 0),
            Bank("5678", 0.0, 100)
    )

    override fun getBanks(): Collection<Bank> {
        return banks
    }

    override fun getBank(accountNumber: String) =
            banks.firstOrNull { it.accountNumber == accountNumber }
                    ?: throw NoSuchElementException("Could not find such bank: $accountNumber")

    override fun addBank(bank: Bank): Bank {
        if (banks.any { it.accountNumber == bank.accountNumber }) {
            throw IllegalArgumentException("Bank with ${bank.accountNumber} is already exists!")
        }
        banks.add(bank)
        return bank

    }

    override fun updateBank(bank: Bank): Bank {
        val currentBank = banks.firstOrNull { it.accountNumber == bank.accountNumber }
                ?: throw NoSuchElementException("Could not find such bank: ${bank.accountNumber}")
        banks.remove(currentBank)
        banks.add(bank)
        return bank
    }

}