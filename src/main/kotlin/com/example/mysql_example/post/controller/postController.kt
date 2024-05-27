package com.example.mysql_example.post.controller

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.post.dto.PostRequestDto
import com.example.mysql_example.post.dto.PostResponseDto
import com.example.mysql_example.post.service.PostService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/posts")
class postController {
    @Autowired
    private lateinit var postService : PostService

    //게시물 전체 조회
    @GetMapping
    private fun getPostList() : ResponseEntity<BaseResponse<List<PostResponseDto>>>{
        val result = postService.getPostList()
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //게시물 id로 개별 조회
    @GetMapping("/{id}")
    private fun getPostById(@PathVariable id : Long) : ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.getPostByUserId(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //사용자 ID에 해당하는 게시글 가져오는 Api
    @GetMapping("/user/{id}")
    private fun getPostByuserId(@PathVariable id : Long) : ResponseEntity<BaseResponse<PostResponseDto>>{
        val result = postService.getPostByUserId(id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //게시물 생성
    @PostMapping
    private fun postPost(@Valid @RequestBody postRequestDto: PostRequestDto) :
            ResponseEntity<BaseResponse<PostResponseDto>> {
        val result = postService.postPosts(postRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }

    //게시물 수정 아이디 추가
    @PutMapping("/{id}")
    private fun putPost(@Valid @RequestBody postRequestDto: PostRequestDto,@PathVariable id : Long) : ResponseEntity<BaseResponse<PostResponseDto>>{
        val result = postService.putPost(postRequestDto, id)
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //게시물 삭제
    @DeleteMapping("/{id}")
    private fun deletePostById(@PathVariable id : Long) : ResponseEntity<BaseResponse<Any>> {
        postService.deletePostById(id)
        return ResponseEntity.ok().body(BaseResponse(data = null))
    }
}