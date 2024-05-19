package com.example.mysql_example.post.repository

import com.example.mysql_example.post.entity.Post
import org.springframework.data.repository.CrudRepository

interface PostRepository : CrudRepository<Post, Long>
