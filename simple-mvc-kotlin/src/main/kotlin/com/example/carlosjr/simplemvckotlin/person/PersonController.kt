package com.example.carlosjr.simplemvckotlin.person

import jakarta.validation.Valid
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import java.util.UUID

@RestController
class PersonController(private val service: PersonService) {

    @PostMapping("/pessoas")
    fun create(@RequestBody @Valid personDto: PersonDto,
                ucb: UriComponentsBuilder) : ResponseEntity<Void> {

        val id = service.create(personDto)

        val resourceUrl =
            ucb
                .path("/pessoas/{id}")
                .buildAndExpand(id)
                .toUri()

        return ResponseEntity.created(resourceUrl).build()
    }

    @GetMapping("/pessoas/{personId}")
    fun getById(@PathVariable(name = "personId") id: UUID)
                                        : ResponseEntity<PersonDto>{
        return ResponseEntity.ok().body(service.getById(id))
    }

    @GetMapping("/pessoas")
    fun getByCriteria(@RequestParam(name = "t") criteria: String)
                                        : ResponseEntity<Set<PersonDto>>{
        return ResponseEntity.ok().body(service.getByCriteria(criteria))
    }

    @GetMapping("/contagem-pessoas")
    fun getCount() : ResponseEntity<String> {
        return ResponseEntity.ok().body(service.getCount())
    }

}