package com.search.blog.search.dto.naver

import com.fasterxml.jackson.annotation.JsonProperty
import com.search.blog.search.dto.common.ApiResponseDto
import com.search.blog.search.dto.common.BlogApiMetadata
import com.search.blog.search.dto.common.BlogApiPost


@JvmRecord
data class NaverApiResponseDto(
    @field:JsonProperty("lastBuildDate") @param:JsonProperty(
        "lastBuildDate"
    ) val lastBuildDate: String, @field:JsonProperty("total") @param:JsonProperty(
        "total"
    ) val total: Int, @field:JsonProperty("start") @param:JsonProperty(
        "start"
    ) val start: Int, @field:JsonProperty("display") @param:JsonProperty(
        "display"
    ) val display: Int, @field:JsonProperty("items") @param:JsonProperty(
        "items"
    ) val items: List<Item>
) : ApiResponseDto {
    data class Item(
        @JsonProperty("title") val title: String,
        @JsonProperty("link") val link: String,
        @JsonProperty("description") val description: String,
        @JsonProperty("bloggername") val bloggerName: String,
        @JsonProperty("bloggerlink") val bloggerLink: String,
        @JsonProperty("postdate") val postDate: String
    )


    override fun getContents(): List<BlogApiPost> {
        return items.map(BlogApiPost::from)
    }

    override fun getMeta(): BlogApiMetadata {
        return BlogApiMetadata.of(null, total, total)
    }

}