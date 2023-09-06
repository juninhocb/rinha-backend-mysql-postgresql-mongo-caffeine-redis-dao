package com.example.carlosjr.simplemvckotlin.person

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*
import java.time.LocalDate
import java.util.*

data class PersonDto(
    @Null
    val id: UUID? = null,
    @field:Size(max = 32)
    @JsonProperty("apelido")
    @field:NotBlank(message = "Não pode ser null (ou vazio)")
    @field:Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+\$", message = "Apelido deve ser string e não número")
    val nickname: String,
    @field:Size(max = 100)
    @JsonProperty("nome")
    @field:NotBlank(message = "Não pode ser null (ou vazio)")
    @field:Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Nome deve ser string e não número")
    val name: String,
    @JsonProperty("nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @field:NotNull(message = "Data não pode ser null")
    val bornAt: LocalDate,
    val stack: Set<String>? = null
    ) {
    constructor() : this(null, "", "", LocalDate.now(), null)
}