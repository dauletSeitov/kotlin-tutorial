package kotli.tutorial.springboot.controller

import kotli.tutorial.springboot.model.Bank
import kotli.tutorial.springboot.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bank")
class BankController(val bankService: BankService) {
    @ExceptionHandler(NoSuchElementException::class)
    fun handle(e: NoSuchElementException) = ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handle(e: IllegalArgumentException) = ResponseEntity(e.message, HttpStatus.BAD_REQUEST)


    @GetMapping
    fun getBanks(): Collection<Bank> {
        return bankService.getBanks()
    }

    @GetMapping("/{accountNumber}")
    fun getBank(@PathVariable accountNumber: String) = bankService.getBank(accountNumber)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank) = bankService.addBank(bank)

    @PatchMapping
    fun updateBank(@RequestBody bank: Bank) = bankService.updateBank(bank)


    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBank(@PathVariable accountNumber: String) = bankService.delete(accountNumber)
}