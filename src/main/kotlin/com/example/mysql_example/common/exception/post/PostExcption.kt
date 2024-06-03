package com.example.mysql_example.common.exception.post

class PostException(
    val msg : String = "에러가 발생했습니다!"
) : RuntimeException()