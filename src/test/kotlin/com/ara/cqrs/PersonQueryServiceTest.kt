package com.ara.cqrs

import com.ara.cqrs.service.PersonQueryService
import com.ara.mvc.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonQueryServiceTest(@Autowired private val personQueryService: PersonQueryService,
                             @Autowired private val personRepository: PersonRepository) {
}