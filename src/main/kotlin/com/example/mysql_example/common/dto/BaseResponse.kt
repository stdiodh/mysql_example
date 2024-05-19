package com.example.mysql_example.common.dto

import com.example.mysql_example.common.enums.ResultStatus

data class BaseResponse<T>(
    var status : String = ResultStatus.SUCCESS.name,
    var data : T? = null,
    var resultMsg : String = ResultStatus.SUCCESS.msg,

)
