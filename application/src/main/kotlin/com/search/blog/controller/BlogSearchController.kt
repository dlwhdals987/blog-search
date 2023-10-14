package com.search.blog.controller

import com.search.blog.common.dto.BaseResponseDto
import com.search.blog.search.domain.blog.BlogSearch
import com.search.blog.search.dto.BlogSearchRequest
import com.search.blog.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/blog")
class BlogSearchController(private val searchService: SearchService) {
    @GetMapping("/search")
    fun findBlogsSearchResults(requestDto: BlogSearchRequest): BaseResponseDto<BlogSearch?>? {
        return BaseResponseDto.ok(
            searchService.searchBlog(
                requestDto
            )
        )
    }
}