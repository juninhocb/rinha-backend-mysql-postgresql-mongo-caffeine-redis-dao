package com.example.carlosjr.simplemvckotlin.person

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "person")
data class Person(
    val _id: ObjectId? = null,
    val nickname: String,
    val name: String,
    val bornAt: String,
    val stack: String?
)
