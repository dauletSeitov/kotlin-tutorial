package kotli.tutorial.springboot.repository

import kotli.tutorial.springboot.model.Bank

interface BankRepository {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber: String): Bank
    fun addBank(bank: Bank): Bank
}