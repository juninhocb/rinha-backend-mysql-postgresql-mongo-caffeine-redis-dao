package com.example.carlosjr.simplemvckotlin.person

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*
interface PersonRepository : MongoRepository<Person, ObjectId>{

    @Query("SELECT p FROM Person p WHERE p.nickname LIKE :criteria OR p.name LIKE :criteria OR p.stack LIKE :criteria OR p.bornAt LIKE :criteria")
    fun findByCriteria(@Param("criteria") criteria: String): List<Person>

}