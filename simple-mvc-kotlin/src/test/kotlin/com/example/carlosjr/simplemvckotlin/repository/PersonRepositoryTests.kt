package com.example.carlosjr.simplemvckotlin.repository

import com.example.carlosjr.simplemvckotlin.person.PersonRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@DataJpaTest
class PersonRepositoryTests(@Autowired val repository: PersonRepository) {

    @Test
    fun shouldFindByCriteria() {

        val persisted = repository
            .findByNameOrNicknameOrStackOrBornAt("john", "john", "john", "john")

        assertThat(persisted).isNotNull

        persisted.forEach {
            println(it.name)
        }

    }

}