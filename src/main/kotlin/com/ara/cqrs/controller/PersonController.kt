package com.ara.cqrs.controller

import com.ara.cqrs.service.PersonCommandService
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mvc")
class PersonController(private val personCommandService: PersonCommandService,
                       private val personQueryService: PersonQueryService) {

    @GetMapping("/person/all")
    fun findAll() : MutableIterable<Person> = personQueryService.findAll()

    @GetMapping("/person")
    fun getPersonById(@RequestParam(value = "id") id: Long) : Person = personQueryService.findById(id)

    @PostMapping("/person")
    fun savePerson(@RequestBody person: Person) = personCommandService.save(person)

    @DeleteMapping("/person")
    fun deletePerson(@RequestParam(value = "id") id: Long) = personCommandService.delete(id)
}