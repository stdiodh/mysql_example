package com.example.mysql_example.common.exception.member

class InvalidEmailException (
    val fieldName : String = "",
    massage : String = "에러가 발생했습니다."
) : RuntimeException(massage)