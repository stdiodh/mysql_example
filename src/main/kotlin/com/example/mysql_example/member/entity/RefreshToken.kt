package com.example.mysql_example.member.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refleshToken", timeToLive = 1000 * 60 * 60 * 24 * 365L)
class RefreshToken (
    //import 확인 잘하기
    @Id
    @Indexed
    val memberId: Long,

    //누구의 토큰인지 알기 위함
    val refreshToken : String,
)