package com.example.carlosjr.simplemvckotlin.person

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PersonMapper {

    fun dtoToEntity(dto: PersonDto) : Person{
        return Person(
            id = null,
            nickname = dto.apelido,
            name = dto.nome,
            bornAt = dto.nascimento,
            stack = dto.stack.toString()
        )
    }

    fun entityToDto (entity: Person) : PersonDto {

        val dbString = entity.stack

        val stringedSet = dbString
            ?.trim(',')
            ?.split(',')
            ?.toSet()

        return PersonDto(
            id = entity.id!!,
            apelido = entity.nickname,
            nome = entity.name,
            nascimento = entity.bornAt,
            stack = stringedSet
        )
    }

}