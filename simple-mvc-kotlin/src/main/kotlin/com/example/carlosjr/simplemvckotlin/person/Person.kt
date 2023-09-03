package com.example.carlosjr.simplemvckotlin.person

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes
import java.util.*

@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @GenericGenerator(name = "UUID")
    @JdbcTypeCode(SqlTypes.CHAR)
    val id: UUID? = null,
    @Column(columnDefinition = "VARCHAR(32)", unique = true)
    val nickname: String,
    @Column(columnDefinition = "VARCHAR(100)")
    val name: String,
    @Column(name = "born_at", columnDefinition = "VARCHAR(10)")
    val bornAt: String,
    @Column(columnDefinition = "VARCHAR(32)")
    val stack: String?
) {
    constructor() : this(null, "", "", "", null)
}
