package com.example.mysql_example.common.authority

import com.example.mysql_example.common.dto.CustomUser
import com.example.mysql_example.common.dto.Tokeninfo
import com.example.mysql_example.member.entity.RefreshToken
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

const val EXPIRATION_MILLISECONDS : Long = 1000 * 60 * 30L

const val REFLESH_EXPIRATION_MILLISECONDS : Long = 1000 * 60 * 60 * 24 * 365L


@Component
class JwtTokenProvider {
    @Value("\${jwt.secret}")
    lateinit var secretKey : String

    private val key by lazy { Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))}

    //토큰 생성 (서비스 사용, 로그인할 때 사용)
    fun createAccessToken(authentication : Authentication) : String {
        // 권한 추출
        val authorities : String = authentication
            .authorities
            .joinToString(",", transform = GrantedAuthority::getAuthority)

        //언제 생성되었는지
        val now = Date()

        //access 토큰 유효시간 (현재시간 + 30분)
        val accessExpiration = Date(now.time + EXPIRATION_MILLISECONDS)

        val userId = (authentication.principal as CustomUser).id

        return Jwts
            .builder()
            .subject(authentication.name)
            .claim("auth", authorities)
            .claim("userId", userId)
            .issuedAt(now)
            .expiration(accessExpiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    //refreshToken 분리
    fun createRefreshToken() : String {
        val now = Date()
        //refresh 토큰 유효시간 (현재시간 + 1년)
        val refreshExpration = Date(now.time + REFLESH_EXPIRATION_MILLISECONDS)
        return Jwts
            .builder()
            .issuedAt(now)
            .expiration(refreshExpration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    //토큰에서 권한 추출 (서비스 사용)

    fun getAuthentication(token: String) : Authentication {
        val claims : Claims = getClaims(token)

        val auth = claims["auth"] ?: throw RuntimeException("유효하지 않은 토큰입니다!")
        val userId = claims["userId"] ?: throw RuntimeException("유효하지 않은 토큰입니다!")

        val authorities : Collection<GrantedAuthority> = (auth as String)
            .split(",")
            .map { SimpleGrantedAuthority(it) }

        val principal : UserDetails = CustomUser(userId.toString().toLong(), Claims.SUBJECT, "", authorities)
        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    fun validateToken(token: String) : Boolean {
        try {
            val clams = getClaims(token)
            return clams.expiration.after(Date())
        } catch (e : Exception) {
            println(e.message)
        }
        return false
    }

    //토큰 claims 추출
    private fun getClaims (token : String) : Claims =
        Jwts
            .parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload

}
