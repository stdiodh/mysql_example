package com.example.mysql_example.common.service

import com.example.mysql_example.common.dto.CustomUser
import com.example.mysql_example.member.entity.Member
import com.example.mysql_example.member.repository.MemberRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service

//상속 후 멤버구현
class CustomUserDetailsService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
) : UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByEmail(username)
            ?.let { createUserDetails(it) }
            ?:throw UsernameNotFoundException("해당하는 유저는 존재하지 않습니다!")
    }

    private fun createUserDetails(member : Member) : UserDetails {
        return CustomUser(
            member.id!!,
            member.email,
            passwordEncoder.encode(member.password),
            member.role!!.map { SimpleGrantedAuthority("ROLE_${it.role}") }
        )
    }
}