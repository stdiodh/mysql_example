package com.example.mysql_example.member.controller

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.common.dto.CustomUser
import com.example.mysql_example.common.dto.Tokeninfo
import com.example.mysql_example.member.dto.LoginDto
import com.example.mysql_example.member.dto.MemberRequestDto
import com.example.mysql_example.member.dto.MemberResponseDto
import com.example.mysql_example.member.service.MemberService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
@Tag(name = "회원 Api 컨트롤러", description = "회원 가입, 로그인, 내 정보 조회 Api 명세서 입니다.")

@RestController
@RequestMapping("/api/member")
class MemberController (
    private val memberService : MemberService
){
    //회원가입 api
    @PostMapping("/join")
    private fun signUp(@Valid @RequestBody memberRequestDto: MemberRequestDto)
    : ResponseEntity<BaseResponse<String>>{
        val result = memberService.signUp(memberRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse(data = result))
    }

    //로그인 api
    @PostMapping("/login")
    private fun login(@Valid @RequestBody loginDto: LoginDto)
    : ResponseEntity<BaseResponse<Tokeninfo>> {
        val tokenInfo = memberService.login(loginDto)
        return ResponseEntity.status(HttpStatus.OK)
            .body(BaseResponse(data = tokenInfo))
    }

    //내 정보 조회 api
    @SecurityRequirement(name = "BearerAuth")
    @GetMapping("/info")
    private fun searchMyInfo()
    : ResponseEntity<BaseResponse<MemberResponseDto>> {
        val id = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val result = memberService.searchMyInfo(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //로그아웃 api
    @SecurityRequirement(name = "BearerAuth")
    @DeleteMapping("/logout")
    private fun logout() : ResponseEntity<BaseResponse<String>>{
        val memberId = (SecurityContextHolder.getContext().authentication.principal as CustomUser).id
        val result = memberService.logout(memberId)
        return ResponseEntity.status(HttpStatus.OK).body(
            BaseResponse(
                data = result
            )
        )
    }

    @PostMapping("/refresh")
    private fun issueAccessToken(@RequestHeader refreshToken: String) {

    }
}