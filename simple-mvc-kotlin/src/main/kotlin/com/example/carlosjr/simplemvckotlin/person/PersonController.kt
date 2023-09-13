package com.example.carlosjr.simplemvckotlin.person

import com.example.carlosjr.simplemvckotlin.exception.RepeatedPersonException
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Valid
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@RestController
class PersonController(private val service: PersonService,
                       private val redisTemplate: RedisTemplate<String, PersonDto>) {

    @PostMapping("/pessoas")
    fun create(@RequestBody @Valid personDto: PersonDto,
                ucb: UriComponentsBuilder) : ResponseEntity<Void> {

        if (redisTemplate.opsForValue().get(personDto.apelido) != null) {
            throw RepeatedPersonException()
        }


        val id = service.create(personDto)

        val resourceUrl =
            ucb
                .path("/pessoas/{id}")
                .buildAndExpand(id)
                .toUri()

        personDto.id = id

        redisTemplate.opsForValue().set(id.toString(), personDto)

        redisTemplate.opsForValue().set(personDto.apelido, personDto)

        return ResponseEntity.created(resourceUrl).build()
    }

    @GetMapping("/pessoas/{personId}")
    fun getById(@PathVariable(name = "personId") id: UUID)
                                        : ResponseEntity<PersonDto>{

        println("test")
        val cachedValue = redisTemplate.opsForValue().get(id.toString())

        println("Old1!")
        if (cachedValue != null) {
            return ResponseEntity.ok().body(cachedValue)
        }

        println("Old2!")

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