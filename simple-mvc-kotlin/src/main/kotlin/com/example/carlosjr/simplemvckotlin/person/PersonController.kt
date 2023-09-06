package com.example.carlosjr.simplemvckotlin.person

import jakarta.validation.Valid
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
class PersonController(private val service: PersonService,
                       private val cacheManger: CacheManager) {

    @PostMapping("/pessoas")
    fun create(@RequestBody @Valid personDto: PersonDto,
                ucb: UriComponentsBuilder) : ResponseEntity<Void> {

        val savedDto = service.create(personDto)

        cacheManger.getCache("person-cache")!!.put(savedDto.id!!, ResponseEntity( savedDto, HttpStatus.OK ))

        val resourceUrl =
            ucb
                .path("/pessoas/{id}")
                .buildAndExpand(savedDto.id)
                .toUri()

        return ResponseEntity.created(resourceUrl).build()
    }

    @GetMapping("/pessoas/{personId}")
    @Cacheable(value = ["person-cache"], key = "#id")
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