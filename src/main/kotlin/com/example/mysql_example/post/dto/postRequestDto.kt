package com.example.mysql_example.post.dto

import com.example.mysql_example.post.entity.Post



data class PostRequestDto(
    var id : Long,
    var title : String,
    var post : String,
    val userId : Long,
    var isPublic : Boolean = true,
){
    fun toEntity() : Post = Post(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
    )
}

data class PostResponseDto(
    var id : Long,
    var title : String,
    var post : String,
    val userId : Long,
    var isPublic: Boolean = true,
){
    fun toResponse() : PostResponseDto = PostResponseDto(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
    )
}
