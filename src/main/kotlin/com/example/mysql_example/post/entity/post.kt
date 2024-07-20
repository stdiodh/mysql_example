package com.example.mysql_example.post.entity

import com.example.mysql_example.post.dto.PostResponseDto
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Post (
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long?,
    @Column(nullable = false, length = 100)
    var title : String,
    @Column(nullable = false, length = 2000)
    var post : String,
    @Column(nullable = false, length = 50)
    val userId : Long,
    @Column(nullable = false, length = 10)
    var isPublic : Boolean = true,

    @Column(nullable = false, length = 1000)
    var imageUrl : String?

){
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "post", cascade = [CascadeType.ALL])
    var comments : List<Comment>? = null
    fun toResponse() : PostResponseDto = PostResponseDto(
        id = id,
        title = title,
        post = post,
        userId = userId,
        isPublic = isPublic,
        comments = comments?.map { it.toResponse() },
        imageUrl = imageUrl
    )
}