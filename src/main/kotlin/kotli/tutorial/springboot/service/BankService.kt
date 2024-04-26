package kotli.tutorial.springboot.service

import kotli.tutorial.springboot.model.Bank
import kotli.tutorial.springboot.repository.BankRepository
import org.springframework.stereotype.Service

@Service
class BankService(private val bankRepository: BankRepository) {
    fun getBanks(): Collection<Bank> {
        return bankRepository.getBanks()
    }


}