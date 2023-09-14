package com.example.carlosjr.simplemvckotlin.person

data class Person(
    val id: String?,
    val nickname: String,
    val name: String,
    val bornAt: String,
    val stack: String?
) {
    constructor() : this(null, "", "", "", null)
}
