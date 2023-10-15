package com.search.blog.popular.dto

import com.search.blog.popular.entity.PopularSearchEntity

data class PopularKeywordDto(
    val keyword: String,
    val count: Int
){
    companion object{
        fun from(popularSearchEntity: PopularSearchEntity):PopularKeywordDto{
            return PopularKeywordDto(
                keyword = popularSearchEntity.keyword,
                count = popularSearchEntity.count
            )
        }
    }
}