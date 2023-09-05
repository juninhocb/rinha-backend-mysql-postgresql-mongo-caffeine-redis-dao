package com.example.carlosjr.simplemvckotlin.exception

//import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.sql.SQLIntegrityConstraintViolationException

@ControllerAdvice
class GlobalExceptionHandler {

    /*
    @ExceptionHandler(Exception::class)
    fun genericExceptionHandler(ex: Exception,
                             request: HttpServletRequest)
            : ResponseEntity<StandardError> {

        val err = StandardError(
            msg = ex.message!!,
            path = request.requestURI,
            code = HttpStatus.INTERNAL_SERVER_ERROR.value()
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err)
    }*/


    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolation(ex: DataIntegrityViolationException,
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

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                         request: HttpServletRequest)
            : ResponseEntity<StandardError> {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            StandardError(
                msg = ex.message,
                path = request.requestURI,
                code = HttpStatus.BAD_REQUEST.value(),
            )
        )
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handleMissingServletRequestParam(ex: MissingServletRequestParameterException,
                             request: HttpServletRequest)
            : ResponseEntity<StandardError> {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            StandardError(
                msg = ex.message,
                path = request.requestURI,
                code = HttpStatus.BAD_REQUEST.value(),
            )
        )
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