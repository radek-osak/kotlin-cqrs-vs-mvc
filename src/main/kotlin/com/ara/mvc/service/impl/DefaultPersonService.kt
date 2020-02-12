package com.ara.mvc.service.impl

import com.ara.mvc.model.Person
import com.ara.mvc.repository.PersonRepository
import com.ara.mvc.service.PersonService
import org.springframework.stereotype.Service
import java.util.*

@Service
class DefaultPersonService(private val personRepository: PersonRepository) : PersonService {

    override fun findAll(): MutableIterable<Person> {
        return personRepository.findAll()
    }

    override fun findById(id: Long): Person {
        var result : Optional<Person> = personRepository.findById(id)
        if (result.isPresent)
            return result.get()
        else
            throw RuntimeException("Can not find person with id ${id}")
    }

    override fun save(person: Person) {
        personRepository.save(person)
    }

    override fun delete(id: Long) {
        val person : Optional<Person> = personRepository.findById(id)
        if (person.isPresent)
            personRepository.delete(person.get())
        else
            throw RuntimeException("Can not find person with id ${id}")
    }
}