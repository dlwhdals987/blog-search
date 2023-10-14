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
        //given
        val uri = "/api/v1/blog/search"
        val query = "맛집"
        val page = 1
        val sort = "recency"
        val blogName = "맛집"
        val contents = "집이 최고야"
        val dateTime = LocalDateTime.now()
        val thumbnail = "null"
        val title = "테스트 코드 작성 방법 가이드"
        val url = "https://github.com/dlwhdals987"
        val isEnd = true
        val pageableCount = 10
        val totalCount = 100
        val document = BlogSearch.Document(blogName, contents, dateTime, thumbnail, title, url)
        val meta = BlogSearch.Meta(isEnd, pageableCount, totalCount)
        val blogSearch = BlogSearch(List.of(document), meta)
        val parameterMap: MultiValueMap<String, String> = LinkedMultiValueMap()
        parameterMap.add(ApiQueryParameterKey.KAKAO.query, query)
        parameterMap.add(ApiQueryParameterKey.KAKAO.page, page.toString())
        parameterMap.add(ApiQueryParameterKey.KAKAO.sort, sort)
        val blogSearchRequest = BlogSearchRequest(query, page, sort)
        BDDMockito.given(searchService.searchBlog(blogSearchRequest)).willReturn(blogSearch)

        mockMvc.perform(MockMvcRequestBuilders.get(uri).params(parameterMap))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("success"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.documents[0].blogname").value("민수의 자바"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.documents[0].contents").value("테스트 코드는 이렇게 작성합니다 ~~"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.documents[0].thumbnail").value("null"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.documents[0].title").value("테스트 코드 작성 방법 가이드"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.documents[0].url").value("https://github.com/minsoozz"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.meta.isEnd").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.meta.pageableCount").value(10))
            .andExpect(MockMvcResultMatchers.jsonPath("$.data.meta.totalCount").value(100))
            .andDo(MockMvcResultHandlers.print())
    }
}