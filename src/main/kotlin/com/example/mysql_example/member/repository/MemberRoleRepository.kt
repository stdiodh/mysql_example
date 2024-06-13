package com.example.mysql_example.member.repository

import com.example.mysql_example.member.entity.MemberRole
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRoleRepository : JpaRepository<MemberRole, Long?>