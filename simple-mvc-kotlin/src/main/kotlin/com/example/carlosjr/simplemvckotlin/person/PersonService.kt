package com.example.carlosjr.simplemvckotlin.person

import java.util.UUID

interface PersonService {

    fun create(personDto: PersonDto) : String
    fun getById(id: UUID) : PersonDto
    fun getByCriteria(criteria: String) : Set<PersonDto>
    fun getCount() : String

}