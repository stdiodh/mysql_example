package com.example.mysql_example.common.exception.member

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.common.enums.ResultStatus
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

//우선순위
@Order(value = 1)
@RestControllerAdvice
class MemberExceptionHanderler {
    @ExceptionHandler(InvalidEmailException::class)
    protected fun invalidEmailExceptionHandler(exception: InvalidEmailException)
    : ResponseEntity<BaseResponse<Map<String, String>>>{
        val error = mapOf(exception.fieldname to (exception.message?: "Not Exception Massage"))
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(
                BaseResponse(
                    status = ResultStatus.ERROR.name,
                    data = error,
                    resultMsg = ResultStatus.ERROR.msg,
                    )
            )
    }
}