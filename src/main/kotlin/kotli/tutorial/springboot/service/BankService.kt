package kotli.tutorial.springboot.service

import kotli.tutorial.springboot.model.Bank
import kotli.tutorial.springboot.repository.BankRepository
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepository) {
    fun getBanks(): Collection<Bank> {
        return bankRepository.getBanks()
    }

    fun getBank(accountNumber: String) = bankRepository.getBank(accountNumber)
    fun addBank(bank: Bank): Bank {
        return bankRepository.addBank(bank)
    }


}