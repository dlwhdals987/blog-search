package com.search.blog.search.service.externalApi.replace

import com.search.blog.search.dto.common.ApiResponseDto

interface ReplaceSearchApiCallService {
    fun replaceSearch(query: String?, page: Int, sort: String): ApiResponseDto?
}