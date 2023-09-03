package com.example.carlosjr.simplemvckotlin.person

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PersonRepository : JpaRepository<Person, UUID> {

    fun findByNameOrNicknameOrStackOrBornAt(name: String,
                                            nickname: String,
                                            stack: String,
                                            bornAt: String) : List<Person>

}