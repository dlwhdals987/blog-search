package com.search.blog.search.dto.kakao

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.search.blog.search.dto.common.ApiResponseDto
import com.search.blog.search.dto.common.BlogApiMetadata
import com.search.blog.search.dto.common.BlogApiPost
import java.time.LocalDateTime



@JvmRecord
data class KakaoApiResponseDto(val documents: List<Document>, val meta: Meta) : ApiResponseDto {

    override fun getContents(): List<BlogApiPost> {
        return documents
            .map(BlogApiPost.Companion::from)

    }

    override fun getMeta(): BlogApiMetadata {
        return BlogApiMetadata.of(meta.isEnd, meta.pageableCount, meta.totalCount)
    }

    data class Document(
        @JsonProperty("blogname") val blogName: String,
        val contents: String,
        @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
            timezone = "Asia/Seoul"
        ) val datetime: LocalDateTime,
        val thumbnail: String,
        val title: String,
        val url: String
    )

    data class Meta(
        @JsonProperty("is_end") val isEnd: Boolean,
        @JsonProperty("pageable_count") val pageableCount: Int,
        @JsonProperty("total_count") val totalCount: Int
    )
}

