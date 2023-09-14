package com.example.carlosjr.simplemvckotlin.person

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import java.sql.Statement
import java.util.*

@Repository
class PersonRepositoryImpl(private val jdbcTemplate: JdbcTemplate,
                           private val rowMapper: PersonDaoMapper) : PersonRepository {
    override fun findById(id: UUID): Optional<Person> {
        val person = jdbcTemplate.queryForObject("" +
                "SELECT * " +
                "FROM person " +
                "WHERE id = ? ",
            rowMapper,
            id
        )
        return Optional.ofNullable(person)
    }

    override fun save(person: Person): String {
        val insertSql = "INSERT INTO person (name, nickname, born_at, stack) VALUES (?, ?, ?, ?) RETURNING id"

        val generatedKeys = GeneratedKeyHolder()

        jdbcTemplate.update(
            { connection ->
                val ps = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)
                ps.setString(1, person.name)
                ps.setString(2, person.nickname)
                ps.setString(3, person.bornAt)
                ps.setString(4, person.stack)
                ps
            },
            generatedKeys
        )

        return generatedKeys.keys?.getValue("id") as String
    }

    override fun findByCriteria(criteria: String): List<Person> {
        val likeCriteria = "%$criteria%"
        return jdbcTemplate
            .query("SELECT * " +
                   "FROM person " +
                   "WHERE nickname LIKE ? " +
                   "OR name LIKE ? " +
                   "OR stack LIKE ? " +
                   "OR born_at LIKE ?", rowMapper,
                   likeCriteria, likeCriteria, likeCriteria, likeCriteria)
    }

    override fun count(): Long {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM person", Long::class.java) ?: 0L
    }

}