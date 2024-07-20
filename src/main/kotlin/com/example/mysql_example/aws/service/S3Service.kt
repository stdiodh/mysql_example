package com.example.mysql_example.aws.service

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.AmazonS3Exception
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class S3Service(
    private val amazonS3Client: AmazonS3Client
) {
    @Value("\${cloud.aws.bucket}")
    private lateinit var bucketName: String

    //이미지 업로드
    fun uploadImageS3(image: MultipartFile): String {
        if (image.isEmpty || image.originalFilename == null) {
            throw AmazonS3Exception("파일이 비었습니다!")
        }

        val extension: String = image.contentType!!
        val s3FileName: String = UUID.randomUUID().toString() + "." + extension
        val inputStream = image.inputStream

        val metadata = ObjectMetadata().apply {
            this.contentType = image.contentType
            this.contentLength = image.size
        }
        try {
            val putObjectRequest: PutObjectRequest =
                PutObjectRequest(bucketName, "image/$s3FileName", inputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            amazonS3Client.putObject(putObjectRequest)
        } catch (e: Exception) {
            throw AmazonS3Exception("이미지 업로드 에러가 발생했습니다.")
        } finally {
            inputStream.close()
        }
        return amazonS3Client.getResourceUrl(bucketName, "image/$s3FileName")
    }

    fun uploadImage(image: MultipartFile): String {
        return uploadImageS3(image)
    }

    fun uploadImages(images: List<MultipartFile>): List<String> {
        val imageUrls = mutableListOf<String>()
        for (image in images) {
            val imageUrl = uploadImageS3(image)
            imageUrls.add(imageUrl)
        }
        return imageUrls
    }


    fun deleteImage(key : String) : String {
        amazonS3Client.deleteObject(bucketName, key)
        return " 이미지가 삭제되었습니다."
    }
}