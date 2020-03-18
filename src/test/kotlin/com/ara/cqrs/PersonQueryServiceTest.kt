package com.ara.cqrs

import com.ara.cqrs.service.PersonCommandService
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonQueryServiceTest(@Autowired private val personQueryService: PersonQueryService,
                             @Autowired private val personCommandService: PersonCommandService) {

    private var PERSON_NAME: String = "Radek"
    private var PERSON_AGE: Int = 30

    @BeforeEach
    fun beforeEach() {
        personCommandService.save(Person(PERSON_NAME, PERSON_AGE))
    }

    @AfterEach
    fun afterEach() {
        personCommandService.delete(PERSON_NAME)
    }

    @Test
    fun shouldFindPersonByNameIfExist() {
        val person: Person? = personQueryService.findByName(PERSON_NAME)
        notNull(person)
        if (person != null) {
            isTrue(PERSON_NAME.equals(person.name))
            isTrue(PERSON_AGE == person.age)
        }
    }

    @Test
    fun shouldNotFindUserIfNotExist() {
        try {
            personQueryService.findByName(PERSON_NAME + "_NAME_POSTFIX")
            fail("shouldNotFindUserIfNotExist failed")
        } catch (e : RuntimeException) {
            isTrue(e.message.equals("Can not find person with name Radek_NAME_POSTFIX"))
        }
    }

    @Test
    fun shouldFindAllPersons() {
        val persons : MutableIterable<Person> = personQueryService.findAll()
        isTrue(persons.count() == 1)
    }

    @Test
    fun shouldNotFindPersonsIfTheyNotExist() {
        personCommandService.delete(PERSON_NAME)
        val persons : MutableIterable<Person> = personQueryService.findAll()
        isTrue(persons.count() == 0)
        personCommandService.save(Person(PERSON_NAME, PERSON_AGE))
    }
}