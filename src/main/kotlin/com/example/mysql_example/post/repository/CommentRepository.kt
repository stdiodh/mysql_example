package com.example.mysql_example.post.repository

import com.example.mysql_example.post.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long?>