package com.example.carlosjr.simplemvckotlin.person

import java.util.*

interface PersonService {

    fun create(personDto: PersonDto) : PersonDto
    fun getById(id: UUID) : PersonDto
    fun getByCriteria(criteria: String) : Set<PersonDto>
    fun getCount() : String

}