package com.example.mysql_example.member.entity

import com.example.mysql_example.common.enums.Gender
import com.example.mysql_example.member.dto.MemberResponseDto
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uk_member_email", columnNames = ["email"])]
)
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,

    //유니크 키, 업데이트 불가
    @Column(nullable = false, length = 100, updatable = false)
    val email : String,

    @Column(nullable = false, length = 100)
    val password : String,

    @Column(nullable = false, length = 10)
    var name : String,

    @Column(nullable = false, length = 30)
    @Temporal(TemporalType.DATE)
    var birthday : LocalDate,

    @Column(nullable = false, length = 5)
    @Enumerated(EnumType.STRING)
    var gender : Gender,

    ) {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    val role : List<MemberRole>? = null

    fun toResponse() : MemberResponseDto = MemberResponseDto(
        name = name,
        email = email,
        birthDay = birthday,
        gender = gender,
    )
}

