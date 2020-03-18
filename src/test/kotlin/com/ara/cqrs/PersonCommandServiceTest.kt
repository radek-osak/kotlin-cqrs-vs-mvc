package com.ara.cqrs

import com.ara.cqrs.repository.PersonCommandRepository
import com.ara.cqrs.service.PersonCommandService
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.util.Assert.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonCommandServiceTest (@Autowired private val personCommandService: PersonCommandService,
                                @Autowired private val personQueryService: PersonQueryService,
                                @Autowired private val personCommandRepository: PersonCommandRepository) {

    @AfterEach
    fun afterEach () {
        personCommandRepository.deleteAll()
    }

    @Test
    fun shouldSaveNewPerson() {
        val person = Person("Radek", 30)
        personCommandService.save(person)
        val result = personQueryService.findByName("Radek")
        isTrue(person.name.equals(result.name))
        isTrue(person.age == result.age)
    }

    @Test
    fun shouldSaveNewPersonWithExistingName() {
        val person1 = Person("Radek", 30)
        val person2 = Person("Radek", 20)
        personCommandService.save(person1)
        personCommandService.save(person2)

        val persons : MutableIterable<Person> = personQueryService.findAll()
        for (item in persons) {
            isTrue(item.name.equals(item.name))
        }
    }

    @Test
    fun shouldDeleteExistingPerson() {
        //given
        val person = Person("PERSON_TO_DELETE", 20)
        personCommandService.save(person)
        val result = personQueryService.findByName(person.name)
        notNull(result)
        isTrue(person.name.equals(result.name))

        //when
        personCommandService.delete(person.name)

        //then
        try {
            personQueryService.findByName(person.name)
            fail("shouldNotFindUserIfNotExist failed")
        } catch (e : RuntimeException) {
            isTrue(e.message.equals("Can not find person with name ${person.name}"))
        }
    }

    @Test
    fun shouldNotDeleteNotExistingPerson() {
        try {
            personCommandService.delete("TEST")
            fail("shouldNotDeleteNotExistingPerson failed")
        } catch (e : RuntimeException) {
            isTrue(e.message.equals("Can not find person with name TEST"))
        }
    }
}