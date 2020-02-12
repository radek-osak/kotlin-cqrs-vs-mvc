package com.ara.cqrs.service.impl

import com.ara.cqrs.repository.PersonCommandRepository
import com.ara.cqrs.service.PersonCommandService
import com.ara.cqrs.service.PersonQueryService
import com.ara.model.Person
import org.springframework.stereotype.Service

@Service
class DefaultPersonCommandService(private val personCommandRepository: PersonCommandRepository,
                                  private val personQueryService: PersonQueryService) : PersonCommandService {

    override fun save(person: Person) {
        personCommandRepository.save(person)
    }

    override fun delete(id: Long) {
        val person : Person = personQueryService.findById(id)
        personCommandRepository.delete(person)
    }
}