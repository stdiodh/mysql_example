package com.example.mysql_example.post.dto

import com.example.mysql_example.post.entity.Post
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank


data class PostRequestDto(
    var id : Long?,
    @field:NotBlank(message = "제목은 반드시 입력해야 됩니다.")
    var title : String,
    @field:NotBlank(message = "내용은 반드시 입력해야 됩니다.")
    var post : String,
    @field:Min(value = 1, message = "유효하지 않은 사용자입니다.")
    var userId : Long,
    var isPublic : Boolean,

    var imageUrl: String?
){
    fun toEntity() : Post = Post(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
        imageUrl = imageUrl
    )
}

data class PostResponseDto(
    var id : Long?,
    var title : String,
    var post : String,
    var userId : Long,
    var isPublic: Boolean,
    var comments : List<CommentResponseDto>?,
    var imageUrl : String?
)
