package com.search.blog.search.service.externalApi.replace.impl

import com.search.blog.search.config.ApiProperties
import com.search.blog.search.dto.common.ApiResponseDto
import com.search.blog.search.dto.naver.NaverApiResponseDto
import com.search.blog.search.enum.ApiQueryParameterKey
import com.search.blog.search.exception.ApiBadRequestException
import com.search.blog.search.service.externalApi.replace.ReplaceSearchApiCallService
import com.search.blog.search.utils.HttpHeaderUtils
import com.search.blog.search.utils.ObjectMapperUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder


@Service
class ReplaceSearchApiCallServiceImpl(
    @param:Qualifier("naverRestTemplate") private val restTemplate: RestTemplate,
    private val apiProperties: ApiProperties,
    private val objectMapperUtils: ObjectMapperUtils
) : ReplaceSearchApiCallService {


    override fun replaceSearch(query: String?, page: Int, sort: String): ApiResponseDto? {
        val uriComponents = UriComponentsBuilder.fromHttpUrl(apiProperties.naver.apiUrl)
            .queryParam(ApiQueryParameterKey.NAVER.query, query)
            .queryParam(ApiQueryParameterKey.NAVER.page, page)
            .queryParam(ApiQueryParameterKey.NAVER.sort, convertSortOrderToFallbackSortString(sort))
            .build()
        val headers: HttpHeaders = HttpHeaderUtils.createJsonMediaType()
        headers[NAVER_CLIENT_ID] = apiProperties.naver.clientId
        headers[NAVER_CLIENT_SECRET] = apiProperties.naver.clientSecret
        val entity = HttpEntity<String>(headers)
        return try {
            val response = restTemplate.exchange(
                uriComponents.encode().toUri(),
                HttpMethod.GET,
                entity,
                String::class.java
            )
            objectMapperUtils.jsonToObject(response.body!!, NaverApiResponseDto::class.java)
        } catch (e: HttpClientErrorException) {
            val stackTraceElements = e.stackTrace
            logger.error("fallback : {}", stackTraceElements[0])
            throw ApiBadRequestException(e.message)
        }
    }

    private fun convertSortOrderToFallbackSortString(sort: String): String {
        return when (sort) {
            "recency" -> "date"
            else -> "sim"
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(ReplaceSearchApiCallServiceImpl::class.java)
        private const val NAVER_CLIENT_ID = "X-Naver-Client-Id"
        private const val NAVER_CLIENT_SECRET = "X-Naver-Client-Secret"
    }
}

