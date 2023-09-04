package com.example.carlosjr.simplemvckotlin.exception

//import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException

@ControllerAdvice
class GlobalExceptionHandler {

    companion object{
        var errorList : MutableList<StandardError> = mutableListOf()
    }

    @ExceptionHandler(Exception::class)
    fun genericExceptionHandler(ex: Exception,
                             request: HttpServletRequest)
            : ResponseEntity<StandardError> {

        val err = StandardError(
            msg = ex.message!!,
            path = request.requestURI,
            code = HttpStatus.INTERNAL_SERVER_ERROR.value(),
        )

        errorList.add(err)

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err)
    }

    @ExceptionHandler(PersonNotFoundException::class)
    fun handlePersonNotFound(ex: PersonNotFoundException,
                             request: HttpServletRequest)
                                : ResponseEntity<StandardError> {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            StandardError(
                msg = ex.message!!,
                path = request.requestURI,
                code = HttpStatus.NOT_FOUND.value(),
            )
        )
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun invalidRequestHandle(ex: SQLIntegrityConstraintViolationException,
                             request: HttpServletRequest)
                                : ResponseEntity<StandardError> {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            StandardError(
                msg = ex.message!!,
                path = request.requestURI,
                code = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            )
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun invalidJacksonConversion(ex: HttpMessageNotReadableException,
                             request: HttpServletRequest)
                                : ResponseEntity<StandardError> {

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            StandardError(
                msg = ex.message!!,
                path = request.requestURI,
                code = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            )
        )
    }



}