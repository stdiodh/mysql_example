package com.example.mysql_example.member.service

import com.example.mysql_example.common.exception.member.InvalidEmailException
import com.example.mysql_example.member.dto.MemberRequestDto
import com.example.mysql_example.member.entity.Member
import com.example.mysql_example.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

/*
서비스에서 발생하는 모든 쿼리에 transactional이 실행
Transactional에는 rollback, commit, checkpoint등이 존재
실패한 쿼리에 대해선 rollback을 사용가능
 */

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