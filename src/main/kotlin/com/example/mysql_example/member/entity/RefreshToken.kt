package com.example.mysql_example.member.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash(value = "refreshToken", timeToLive = 1000 * 60 * 60 * 24 * 30L)
class RefreshToken (
    //import 확인 잘하기
    //누구의 토큰인지 알기 위함
    @Id
    @Indexed
    val memberId: Long,

    @Indexed
    val refreshToken : String,
)