package com.search.blog.search.domain.blog

import com.search.blog.search.dto.common.BlogApiMetadata
import com.search.blog.search.dto.common.BlogApiPost
import java.time.LocalDateTime


@JvmRecord
data class BlogSearch(val documents: List<Document>, val meta: Meta) {
    data class Document(
        val blogName: String,
        val contents: String,
        val datetime: LocalDateTime,
        val thumbnail: String?,
        val title: String,
        val url: String
    ) {

        companion object {
            fun from(document: BlogApiPost): Document {
                return Document(
                    document.blogName,
                    document.contents,
                    document.dateTime,
                    document.thumbnail,
                    document.title,
                    document.url
                )
            }
        }
    }

    data class Meta(val isEnd: Boolean?, val pageableCount: Int, val totalCount: Int) {


        companion object {
            fun from(metadata: BlogApiMetadata): Meta {
                return Meta(
                    metadata.isEnd,
                    metadata.pageableCount,
                    metadata.totalCount
                )
            }
        }
    }

    companion object {
        fun of(documents: List<Document>, meta: Meta): BlogSearch {
            return BlogSearch(documents, meta)
        }
    }
}

