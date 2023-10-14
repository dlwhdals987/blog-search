package com.search.blog.search.enum


enum class ApiQueryParameterKey(val query: String, val sort: String, val page: String, val size: String) {
    KAKAO("query", "sort", "page", "size"),
    NAVER("query", "sort", "start", "display")

}

