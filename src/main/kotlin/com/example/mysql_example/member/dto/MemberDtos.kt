package com.example.mysql_example.member.dto

import com.example.mysql_example.common.enums.Gender
import com.example.mysql_example.member.entity.Member
import java.time.LocalDate


data class MemberRequestDto (
    val id : Long?,
    val email : String,
    val password : String,
    val name : String,
    val birthDate : LocalDate,
    val gender : Gender,
) {
    fun toEntity() = Member(
        id = id,
        email = email,
        password = password,
        name = name,
        birthDate = birthDate,
        gender = gender
    )
}