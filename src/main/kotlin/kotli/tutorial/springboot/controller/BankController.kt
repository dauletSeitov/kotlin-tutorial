package kotli.tutorial.springboot.controller

import kotli.tutorial.springboot.model.Bank
import kotli.tutorial.springboot.service.BankService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/bank")
class BankController(val bankService: BankService) {

    @GetMapping
    fun getBanks(): Collection<Bank> {
        return bankService.getBanks()
    }
}