package com.search.blog.search.dto.common


interface ApiResponseDto {
    fun getContents(): List<BlogApiPost>

    fun getMeta(): BlogApiMetadata
}

