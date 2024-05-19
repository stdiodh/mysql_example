package com.example.mysql_example.post.service

import com.example.mysql_example.post.dto.PostRequestDto
import com.example.mysql_example.post.dto.PostResponseDto
import com.example.mysql_example.post.repository.PostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class PostService {
    @Autowired
    private lateinit var postRepository: PostRepository

    //리스트 전체 조회
    fun getPostList() : List<PostResponseDto>{
        val result = postRepository.findAll()
        return result.map {it.toResponse()}
    }

    //리스트 개별 조회
    fun getPostById(id : Long) : PostResponseDto{
        val result = postRepository.findByIdOrNull(id) ?:throw ResponseStatusException(HttpStatus.NOT_FOUND)
        return result.toResponse()
    }

    //게시물 생성
    fun titlePost (post: PostRequestDto) : PostResponseDto {
        val result = postRepository.save(post.toEntity())
        return result.toResponse()
    }

//    fun modifyList() : List<PostResponseDto>{
//        val result = postRepository.findAll()
//        return result.toResponce
//    }

    //게시물 수정은 Get과 동일하게 업데이트 함

    //게시물 삭제
    fun deletePostById(id : Long) : Unit {
        postRepository.deleteById(id)
    }

}