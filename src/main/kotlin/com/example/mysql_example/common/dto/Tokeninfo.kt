package com.example.mysql_example.common.dto

//access token
class Tokeninfo (
    val grantType : String,
    val accessToken : String,
    val refreshToken : String,
)