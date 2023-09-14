package com.example.carlosjr.simplemvckotlin.person

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*
import java.util.*

data class PersonDto (
    @JsonProperty("id")
    @Null
    var id: String? = null,
    @field:Size(max = 32)
    @JsonProperty("apelido")
    @field:NotBlank(message = "Não pode ser null (ou vazio)")
    @field:Pattern(regexp = "^[a-zA-Z ]+$", message = "Nome deve ser string e não número")
    val apelido: String,
    @field:Size(max = 100)
    @JsonProperty("nome")
    @field:NotBlank(message = "Não pode ser null (ou vazio)")
    @field:Pattern(regexp = "^[a-zA-Z ]+$", message = "Nome deve ser string e não número")
    val nome: String,
    @JsonProperty("nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @field:NotNull(message = "Data não pode ser null")
    val nascimento: String,
    @JsonProperty("stack")
    val stack: Set<String>? = null
) {
    constructor() : this(null, "", "", "", null)
}