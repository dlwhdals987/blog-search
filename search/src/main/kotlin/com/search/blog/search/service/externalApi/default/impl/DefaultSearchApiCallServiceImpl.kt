package com.search.blog.search.service.externalApi.default.impl

import com.search.blog.search.config.ApiProperties
import com.search.blog.search.dto.common.ApiResponseDto
import com.search.blog.search.dto.kakao.KakaoApiResponseDto
import com.search.blog.search.enum.ApiQueryParameterKey
import com.search.blog.search.exception.ApiBadRequestException
import com.search.blog.search.service.externalApi.replace.impl.ReplaceSearchApiCallServiceImpl
import com.search.blog.search.service.externalApi.default.DefaultSearchApiCallService
import com.search.blog.search.utils.HttpHeaderUtils
import com.search.blog.search.utils.ObjectMapperUtils
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.slf4j.Logger
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
class DefaultSearchApiCallServiceImpl(
    @param:Qualifier("kakaoRestTemplate") private val restTemplate: RestTemplate,
    private val externalApiProperties: ApiProperties,
    private val objectMapperUtils: ObjectMapperUtils,
    private val callerCircuitBreaker: ReplaceSearchApiCallServiceImpl
) : DefaultSearchApiCallService {


    private val logger: Logger =
        LoggerFactory.getLogger(DefaultSearchApiCallServiceImpl::class.java)
    private val KAKAO_AUTHORIZATION_KEY_PREFIX = "KakaoAK "



    @CircuitBreaker(name = "caller", fallbackMethod = "fallback")
    override fun searchBlogs(query: String?, page: Int, sort: String): ApiResponseDto {
        val uriComponents = UriComponentsBuilder.fromHttpUrl(externalApiProperties.kakao.apiUrl)
            .queryParam(ApiQueryParameterKey.KAKAO.query, query)
            .queryParam(ApiQueryParameterKey.KAKAO.page, page)
            .queryParam(ApiQueryParameterKey.KAKAO.sort, sort)
            .build()
        val headers: HttpHeaders = HttpHeaderUtils.createJsonMediaType()
        headers[HttpHeaders.AUTHORIZATION] =
            KAKAO_AUTHORIZATION_KEY_PREFIX + externalApiProperties.kakao.apiKey
        val entity = HttpEntity<String>(headers)
        return try {
            val response = restTemplate!!.exchange(
                uriComponents.encode().toUri(),
                HttpMethod.GET,
                entity,
                String::class.java
            )
            objectMapperUtils.jsonToObject(response.body!!, KakaoApiResponseDto::class.java)!!
        } catch (e: HttpClientErrorException) {
            val stackTraceElements = e.stackTrace
            logger.error("HttpClientErrorException : {}", stackTraceElements[0])
            throw ApiBadRequestException(e.message)
        }
    }

    /**
     * 카카오 API 호출 실패 시 대체 API 작동
     */
    fun fallback(query: String?, page: Int, sort: String, t: Throwable): ApiResponseDto? {
        logger.error("CircuitBreaker fallback start : {}", t.message)
        return callerCircuitBreaker.replaceSearch(query, page, sort)
    }
}
