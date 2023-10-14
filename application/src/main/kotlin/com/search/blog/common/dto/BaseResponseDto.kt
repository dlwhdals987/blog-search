package com.search.blog.common.dto


@JvmRecord
data class BaseResponseDto<T>(val message: String, val data: T) {
    companion object {
        fun <T> ok(data: T): BaseResponseDto<T> {
            return BaseResponseDto("success", data)
        }
    }
}

