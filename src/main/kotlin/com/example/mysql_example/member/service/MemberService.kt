package com.example.mysql_example.member.service

import com.example.mysql_example.common.exception.member.InvalidEmailException
import com.example.mysql_example.member.dto.MemberRequestDto
import com.example.mysql_example.member.entity.Member
import com.example.mysql_example.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {
    //회원가입
    fun signUp(memberRequestDto: MemberRequestDto) : String {
        val member : Member? = memberRepository.findByEmail(memberRequestDto.email)
        if (member != null){
            throw InvalidEmailException(fieldName = "email", massage = "이미 가입한 이메일입니다!")
        }
        memberRepository.save(memberRequestDto.toEntity())
        return "회원가입이 완료되었습니다."
    }
}