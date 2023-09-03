package com.example.carlosjr.simplemvckotlin.person

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class PersonMapper {

    fun dtoToEntity(dto: PersonDto) : Person{
        return Person(
            id = null,
            nickname = dto.nickname,
            name = dto.name,
            bornAt = dto.bornAt.toString(),
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
            nickname = entity.nickname,
            name = entity.name,
            bornAt = LocalDate.parse(entity.bornAt),
            stack = stringedSet
        )
    }

}