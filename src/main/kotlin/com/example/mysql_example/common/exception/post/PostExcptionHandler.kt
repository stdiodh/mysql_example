package com.example.mysql_example.common.exception.post

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.common.enums.ResultStatus
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@Order(value = 1)
@RestControllerAdvice
class PostExceptionHandler {

    @ExceptionHandler(PostException::class)
    protected fun noPostExceptionHandler(exception : PostException) :
            ResponseEntity<BaseResponse<Any>> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                BaseResponse(
                    status = ResultStatus.ERROR.name, resultMsg = exception.msg
                )
            )
    }
}