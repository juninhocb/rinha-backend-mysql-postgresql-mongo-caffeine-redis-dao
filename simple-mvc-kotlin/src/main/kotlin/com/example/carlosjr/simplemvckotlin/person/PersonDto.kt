package com.example.carlosjr.simplemvckotlin.person

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.*
import java.time.LocalDate
import java.util.*

data class PersonDto(
    @Null
    val id: UUID? = null,
    @Size(max = 32)
    @JsonProperty("apelido")
    @NotBlank(message = "Não pode ser null (ou vazio)")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Nome deve ser string e não número")
    val nickname: String,
    @Size(max = 100)
    @JsonProperty("nome")
    @NotBlank(message = "Não pode ser null (ou vazio)")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Nome deve ser string e não número")
    val name: String,
    @JsonProperty("nascimento")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Data não pode ser null")
    val bornAt: LocalDate,
    //@Size(max = 32)
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Stack deve ser um array apenas de Strings")
    val stack: Set<String>? = null
    ) {
    constructor() : this(null, "", "", LocalDate.now(), null)
}