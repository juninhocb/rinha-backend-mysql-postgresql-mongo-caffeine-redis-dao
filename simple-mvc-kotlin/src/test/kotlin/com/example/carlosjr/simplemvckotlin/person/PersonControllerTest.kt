package com.example.carlosjr.simplemvckotlin.person

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.constraints.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.*
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.util.*

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PersonControllerTest(@Autowired private val restTemplate: TestRestTemplate,
                           @Autowired private val objectMapper: ObjectMapper) {

    lateinit var validPerson : PersonDto

}

    /*
    @BeforeEach
    fun setUp() {
        validPerson = PersonDto(
            nickname = "ana",
            name = "Ana Barbosa",
            bornAt = LocalDate.parse("1985-09-23"),
            stack =  setOf("Node", "Postgres")
        )
    }


    //@Test
    @DirtiesContext
    fun shouldCreateAndGetResourceLocation() {

        val postResponse = restTemplate
            .postForEntity("/pessoas", validPerson, Void.TYPE )

        assertThat(postResponse.statusCode).isEqualTo(HttpStatus.CREATED)

        val resource = postResponse.headers.location

        val getResponse = restTemplate
            .getForEntity(resource, PersonDto::class.java)

        assertThat(getResponse.statusCode).isEqualTo(HttpStatus.OK)

        assertThat(getResponse.body!!.name).isEqualTo("Ana Barbosa")

        println(getResponse.body)

    }

    /*
    //@Test
    @DirtiesContext
    fun shouldPersistEvenIfStackIsNull() {
        val p = PersonDto(
            nickname = "josé",
            name = "José Roberto",
            bornAt = LocalDate.parse("2000-10-01"),
            stack =  null)

        val postResponse = restTemplate
            .postForEntity("/pessoas", p, Void.TYPE )

        assertThat(postResponse.statusCode).isEqualTo(HttpStatus.CREATED)
    } */

    //@Test
    @DirtiesContext
    fun shouldReturn422ForInvalidRequests(){

        val postResponse = restTemplate
            .postForEntity("/pessoas", validPerson, Void.TYPE)

        assertThat(postResponse.statusCode).isEqualTo(HttpStatus.CREATED)

        val postResponse2 = restTemplate
            .postForEntity("/pessoas", validPerson, Void.TYPE)

        assertThat(postResponse2.statusCode).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)

        val invalidNullName = InvalidPersonDto(
            nickname = "joseph",
            bornAt = LocalDate.parse("2000-10-01"),
            stack =  null)

        val postResponse3 = restTemplate
            .postForEntity("/pessoas", invalidNullName, Void.TYPE)

        assertThat(postResponse3.statusCode).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)

        val invalidNullNickname = InvalidPersonDto(
            name = "George Silver",
            bornAt = LocalDate.parse("2000-10-01"),
            stack =  null)

        val postResponse4 = restTemplate
            .postForEntity("/pessoas", invalidNullNickname, Void.TYPE)

        assertThat(postResponse4.statusCode).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)

        val invalidNumberName = InvalidPersonDto(
            nickname = "johny",
            name = 1,
            bornAt = LocalDate.parse("2000-10-01"),
            stack =  null)

        val postResponse5 = restTemplate
            .postForEntity("/pessoas", invalidNumberName, Void.TYPE)

        assertThat(postResponse5.statusCode).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)

        val invalidStackWithNumber = InvalidPersonDto(
            nickname = "ana",
            name = "Ana Barbosa",
            bornAt = LocalDate.parse("1985-09-23"),
            stack =  setOf(1, "Postgres"))

        val postResponse6 = restTemplate
            .postForEntity("/pessoas", invalidStackWithNumber, Void.TYPE)

        assertThat(postResponse6.statusCode).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)

    }

    //@Test
    @DirtiesContext
    fun shouldRetrieveFromDatabaseByCriteria() {

        val postResponse = restTemplate
            .postForEntity("/pessoas", validPerson, Void.TYPE )

        assertThat(postResponse.statusCode).isEqualTo(HttpStatus.CREATED)

        val responseType = object : ParameterizedTypeReference<Set<PersonDto>>() {}

        //by exactly nickname
        val getResponse = restTemplate.exchange(
            "/pessoas?t=ana",
            HttpMethod.GET,
            null,
            responseType
        )

        assertThat(getResponse.body!!.elementAt(0).nickname).isEqualTo("ana")

        //by peace of name
        val getResponse2 = restTemplate.exchange(
            "/pessoas?t=Barbosa",
            HttpMethod.GET,
            null,
            responseType
        )

        assertThat(getResponse2.body!!.elementAt(0).nickname).isEqualTo("ana")

        //by peace of birth
        val getResponse3 = restTemplate.exchange(
            "/pessoas?t=1985",
            HttpMethod.GET,
            null,
            responseType
        )

        assertThat(getResponse3.body!!.elementAt(0).nickname).isEqualTo("ana")

        //by peace of stack
        val getResponse4 = restTemplate.exchange(
            "/pessoas?t=Node",
            HttpMethod.GET,
            null,
            responseType
        )

        assertThat(getResponse4.body!!.size).isGreaterThan(1)

        println(getResponse4.body)

    }

    //@Test
    fun shouldRetrieveTheCorrectNumberOfPersonInDatabase() {

        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_PLAIN)

        val getResponse = restTemplate.exchange(
            "/contagem-pessoas",
            HttpMethod.GET,
            HttpEntity<Any>(headers),
            String::class.java
        )

        assertThat(getResponse.body).isEqualTo("3")

    }

    data class InvalidPersonDto(
        val id: UUID? = null,
        val nickname: String? = null,
        val name: Any? = null,
        val bornAt: LocalDate? = null,
        val stack: Set<Any>? = null
    )
}*/