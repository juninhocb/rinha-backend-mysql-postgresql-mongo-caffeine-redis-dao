package com.example.carlosjr.simplemvckotlin.person

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*


interface PersonRepository : JpaRepository<Person, UUID>, JpaSpecificationExecutor<Person> {

    @Query("SELECT p FROM Person p WHERE p.nickname LIKE %:criteria% OR p.name LIKE %:criteria% OR p.stack LIKE %:criteria% OR p.bornAt LIKE %:criteria%")
    fun findByCriteria(@Param("criteria") criteria: String): List<Person>

}