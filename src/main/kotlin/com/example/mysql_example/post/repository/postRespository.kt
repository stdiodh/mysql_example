package com.example.mysql_example.post.repository

import com.example.mysql_example.post.entity.Post
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long>{
    fun findByUserId(id : Long) : Post

    @Query(value = "SELECT p FROM Post p LEFT JOIN FETCH p.comments")
    fun findAllByFetchJoin() : List<Post>
}
