package com.example.carlosjr.simplemvckotlin.person

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "person")
data class Person(
    val _id: ObjectId? = null, // MongoDB uses ObjectId as the default primary key
    val nickname: String,
    val name: String,
    val bornAt: String,
    val stack: String?
)