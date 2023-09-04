package com.example.carlosjr.simplemvckotlin.exception

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class StandardError(
    val msg: String,
    val path: String,
    @JsonProperty("status_code")
    val code: Int,
    val timestamp: LocalDateTime = LocalDateTime.now(),
    @JsonProperty("class")
    val className: String? = ""
)