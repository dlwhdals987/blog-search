package com.search.blog.popular.service

import com.search.blog.popular.dto.PopularKeywordDto

interface PopularService {
    fun increaseKeyword(keyword:String?)
    fun findPopularKeywords():List<PopularKeywordDto>
}