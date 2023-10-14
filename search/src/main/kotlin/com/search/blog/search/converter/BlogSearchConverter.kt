package com.search.blog.search.converter

import com.search.blog.search.domain.blog.BlogSearch
import com.search.blog.search.dto.common.ApiResponseDto


object BlogSearchConverter {
    fun toBlog(apiResponseDto: ApiResponseDto): BlogSearch {
        val documents: List<BlogSearch.Document> =
            apiResponseDto.getContents().stream().map(BlogSearch.Document::from)
                .toList()
        val meta: BlogSearch.Meta = apiResponseDto.getMeta().let { BlogSearch.Meta.from(it) }
        return BlogSearch.of(documents, meta)
    }
}

