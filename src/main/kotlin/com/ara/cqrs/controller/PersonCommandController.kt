package com.ara.cqrs.controller

import com.ara.cqrs.service.PersonCommandService
import com.ara.model.Person
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cqrs/command")
class PersonCommandController (private val personCommandService: PersonCommandService) {

    @PostMapping("/person")
    fun savePerson(@RequestBody person: Person) = personCommandService.save(person)

    @DeleteMapping("/person")
    fun deletePerson(@RequestParam(value = "name") name: String) = personCommandService.delete(name)
}