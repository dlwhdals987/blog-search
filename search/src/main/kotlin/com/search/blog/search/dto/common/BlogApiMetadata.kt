package com.search.blog.search.dto.common


@JvmRecord
data class BlogApiMetadata(val isEnd: Boolean?, val pageableCount: Int, val totalCount: Int) {
    companion object {
        fun of(isEnd: Boolean?, pageableCount: Int, totalCount: Int): BlogApiMetadata {
            return BlogApiMetadata(isEnd, pageableCount, totalCount)
        }
    }
}

