package com.example.mysql_example.member.service

import com.example.mysql_example.common.authority.JwtTokenProvider
import com.example.mysql_example.common.dto.Tokeninfo
import com.example.mysql_example.common.enums.Role
import com.example.mysql_example.common.exception.member.InvalidEmailException
import com.example.mysql_example.member.dto.LoginDto
import com.example.mysql_example.member.dto.MemberRequestDto
import com.example.mysql_example.member.entity.Member
import com.example.mysql_example.member.entity.MemberRole
import com.example.mysql_example.member.repository.MemberRepository
import com.example.mysql_example.member.repository.MemberRoleRepository
import jakarta.transaction.Transactional
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.stereotype.Service

/*
서비스에서 발생하는 모든 쿼리에 transactional이 실행
Transactional에는 rollback, commit, checkpoint등이 존재
실패한 쿼리에 대해선 rollback을 사용가능
 */

@Transactional
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberRoleRepository: MemberRoleRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
) {
    //회원가입
    fun signUp(memberRequestDto: MemberRequestDto) : String {
        var member : Member? = memberRepository.findByEmail(memberRequestDto.email)
        if (member != null){
            throw InvalidEmailException(fieldName = "email", massage = "이미 가입한 이메일입니다!")
        }
        // 사용자가 회원가입을 했을 때 권한까지 부여
        member = memberRequestDto.toEntity()
        memberRepository.save(member)

        val memberRole = MemberRole(
            id = null,
            role = Role.MEMBER,
            member = member,
        )
        memberRoleRepository.save(memberRole)

        return "회원가입이 완료되었습니다."
    }

    //로그인

    fun login(loginDto: LoginDto) : Tokeninfo {
        val authenticationToken = UsernamePasswordAuthenticationToken(loginDto.email, loginDto.password)
        val authentication = authenticationManagerBuilder.`object`.authenticate(authenticationToken)
        return jwtTokenProvider.createToken(authentication)
    }
}