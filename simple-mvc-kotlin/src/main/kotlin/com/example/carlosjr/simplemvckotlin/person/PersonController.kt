package com.example.carlosjr.simplemvckotlin.person

import jakarta.validation.Valid
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

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
    fun getById(@PathVariable(name = "personId") id: ObjectId)
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