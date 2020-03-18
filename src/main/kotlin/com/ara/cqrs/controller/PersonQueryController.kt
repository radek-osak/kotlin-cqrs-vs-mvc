package com.ara.cqrs.controller

import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/cqrs/query")
class PersonQueryController(private val personQueryService: PersonQueryService) {

    @GetMapping("/person/all")
    fun findAll() : MutableIterable<Person> = personQueryService.findAll()

    @GetMapping("/person")
    fun getPersonById(@RequestParam(value = "name") name: String) : Person = personQueryService.findByName(name)

}