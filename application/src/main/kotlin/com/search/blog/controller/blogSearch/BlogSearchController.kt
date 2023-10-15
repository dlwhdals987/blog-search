package com.search.blog.controller.blogSearch

import com.search.blog.common.dto.BaseResponseDto
import com.search.blog.popular.dto.PopularKeywordDto
import com.search.blog.popular.service.PopularService
import com.search.blog.search.domain.blog.BlogSearch
import com.search.blog.search.dto.BlogSearchRequest
import com.search.blog.search.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/blog")
class BlogSearchController(
    private val searchService: SearchService,
    private val popularService: PopularService
) {
    @GetMapping("/search")
    fun findBlogsSearchResults(requestDto: BlogSearchRequest): BaseResponseDto<BlogSearch>? {
        val response = BaseResponseDto.ok(
            searchService.searchBlog(
                requestDto
            )
        )
        popularService.increaseKeyword(requestDto.query)
        return response
    }

    @GetMapping("/popular/search-keyword")
    fun findPopularKeywords(requestDto: BlogSearchRequest): BaseResponseDto<List<PopularKeywordDto>>? {
        return BaseResponseDto.ok(
            popularService.findPopularKeywords()
        )
    }

}