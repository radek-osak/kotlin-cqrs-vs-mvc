package com.ara.mvc.controller

import com.ara.mvc.model.Person
import com.ara.mvc.service.PersonService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mvc")
class PersonController(private val personService: PersonService) {

    @GetMapping("/person/all")
    fun findAll() : MutableIterable<Person> = personService.findAll()

    @GetMapping("/person")
    fun getPersonById(@RequestParam(value = "id") id: Long) : Person = personService.findById(id)

    @PostMapping("/person")
    fun savePerson(@RequestBody person: Person) = personService.save(person)

    @DeleteMapping("/person")
    fun deletePerson(@RequestParam(value = "id") id: Long) = personService.delete(id)
}