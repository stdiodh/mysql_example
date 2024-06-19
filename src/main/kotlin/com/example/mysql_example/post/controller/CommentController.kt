package com.example.mysql_example.post.controller

import com.example.mysql_example.common.dto.BaseResponse
import com.example.mysql_example.post.dto.CommentRequestDto
import com.example.mysql_example.post.dto.CommentResponseDto
import com.example.mysql_example.post.service.CommentService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "BearerAuth")
@Tag(name = "댓글 Api 컨트롤러", description = "댓글 등록, 조회 API 명세서 입니다.")

@RestController
@RequestMapping("/api/posts/comments")

class CommentController {

    @Autowired
    private lateinit var commentService: CommentService

    //댓글 조회
    @GetMapping
    private fun getComments() : ResponseEntity<BaseResponse<List<CommentResponseDto>>> {
        val result = commentService.getComments()
        return ResponseEntity.status(HttpStatus.OK).body(BaseResponse(data = result))
    }

    //게시판 댓글 등록
    @PostMapping("/{id}")
    private fun postComment(@PathVariable id : Long, @RequestBody commentRequestDto: CommentRequestDto):
            ResponseEntity<BaseResponse<CommentResponseDto>> {
        val result = commentService.postComment(id, commentRequestDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponse(data = result))
    }

}