package com.search.blog.search.service

import com.search.blog.search.domain.blog.BlogSearch
import com.search.blog.search.dto.BlogSearchRequest
import com.search.blog.search.enum.ApiQueryParameterKey
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import java.time.LocalDateTime
import java.util.List

@SpringBootTest
@ConfigurationPropertiesScan("com.search.blog.search.config")
@AutoConfigureMockMvc
class SearchServiceTest (
) {
    @Autowired private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var searchService: SearchService
    @Test
    @Throws(Exception::class)
    fun 블로그를_검색한다() {
        val query = "맛집"
        val page = 1
        val sort = "recency"
        val parameterMap: MultiValueMap<String, String> = LinkedMultiValueMap()
        parameterMap.add(ApiQueryParameterKey.KAKAO.query, query)
        parameterMap.add(ApiQueryParameterKey.KAKAO.page, page.toString())
        parameterMap.add(ApiQueryParameterKey.KAKAO.sort, sort)
        val blogSearchRequest = BlogSearchRequest(query, page, sort)
        var search = searchService.searchBlog(blogSearchRequest)
    }
}