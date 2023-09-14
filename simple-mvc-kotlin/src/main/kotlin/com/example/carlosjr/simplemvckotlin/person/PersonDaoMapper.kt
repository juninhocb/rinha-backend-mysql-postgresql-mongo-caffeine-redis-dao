package com.example.carlosjr.simplemvckotlin.person

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import java.sql.ResultSet

@Component
class PersonDaoMapper : RowMapper<Person> {
    override fun mapRow(rs: ResultSet, rowNum: Int): Person? {
        return Person(
            id = rs.getString("id"),
            nickname = rs.getString("nickname"),
            name = rs.getString("name"),
            bornAt = rs.getString("born_at"),
            stack = rs.getString("stack")
        )
    }


}