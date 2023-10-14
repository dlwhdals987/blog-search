package com.search.blog.search.service

import com.search.blog.search.converter.BlogSearchConverter
import com.search.blog.search.domain.blog.BlogSearch
import com.search.blog.search.dto.BlogSearchRequest
import com.search.blog.search.service.externalApi.default.DefaultSearchApiCallService
import org.springframework.stereotype.Service

@Service
class SearchService(private val defaultSearchApiCallService: DefaultSearchApiCallService) {
    fun searchBlog(blogSearchRequest: BlogSearchRequest): BlogSearch {
        val request = BlogSearchRequest.of(blogSearchRequest)
        val responseDto = defaultSearchApiCallService.searchBlogs(
            request.query,
            request.page!!,
            request.sort!!
        )
        return BlogSearchConverter.toBlog(responseDto)
    }
}