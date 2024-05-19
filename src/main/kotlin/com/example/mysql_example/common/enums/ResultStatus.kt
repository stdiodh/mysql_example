package com.example.mysql_example.common.enums

//이넘 클래스 사용 이유 : 개발자 입장에서 쉽게 보기 위해
enum class ResultStatus(val msg : String) {
    SUCCESS("요청이 성공했습니다!"),
    ERROR("에러가 발생했습니다."),
}