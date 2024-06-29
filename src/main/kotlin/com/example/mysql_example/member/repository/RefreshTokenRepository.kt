package com.example.mysql_example.member.repository

import com.example.mysql_example.member.entity.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository : CrudRepository<RefreshToken, Long>