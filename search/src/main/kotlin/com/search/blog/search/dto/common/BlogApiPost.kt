package com.search.blog.search.dto.common

import com.search.blog.search.dto.naver.NaverApiResponseDto
import com.search.blog.search.dto.kakao.KakaoApiResponseDto
import com.search.blog.search.utils.LocalDateTimeUtils
import java.time.LocalDateTime


data class BlogApiPost(
    val blogName: String,
    val contents: String,
    val dateTime: LocalDateTime,
    val thumbnail: String?,
    val title: String,
    val url: String
) {
    companion object {
        fun from(document: KakaoApiResponseDto.Document): BlogApiPost {
            return BlogApiPost(
                document.blogName,
                document.contents,
                document.datetime,
                document.thumbnail,
                document.title,
                document.url
            )
        }

        fun from(item: NaverApiResponseDto.Item): BlogApiPost {
            return BlogApiPost(
                item.bloggerName,
                item.description,
                LocalDateTimeUtils.stringToLocalDateTime(item.postDate),
                null,
                item.title,
                item.bloggerName
            )
        }
    }
}

