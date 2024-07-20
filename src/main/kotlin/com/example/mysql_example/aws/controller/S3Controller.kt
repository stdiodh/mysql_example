package com.example.mysql_example.aws.controller

import com.example.mysql_example.aws.dto.S3RequestDto
import com.example.mysql_example.aws.service.S3Service
import com.example.mysql_example.common.dto.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/aws/s3")
class S3Controller(
    private val s3Service: S3Service
) {
    @PostMapping("/imageUpload")
    private fun imageUpload(@RequestPart(value = "image", required = false) image : MultipartFile)
    : ResponseEntity<BaseResponse<String>> {
        val result = s3Service.uploadImageS3(image)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(BaseResponse(data = result))
    }

//    @PostMapping("/imagesUpload")
//    private fun imagesUpload(@RequestPart(value = "image", required = false) image : MultipartFile)
//            : ResponseEntity<BaseResponse<List<String>>> {
//        val result = s3Service.uploadImages(image)
//        return ResponseEntity.status(HttpStatus.CREATED)
//            .body(BaseResponse(data = result))
//    }

    @DeleteMapping("/delete")
    private fun deleteImage(@RequestBody s3RequestDto: S3RequestDto)
    : ResponseEntity<String> {
        val result = s3Service.deleteImage(s3RequestDto.imageFileName)
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}