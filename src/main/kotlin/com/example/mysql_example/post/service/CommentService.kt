package com.example.mysql_example.post.service

import com.example.mysql_example.common.exception.PostException
import com.example.mysql_example.post.dto.CommentRequestDto
import com.example.mysql_example.post.dto.CommentResponseDto
import com.example.mysql_example.post.entity.Post
import com.example.mysql_example.post.repository.CommentRepository
import com.example.mysql_example.post.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CommentService {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    @Autowired
    private lateinit var postRepository: PostRepository

    //댓글 가져오기 기능
    fun getComments() : List<CommentResponseDto> {
        val result = commentRepository.findAll()
        return result.map { it.toResponse()}
    }

    //댓글 달기 기능
    fun postComment(id : Long, commentRequestDto: CommentRequestDto) : CommentResponseDto {
        val post : Post = postRepository.findByIdOrNull(id)
            ?: throw PostException("게시글이 존재하지 않습니다.")

        commentRequestDto.post = post

        val result = commentRepository.save(commentRequestDto.toEntity())
        return result.toResponse()
    }
}