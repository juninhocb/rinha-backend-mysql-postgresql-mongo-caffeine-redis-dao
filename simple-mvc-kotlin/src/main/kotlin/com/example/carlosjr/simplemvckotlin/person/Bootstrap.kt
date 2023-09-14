package com.example.carlosjr.simplemvckotlin.person

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Profile("test")
@Component
class Bootstrap(private val repository: PersonRepository)
    : CommandLineRunner{

    override fun run(vararg args: String?) {

        val p1 = Person(
            id = "",
            nickname = "john",
            name = "John Green",
            bornAt = "2000-10-01",
            stack = "C#, Node, Oracle"
        )

        val p2 = Person(
            id = "",
            nickname = "peter",
            name = "Peter Red",
            bornAt = "2002-10-01",
            stack = "C#, C++"
        )

        val p3 = Person(
            id = "",
            nickname = "robert",
            name = "Robert Orange",
            bornAt = "2003-10-01",
            stack = null
        )

        repository.save(p1)
        repository.save(p2)
        repository.save(p3)
    }


}