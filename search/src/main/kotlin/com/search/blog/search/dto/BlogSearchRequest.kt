package com.search.blog.search.dto


data class BlogSearchRequest(
    val query: String?="",
    val page: Int?,
    val sort: String?
){
    companion object{
        fun of(blogSearchRequest:BlogSearchRequest):BlogSearchRequest{
            return BlogSearchRequest(
                query = blogSearchRequest.query,
                page = blogSearchRequest.page?:1,
                sort = blogSearchRequest.sort?:"accuracy"
            )

        }
    }
}

