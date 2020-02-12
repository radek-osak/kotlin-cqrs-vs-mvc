package com.ara.mvc

import com.ara.model.Person
import com.ara.mvc.repository.PersonRepository
import com.ara.mvc.service.PersonService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonServiceTest (@Autowired private val personService: PersonService,
                         @Autowired private val personRepository: PersonRepository) {

    @BeforeEach
    fun setup() {
        var result = personRepository.findAll()
        result.forEach {
            personRepository.delete(it)
        }
    }

    @Test
    fun `Should create new person`() {
        val person = Person("Test 1", 33, null)
        personService.save(person)
        val result : Optional<Person> = personRepository.findByName(person.name)
        Assert.isTrue(result.isPresent)
        Assert.isTrue(result.get().name.equals(person.name))
        Assert.isTrue(result.get().age == person.age)
        Assert.notNull(result.get().id)
    }

    @Test
    fun `Should update existing person`() {
        val person = Person("Test 2", 33, null)
        personService.save(person)
        val result : Optional<Person> = personRepository.findByName(person.name)
        result.get().name = "Test 3"
        personService.save(result.get())
        var id : Long = result.get().id!!
        var secondResult : Optional<Person> = personRepository.findById(id)
        Assert.isTrue(secondResult.isPresent)
        Assert.isTrue(secondResult.get().name.equals(result.get().name))
    }

    @Test
    fun `Should load existing person by name`() {
        val person = Person("Test 2", 33, null)
        personService.save(person)
        val result : Optional<Person> = personRepository.findByName(person.name)
        Assert.isTrue(result.isPresent)
        Assert.isTrue(result.get().name.equals(person.name))
    }

    @Test
    fun `Should load all existing persons`() {
        val person1 = Person("Test 1", 33, null)
        val person2 = Person("Test 2", 33, null)
        val person3 = Person("Test 3", 33, null)
        personService.save(person1)
        personService.save(person2)
        personService.save(person3)
        var result = personRepository.findAll()
        Assert.notNull(result)
        Assert.isTrue(result.count() == 3)
    }

    @Test
    fun `Should delete existing person by ID`() {
        val person1 = Person("Test 1", 33, null)
        val person2 = Person("Test 2", 33, null)
        val person3 = Person("Test 3", 33, null)
        personService.save(person1)
        personService.save(person2)
        personService.save(person3)
        var result = personRepository.findAll()
        Assert.notNull(result)
        Assert.isTrue(result.count() == 3)

        personRepository.delete(person2)
        result = personRepository.findAll()
        Assert.notNull(result)
        Assert.isTrue(result.count() == 2)
    }
}