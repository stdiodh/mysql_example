package com.example.mysql_example.common.exception

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.common.enums.ResultStatus
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class PostExceptionHandler {

    @ExceptionHandler(PostException::class)
    protected fun noPostExeptionHandler(exception: PostException) :
            ResponseEntity<BaseResponse<Any>> {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                    BaseResponse(
                    status = ResultStatus.ERROR.name, resultMsg = exception.msg))
        }
}