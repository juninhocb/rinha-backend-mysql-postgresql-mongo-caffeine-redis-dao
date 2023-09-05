package com.example.carlosjr.simplemvckotlin.person

import org.bson.types.ObjectId

interface PersonService {

    fun create(personDto: PersonDto) : ObjectId
    fun getById(id: ObjectId) : PersonDto
    fun getByCriteria(criteria: String) : Set<PersonDto>
    fun getCount() : String

}