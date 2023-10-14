package com.search.blog.search.service.externalApi.default

import com.search.blog.search.dto.common.ApiResponseDto

interface DefaultSearchApiCallService {
    fun searchBlogs(query: String?, page: Int, sort: String): ApiResponseDto
}