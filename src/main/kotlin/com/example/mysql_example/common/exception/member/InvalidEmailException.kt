package com.example.mysql_example.common.exception.member

class InvalidEmailException (
    val fieldname : String = "",
    massage : String = "에러가 발생했습니다."
) : RuntimeException(massage)