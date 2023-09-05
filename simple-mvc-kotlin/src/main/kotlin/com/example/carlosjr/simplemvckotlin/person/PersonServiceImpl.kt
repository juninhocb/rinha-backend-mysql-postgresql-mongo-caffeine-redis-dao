package com.example.carlosjr.simplemvckotlin.person

import com.example.carlosjr.simplemvckotlin.exception.PersonNotFoundException
import org.bson.types.ObjectId
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonServiceImpl(private val repository: PersonRepository,
                        private val mapper: PersonMapper) : PersonService {

    override fun create(personDto: PersonDto): ObjectId {
        return repository.save(
                mapper.dtoToEntity(personDto)
            )._id!!
    }

    override fun getById(id: ObjectId): PersonDto {
        return handleOpt(repository.findById(id))!!
    }

    override fun getByCriteria(criteria: String): Set<PersonDto> {
        return repository
            .findByCriteria(criteria)
            .map { mapper.entityToDto(it) }
            .toSet()
    }

    override fun getCount(): String {
        return repository.count().toString()
    }

    private fun handleOpt(personOpt: Optional<Person>) : PersonDto? {

        if (personOpt.isEmpty){
            throw PersonNotFoundException()
        }
        return mapper.entityToDto(personOpt.get())
    }
}