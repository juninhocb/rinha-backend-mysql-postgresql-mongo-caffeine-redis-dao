package com.example.carlosjr.simplemvckotlin.person

import java.util.*

interface PersonRepository {
    fun findById(id: UUID) : Optional<Person>
    fun save(person: Person) : String
    fun findByCriteria(criteria: String) : List<Person>
    fun count() : Long
}