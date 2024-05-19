package com.example.mysql_example.post.entity

import com.example.mysql_example.post.dto.PostResponseDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Post (
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long,
    @Column(nullable = false)
    var title : String,
    @Column(nullable = false)
    var post : String,
    //아이디를 유저에게 받는 방법이 뭘까,,
    @Column(nullable = false)
    val userId : Long,
    var isPublic : Boolean = true,

){
    fun toResponse() : PostResponseDto = PostResponseDto(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
    )
}